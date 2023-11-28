package com.fish.entity.pojo;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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
@Table(value = "oj_example_result")
public class ExampleResult implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long exampleResultId;

    private Long exampleId;

    private Long resultId;

    private Long memory;

    private Long time;

    private String assessmentStatus;

    public Long getExampleResultId() {
        return exampleResultId;
    }

    public void setExampleResultId(Long exampleResultId) {
        this.exampleResultId = exampleResultId;
    }

    public Long getExampleId() {
        return exampleId;
    }

    public void setExampleId(Long exampleId) {
        this.exampleId = exampleId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getAssessmentStatus() {
        return assessmentStatus;
    }

    public void setAssessmentStatus(String assessmentStatus) {
        this.assessmentStatus = assessmentStatus;
    }
}
