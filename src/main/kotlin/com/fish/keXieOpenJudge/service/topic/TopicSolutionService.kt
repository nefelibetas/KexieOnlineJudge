package com.fish.keXieOpenJudge.service.topic

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.solution.InsertSolutionDTO
import com.fish.keXieOpenJudge.entity.pojo.topic.TopicSolution
import com.fish.keXieOpenJudge.entity.vo.PreviewTopicSolution
import com.fish.keXieOpenJudge.entity.vo.TopicSolutionVO
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface TopicSolutionService : IService<TopicSolution> {
    /**
     * 添加题解,若内容出错则评论区见
     * @param solution 前端传输的题解数据,具体要求见下方
     * @sample InsertSolutionDTO
     */
    fun addSolution(solution: InsertSolutionDTO) : Result<*>
    /**
     * 获得题解
     * @param solutionId 题解id
     */
    fun getSolution(solutionId: Long) : Result<TopicSolutionVO>
    /**
     * 获取一道题目的全部题解
     * @param topicId 题目id
     */
    fun getAllSolutions(topicId: Long, pageNo: Int, pageSize: Int) : Result<Page<PreviewTopicSolution>>
    /**
     * 设置置顶和取消置顶
     * @param topicId 题目id
     * @param solutionId 题解id
     */
    fun changePined(topicId: Long, solutionId: Long, action: Boolean) : Result<*>
    /**
     * 启用或禁用题解
     * @param solutionId 题解id
     */
    fun changeStatus(topicId: Long, solutionId: Long, action: Boolean) : Result<*>
}
