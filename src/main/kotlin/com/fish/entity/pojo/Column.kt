package com.fish.entity.pojo

import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.io.Serializable

/**
 * 实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Table(value = "oj_column")
open class Column(
    columnId: Long?,
    columnName: String?,
    columnDescribe: String?,
    enabled: Boolean?
): Serializable {
    @Id(keyType = KeyType.Auto)
    val columnId: Long? = null
    @NotBlank(message = "栏目名未填写")
    @Size(min = 1, max = 32, message = "1~32字符以内")
    val columnName:  String? = null
    @NotBlank(message = "栏目描述未填写")
    @Size(min = 1, max = 100, message = "1~100字符以内")
    val columnDescribe: String? = null
    val enabled: Boolean? = null
}
