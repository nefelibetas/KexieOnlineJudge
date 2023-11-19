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
@Table(value = "oj_exam")
public class Exam implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long examId;

    private String hostId;

    /**
     * 是否公布成绩
     */
    private Boolean opened;

    /**
     * 是否进行排行
     */
    private Boolean ranked;

    private String describe;

    /**
     * 考试开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间，默认为开始时间后七天
     */
    private LocalDateTime endTime;

    /**
     * 该项考试创建时间，和enabled字段搭配实现复用
     */
    private LocalDateTime createTime;

    private Boolean enabled;

}
