package com.fish.entity.pojo

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_example_result")
data class ExampleResult(
    @Id(keyType = KeyType.Auto)
    var exampleResultId: Long?,
    var exampleId: Long?,
    var resultId: Long?,
    var memory: Long?,
    var time: Long?,
    var assessmentStatus: String?,
) : Serializable
