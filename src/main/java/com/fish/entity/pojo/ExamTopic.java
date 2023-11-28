package com.fish.entity.pojo;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
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
@Table(value = "oj_exam_topic")
public class ExamTopic implements Serializable {

    @Id
    private Long examId;

    @Id
    private Long topicId;

    private Long examScore;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getExamScore() {
        return examScore;
    }

    public void setExamScore(Long examScore) {
        this.examScore = examScore;
    }
}
