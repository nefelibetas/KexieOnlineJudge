package com.fish.keXieOpenJudge.mapper.example

import com.fish.keXieOpenJudge.entity.pojo.example.AssessmentResult
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Select

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface AssessmentResultMapper : BaseMapper<AssessmentResult> {
    @Select("select oj_assessment_result.* from oj_assessment_result where topic_id = #{topicId}")
    fun getHashList(topicId: Long): List<AssessmentResult>
}
