package com.fish.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ColumnVO implements Serializable {
    private Long columnId;
    private String columnName;
    private String columnDescribe;
    private Boolean enabled;
    ArrayList<TopicVO> topicVOS;
}
