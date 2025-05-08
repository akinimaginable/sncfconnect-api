package org.etrange.sncf_connect_api.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class AccountEntity(
    @Id @GeneratedValue val id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null
) {
    protected constructor() : this(null)
}