package com.fish.entity.dto

import java.io.Serializable

data class UpdateAccountDTO(
    var id: String,
    val nickname: String,
    val avatar: String,
    val gender: String,
    val email: String,
    val qq: String,
    val blogAddress: String,
    val githubAddress: String
): Serializable
