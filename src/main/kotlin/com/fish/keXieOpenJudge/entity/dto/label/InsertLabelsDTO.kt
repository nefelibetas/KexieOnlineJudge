package com.fish.keXieOpenJudge.entity.dto.label

import com.fish.keXieOpenJudge.entity.pojo.label.Label
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import java.io.Serializable

class InsertLabelsDTO: Serializable {
    @Valid
    @NotEmpty
    val labels: ArrayList<Label>? = null
}