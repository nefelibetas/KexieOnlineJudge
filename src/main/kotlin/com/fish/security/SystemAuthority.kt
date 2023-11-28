package com.fish.security

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class SystemAuthority @JsonCreator constructor(
    @param:JsonProperty("authority") val role: String
) : GrantedAuthority {
    override fun getAuthority(): String {
        return role
    }
}
