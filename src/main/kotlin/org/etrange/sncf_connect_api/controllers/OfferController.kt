package org.etrange.sncf_connect_api.controllers

import org.etrange.sncf_connect_api.dtos.OfferDto
import org.etrange.sncf_connect_api.dtos.PageDto
import org.etrange.sncf_connect_api.extensions.toDto
import org.etrange.sncf_connect_api.repositories.OfferRepository
import org.etrange.sncf_connect_api.validations.PaginationUtils
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/offers")
class OfferController(private val offerRepository: OfferRepository) {
    @GetMapping
    fun getOffers(
        @RequestParam("page", defaultValue = "1") page: Int, @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<PageDto<OfferDto>> {
        PaginationUtils.validatePaginationParams(page)
        val pageSize = size.coerceAtMost(50)
        val pageable: Pageable = PageRequest.of(page - 1, pageSize)

        val offersPage = offerRepository.findAll(pageable)
        if (offersPage.isEmpty) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }

        val paginatedOffers = offersPage.content.map { it.toDto() }

        return ResponseEntity.ok(
            PageDto(page = page, pageSize = pageSize, total = offersPage.totalElements, data = paginatedOffers)
        )
    }

    @GetMapping("/{id}")
    fun getOfferById(@PathVariable id: Long): ResponseEntity<OfferDto> =
        offerRepository.findById(id).map { it.toDto() }.map { ResponseEntity.ok(it) }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found") }
}
