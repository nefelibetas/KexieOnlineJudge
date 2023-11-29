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
data class Column(
    @Id(keyType = KeyType.Auto)
    var columnId: Long?,
    var columnName: @NotBlank(message = "栏目名未填写") @Size(min = 1, max = 32, message = "1~32字符以内") String?,
    var columnDescribe: @NotBlank(message = "栏目描述未填写") @Size(
        min = 1,
        max = 100,
        message = "1~100字符以内"
    ) String?,
    var enabled: Boolean?,
) : Serializable {
    override fun toString(): String {
        return "Column(columnId=$columnId, columnName=$columnName, columnDescribe=$columnDescribe, enabled=$enabled)"
    }
}
