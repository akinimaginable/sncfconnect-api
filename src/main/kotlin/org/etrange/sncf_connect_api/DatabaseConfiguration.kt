package org.etrange.sncf_connect_api

import org.etrange.sncf_connect_api.repositories.AccountRepository
import org.etrange.sncf_connect_api.repositories.OfferRepository
import org.etrange.sncf_connect_api.repositories.TripRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfiguration {
    @Bean
    fun databaseInitializer(
        accountRepository: AccountRepository, offerRepository: OfferRepository, tripRepository: TripRepository
    ) = ApplicationRunner {
        tripRepository.deleteAll()
        offerRepository.deleteAll()
        accountRepository.deleteAll()

        accountRepository.saveAll(accounts).toList()
        offerRepository.saveAll(offers)

        /*val createdTrips = trips.mapIndexed { index, trip ->
            val passengerIndex = index.rem(savedAccounts.size)
            val passengerAccount = savedAccounts.elementAt(passengerIndex)
            trip.copy(passenger = passengerAccount)
        }

        tripRepository.saveAll(createdTrips)*/
    }
}
