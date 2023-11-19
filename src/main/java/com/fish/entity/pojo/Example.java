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

}
