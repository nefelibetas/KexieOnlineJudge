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
@Table(value = "oj_message_type")
class MessageType : Serializable {
    @Id(keyType = KeyType.Auto)
    var typeId: Long? = null
    var typeName: String? = null
    var typeDescribe: String? = null
}
