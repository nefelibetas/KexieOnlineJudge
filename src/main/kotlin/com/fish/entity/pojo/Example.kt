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
@Table(value = "oj_example")
open class Example(
    exampleId: Long?,
    topicId: Long?,
    input: String?,
    output: String?,
    showed: Boolean?,
    assessed: Boolean?,
) : Serializable {
    @Id(keyType = KeyType.Auto)
    var exampleId: Long? = null
    var topicId: Long? = null
    var input: String? = null
    var output: String? = null
    /**
     * 是否展示
     */
    var showed: Boolean? = null
    /**
     * 是否参与测评
     */
    var assessed: Boolean? = null
}
