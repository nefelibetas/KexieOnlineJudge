package com.fish.entity.pojo;


import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
@Table(value = "oj_like")
public class Like implements Serializable {
    @Id(keyType = KeyType.Auto)
    private Long likeId;
    /**
     * 题解id
     */
    private Long solutionId;
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 点赞的用户
     */
    private String userId;
}
