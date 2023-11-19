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
@Table(value = "oj_topic_solutions_comments")
public class TopicSolutionsComments implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long commentId;

    private String userId;

    private Long solutionId;

    /**
     * 父评论的id，作为鉴别是否为二级评论的标志
     */
    private Long parentId;

    private String content;

    private Boolean enabled;

    private LocalDateTime createTime;

}
