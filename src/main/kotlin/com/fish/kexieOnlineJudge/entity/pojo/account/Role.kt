package com.fish.kexieOnlineJudge.entity.pojo.account

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
open class Role(
    roleId: Long?,
    roleName: String?,
    roleDescribe: String?
) : Serializable {
    @Id(keyType = KeyType.Auto)
    var roleId: Long? = null
    val roleName: String? = null
    val roleDescribe: String? = null
}
