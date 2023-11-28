package com.fish.entity.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class ColumnVO implements Serializable {
    private Long columnId;
    private String columnName;
    private String columnDescribe;
    private Boolean enabled;
    ArrayList<TopicVO> topicVOS;

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

    public ArrayList<TopicVO> getTopicVOS() {
        return topicVOS;
    }

    public void setTopicVOS(ArrayList<TopicVO> topicVOS) {
        this.topicVOS = topicVOS;
    }
}
