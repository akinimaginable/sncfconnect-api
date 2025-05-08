package org.etrange.sncf_connect_api.repositories

import org.etrange.sncf_connect_api.entities.AccountEntity
import org.etrange.sncf_connect_api.entities.OfferEntity
import org.etrange.sncf_connect_api.entities.TripEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : CrudRepository<AccountEntity, Long>, PagingAndSortingRepository<AccountEntity, Long> {
    fun findByEmail(email: String): AccountEntity?
}

@Repository
interface OfferRepository : CrudRepository<OfferEntity, Long>, PagingAndSortingRepository<OfferEntity, Long>

@Repository
interface TripRepository : CrudRepository<TripEntity, Long>, PagingAndSortingRepository<TripEntity, Long> {
    fun findByPassengerId(id: Long): List<TripEntity>
}
