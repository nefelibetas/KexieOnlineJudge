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
@Table(value = "oj_message_type")
open class MessageType(
    typeId: Long?,
    typeName: String?,
    typeDescribe: String?,
) : Serializable {
    @Id(keyType = KeyType.Auto)
    val typeId: Long? = null
    val typeName: String? = null
    val typeDescribe: String? = null
}
