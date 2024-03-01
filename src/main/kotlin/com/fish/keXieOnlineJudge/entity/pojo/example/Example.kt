package com.fish.keXieOnlineJudge.entity.pojo.example

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
open class Example: Serializable {
    @Id(keyType = KeyType.Auto)
    val exampleId: Long? = null
    val topicId: Long? = null
    val input: String? = null
    val output: String? = null
    /**
     * 是否展示
     */
    val showed: Boolean? = null
    /**
     * 是否参与测评
     */
    val assessed: Boolean? = null
}
