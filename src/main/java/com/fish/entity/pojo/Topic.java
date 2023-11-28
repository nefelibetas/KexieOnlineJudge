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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  实体类。
 *
 * @author fish
 * @since 2023-11-14
 */
@Data
@Builder
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
}
