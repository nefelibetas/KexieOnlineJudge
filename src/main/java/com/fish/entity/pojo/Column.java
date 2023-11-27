package com.fish.entity.pojo;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "oj_column")
public class Column implements Serializable {
    @Id(keyType = KeyType.Auto)
    private Long columnId;
    @NotBlank(message = "栏目名未填写")
    @Size(min = 1, max = 32,message = "1~32字符以内")
    private String columnName;
    @NotBlank(message = "栏目描述未填写")
    @Size(min = 1, max = 100, message = "1~100字符以内")
    private String columnDescribe;
    private Boolean enabled;

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDescribe() {
        return columnDescribe;
    }

    public void setColumnDescribe(String columnDescribe) {
        this.columnDescribe = columnDescribe;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
