package com.fish.keXieOnlineJudge.entity.pojo.column

import com.mybatisflex.annotation.Column
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
open class Column: Serializable {
    @Id(keyType = KeyType.Auto)
    var columnId: Long? = null
    @NotBlank(message = "栏目名未填写")
    @Size(min = 1, max = 32, message = "栏目名要求在1~32字符以内")
    val columnName:  String? = null
    @NotBlank(message = "栏目描述未填写")
    @Size(min = 1, max = 100, message = "栏目描述要求在1~100字符以内")
    val columnDescribe: String? = null
    @Column(onInsertValue = "true")
    var enabled: Boolean? = null
}
