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
@Table(value = "oj_role")
data class Role(
    @Id(keyType = KeyType.Auto) var roleId: Long?,
    val roleName: String?,
    val roleDescribe: String?,
) : Serializable {
    override fun toString(): String {
        return "Role(roleId=$roleId, roleName=$roleName, roleDescribe=$roleDescribe)"
    }
}
