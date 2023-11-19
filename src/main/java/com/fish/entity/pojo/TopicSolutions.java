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
@Table(value = "oj_topic_solutions")
public class TopicSolutions implements Serializable {
    @Id(keyType = KeyType.Auto)
    private Long solutionId;
    private Long topicId;
    private String title;
    private String content;
    /**
     * 置顶
     */
    private Boolean pined;
    private Boolean enabled;
    private LocalDateTime createTime;

}
