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
@Table(value = "oj_assessment_result")
public class AssessmentResult implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long resultId;

    private String userId;

    private Long topicId;

    private String code;

    private String hash;

    private Long examId;

    private Long score;

    private Long allTime;

    private String status;

}
