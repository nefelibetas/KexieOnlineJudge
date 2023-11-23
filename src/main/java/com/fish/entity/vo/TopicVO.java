package com.fish.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fish.entity.pojo.Label;
import com.fish.entity.pojo.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicVO implements Serializable {
    private Long topicId;
    private String uploadUserId;
    private String title;
    private String content;
    private String difficulty;
    private Long limitedMemory;
    private Long limitedTime;
    private String inputDescribe;
    private String outputDescribe;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;
    private String precautions;
    private String from;
    private ArrayList<Label> labels;
}
