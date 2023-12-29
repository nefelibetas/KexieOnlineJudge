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
open class ExampleResult(
    exampleResultId: Long?,
    exampleId: Long?,
    resultId: Long?,
    memory: Long?,
    time: Long?,
    assessmentStatus: String?,
) : Serializable {
    @Id(keyType = KeyType.Auto)
    var exampleResultId: Long? = null
    var exampleId: Long? = null
    var resultId: Long? = null
    var memory: Long? = null
    var time: Long? = null
    var assessmentStatus: String? = null
}
