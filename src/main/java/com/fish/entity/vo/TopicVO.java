package com.fish.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fish.entity.pojo.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicVO implements Serializable {
    private Long topicId;
    private String uploadUserId;
    private String title;
    /**
     * 题面
     */
    private String content;
    private String difficulty;
    /**
     * 限制内存
     */
    private Long limitedMemory;
    /**
     * 限制时间
     */
    private Long limitedTime;
    /**
     * 输入描述
     */
    private String inputDescribe;
    /**
     * 输出描述
     */
    private String outputDescribe;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;
    private String precautions;
    private String from;
    public TopicVO(Topic topic) {
        this.topicId = topic.getTopicId();
        this.uploadUserId = topic.getUploadUserId();
        this.title = topic.getTitle();
        this.content = topic.getContent();
        this.difficulty = topic.getDifficulty();
        this.limitedMemory = topic.getLimitedMemory();
        this.limitedTime = topic.getLimitedTime();
        this.inputDescribe = topic.getInputDescribe();
        this.outputDescribe = topic.getOutputDescribe();
        this.updateTime = topic.getUpdateTime();
        this.precautions = topic.getPrecautions();
        this.from = topic.getFrom();
    }
}
