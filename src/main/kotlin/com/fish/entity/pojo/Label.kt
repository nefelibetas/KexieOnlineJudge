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
@Table(value = "oj_label")
data class Label(
    @Id(keyType = KeyType.Auto) var labelId: Long?,
    var labelName: @Size(min = 1, max = 32, message = "1~32字符以内") @NotBlank(message = "标签名未填写") String?,
) : Serializable {
    override fun toString(): String {
        return "Label(labelId=$labelId, labelName=$labelName)"
    }
}
