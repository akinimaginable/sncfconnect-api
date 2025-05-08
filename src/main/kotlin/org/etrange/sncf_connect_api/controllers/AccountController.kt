package org.etrange.sncf_connect_api.controllers

import org.etrange.sncf_connect_api.dtos.AccountDto
import org.etrange.sncf_connect_api.dtos.PageDto
import org.etrange.sncf_connect_api.extensions.toDto
import org.etrange.sncf_connect_api.repositories.AccountRepository
import org.etrange.sncf_connect_api.validations.PaginationUtils
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountRepository: AccountRepository) {
    @GetMapping
    fun getAccounts(
        @RequestParam("page", defaultValue = "1") page: Int, @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<PageDto<AccountDto>> {
        PaginationUtils.validatePaginationParams(page)
        val pageSize = size.coerceAtMost(50)
        val pageable: Pageable = PageRequest.of(page - 1, pageSize)

        val accountPage = accountRepository.findAll(pageable)
        if (accountPage.isEmpty) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }

        val paginatedAccounts = accountPage.content.map { it.toDto() }

        return ResponseEntity.ok(
            PageDto(page = page, pageSize = pageSize, total = accountPage.totalElements, data = paginatedAccounts)
        )
    }

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable id: Long): ResponseEntity<AccountDto> =
        accountRepository.findById(id).map { it.toDto() }.map { ResponseEntity.ok(it) }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found") }
}
