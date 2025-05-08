package org.etrange.sncf_connect_api.controllers

import org.etrange.sncf_connect_api.dtos.PageDto
import org.etrange.sncf_connect_api.dtos.TripDto
import org.etrange.sncf_connect_api.extensions.toDto
import org.etrange.sncf_connect_api.repositories.TripRepository
import org.etrange.sncf_connect_api.validations.PaginationUtils
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/trips")
class TripController(private val tripRepository: TripRepository) {
    @GetMapping
    fun getTrips(
        @RequestParam("page", defaultValue = "1") page: Int, @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<PageDto<TripDto>> {
        PaginationUtils.validatePaginationParams(page)
        val pageSize = size.coerceAtMost(50)
        val pageable: Pageable = PageRequest.of(page - 1, pageSize)

        val tripsPage = tripRepository.findAll(pageable)
        if (tripsPage.isEmpty) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }

        val paginatedTrips = tripsPage.content.map { it.toDto() }

        return ResponseEntity.ok(
            PageDto(page = page, pageSize = pageSize, total = tripsPage.totalElements, data = paginatedTrips)
        )
    }

    @GetMapping("/{id}")
    fun getTripById(@PathVariable id: Long): ResponseEntity<TripDto> =
        tripRepository.findById(id).map { it.toDto() }.map { ResponseEntity.ok(it) }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found") }
}
