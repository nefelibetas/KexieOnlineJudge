package com.fish.entity.dto

import com.fish.entity.pojo.Label
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import java.io.Serializable

class LabelsDTO: Serializable {
    @Valid
    @NotEmpty
    val labels: ArrayList<Label>? = null
}