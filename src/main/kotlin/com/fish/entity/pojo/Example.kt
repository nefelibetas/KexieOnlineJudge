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
data class Example(
    @Id(keyType = KeyType.Auto)
    var exampleId: Long?,
    var topicId: Long?,
    var input: String?,
    var output: String?,
    /**
     * 是否展示
     */
    var showed: Boolean?,
    /**
     * 是否参与测评
     */
    var assessed: Boolean?,
) : Serializable {
    override fun toString(): String {
        return "Example(exampleId=$exampleId, topicId=$topicId, input=$input, output=$output, showed=$showed, assessed=$assessed)"
    }
}
