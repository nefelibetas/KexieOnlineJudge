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
@Table(value = "oj_example")
public class Example implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long exampleId;

    private Long topicId;

    private String input;

    private String output;

    /**
     * 是否展示
     */
    private Boolean showed;

    /**
     * 是否参与测评
     */
    private Boolean assessed;

    public Long getExampleId() {
        return exampleId;
    }

    public void setExampleId(Long exampleId) {
        this.exampleId = exampleId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Boolean getShowed() {
        return showed;
    }

    public void setShowed(Boolean showed) {
        this.showed = showed;
    }

    public Boolean getAssessed() {
        return assessed;
    }

    public void setAssessed(Boolean assessed) {
        this.assessed = assessed;
    }
}
