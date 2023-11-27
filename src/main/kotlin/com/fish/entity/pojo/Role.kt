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
@Table(value = "oj_role")
class Role : Serializable {
    @Id(keyType = KeyType.Auto)
    var roleId: Long? = null
    var roleName: String? = null
    var roleDescribe: String? = null
}
