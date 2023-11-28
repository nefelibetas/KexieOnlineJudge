package com.fish.entity.pojo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "oj_topic")
public class Topic implements Serializable {
    @Id(keyType = KeyType.Auto)
    private Long topicId;
    @NotBlank(message = "上传人id不能为空")
    private String uploadUserId;
    @NotBlank(message = "题目未填写")
    @Size(min = 2, max = 32, message = "题目要求在2~32字内")
    private String title;
    /**
     * 题面
     */
    @NotBlank(message = "题面未填写")
    @Size(min = 2, max = 1000, message = "题面要求在2~1000字内")
    private String content;
    @Pattern(regexp = "^([低中高])$", message = "只能在低、中、高中选择")
    private String difficulty;
    /**
     * 限制内存
     */
    @NotNull(message = "最大内存未填写")
    private Long limitedMemory;
    /**
     * 限制时间
     */
    @NotNull(message = "最大时间未填写")
    private Long limitedTime;
    /**
     * 输入描述
     */
    @NotBlank(message = "输入描述未填写")
    @Size(min = 2, max = 1000, message = "输入描述要求在2~1000字内")
    private String inputDescribe;
    /**
     * 输出描述
     */
    @NotBlank(message = "输出描述未填写")
    @Size(min = 2, max = 1000, message = "输出描述要求在2~1000字内")
    private String outputDescribe;
    /**
     * 是否开启题解
     */
    private Boolean enabledSolution;
    /**
     * 题目创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
    /**
     * 题目更新时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;
    /**
     * 注意事项
     */
    private String precautions;
    private String from;
    private Boolean enabled;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(String uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getLimitedMemory() {
        return limitedMemory;
    }

    public void setLimitedMemory(Long limitedMemory) {
        this.limitedMemory = limitedMemory;
    }

    public Long getLimitedTime() {
        return limitedTime;
    }

    public void setLimitedTime(Long limitedTime) {
        this.limitedTime = limitedTime;
    }

    public String getInputDescribe() {
        return inputDescribe;
    }

    public void setInputDescribe(String inputDescribe) {
        this.inputDescribe = inputDescribe;
    }

    public String getOutputDescribe() {
        return outputDescribe;
    }

    public void setOutputDescribe(String outputDescribe) {
        this.outputDescribe = outputDescribe;
    }

    public Boolean getEnabledSolution() {
        return enabledSolution;
    }

    public void setEnabledSolution(Boolean enabledSolution) {
        this.enabledSolution = enabledSolution;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
