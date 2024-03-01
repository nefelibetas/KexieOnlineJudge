package com.fish.keXieOnlineJudge.service.topic

import com.fish.keXieOnlineJudge.common.Result
import com.fish.keXieOnlineJudge.entity.dto.topic.InsertTopicSolutionDTO
import com.fish.keXieOnlineJudge.entity.pojo.topic.TopicSolution
import com.fish.keXieOnlineJudge.entity.vo.PreviewTopicSolution
import com.fish.keXieOnlineJudge.entity.vo.TopicSolutionVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface TopicSolutionService : IService<TopicSolution> {
    /**
     * 添加题解,若内容出错则评论区见
     * @param solution 前端传输的题解数据,具体要求见下方
     * @sample InsertTopicSolutionDTO
     */
    fun addSolution(solution: InsertTopicSolutionDTO) : Result<*>
    /**
     * 获得题解
     * @param solutionId 题解id
     */
    fun getSolution(solutionId: Long?, pageNo: Int, pageSize: Int) : Result<TopicSolutionVO>
    /**
     * 获取一道题目的全部题解
     * @param topicId 题目id
     */
    fun getAllSolutions(topicId: Long?, pageNo: Int, pageSize: Int) : Result<Page<PreviewTopicSolution>>
    /**
     * 设置置顶和取消置顶
     * @param topicId 题目id
     * @param solutionId 题解id
     */
    fun changePined(topicId: Long?, solutionId: Long?, action: Boolean) : Result<*>
    /**
     * 启用或禁用题解
     * @param solutionId 题解id
     */
    fun changeStatus(topicId: Long?, solutionId: Long?, action: Boolean) : Result<*>
}
