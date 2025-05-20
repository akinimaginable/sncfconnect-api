package org.etrange.sncfconnect.application.dtos

data class OfferDto(
    val id: Long,
    val name: String,
    val altText: String,
    val illustration: String,
    val price: Int?,
    val discount: Int?
)
