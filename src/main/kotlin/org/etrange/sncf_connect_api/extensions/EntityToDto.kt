package org.etrange.sncf_connect_api.extensions

import org.etrange.sncf_connect_api.dtos.AccountDto
import org.etrange.sncf_connect_api.dtos.OfferDto
import org.etrange.sncf_connect_api.dtos.TripDto
import org.etrange.sncf_connect_api.entities.AccountEntity
import org.etrange.sncf_connect_api.entities.OfferEntity
import org.etrange.sncf_connect_api.entities.TripEntity

fun AccountEntity.toDto(): AccountDto = AccountDto(
    id = this.id ?: -1, firstName = this.firstName ?: "", lastName = this.lastName ?: "", email = this.email ?: ""
)

fun OfferEntity.toDto(): OfferDto = OfferDto(
    id = this.id ?: -1,
    name = this.name ?: "",
    altText = this.altText ?: "",
    illustration = this.illustration ?: "",
    price = this.price ?: 0,
    discount = this.discount ?: 0
)

fun TripEntity.toDto(): TripDto = TripDto(
    id = this.id ?: -1,
    departureDate = this.departureDate ?: "",
    arrivalDate = this.arrivalDate ?: "",
    departureStation = this.departureStation ?: "",
    arrivalStation = this.arrivalStation ?: "",
    trainNumber = this.trainNumber ?: "",
    duration = this.duration ?: "",
    price = this.price ?: 0,
    currency = this.currency ?: "",
    type = this.type ?: "",
    passenger = this.passenger?.toDto() ?: AccountDto(-1, "", "", ""),
    coach = this.coach ?: "",
    seat = this.seat ?: "",
    co2Emission = this.co2Emission ?: 0.0,
    reference = this.reference ?: "",
    purpose = this.purpose
)