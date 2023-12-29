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
    @Id(keyType = KeyType.Auto)
    var typeId: Long?,
    var typeName: String?,
    var typeDescribe: String?,
) : Serializable
