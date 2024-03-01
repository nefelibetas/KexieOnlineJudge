package com.fish.keXieOpenJudge.entity.pojo.example

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
open class ExampleResult(
    exampleId: Long,
    resultId: Long,
    memory: String,
    cpuTime: String,
    realTime: String,
    assessmentStatus: String?
): Serializable {
    @Id(keyType = KeyType.Auto)
    val exampleResultId: Long? = null
    val exampleId = exampleId
    val resultId = resultId
    val memory = memory
    val cpuTime = cpuTime
    val realTime = realTime
    val assessmentStatus = assessmentStatus
}
