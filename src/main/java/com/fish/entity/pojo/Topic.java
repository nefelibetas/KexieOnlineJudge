package com.fish.entity.pojo;


import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
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

    private String uploadUserId;

    /**
     * 标题
     */
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

    /**
     * 是否开启题解
     */
    private Boolean enabledSolution;

    /**
     * 题目创建时间
     */
    private LocalDateTime createTime;

    /**
     * 题目更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 注意事项
     */
    private String precautions;

    private String from;

    private Boolean enabled;

}
