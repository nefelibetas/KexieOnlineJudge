package com.fish.entity.pojo

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_example")
class Example : Serializable {
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
