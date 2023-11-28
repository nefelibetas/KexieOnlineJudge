package com.fish.entity.pojo;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(value = "oj_accepted")
public class Accepted implements Serializable {
    @Id
    @NotNull(message = "用户Id未填写")
    private String userId;
    @Id
    @NotNull(message = "题目Id未填写")
    private Long topicId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
