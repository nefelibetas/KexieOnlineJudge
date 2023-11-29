package com.fish.service.topic

import com.fish.common.Result
import com.fish.entity.dto.SolutionDTO
import com.fish.entity.pojo.TopicSolution
import com.mybatisflex.core.service.IService

interface TopicSolutionService : IService<TopicSolution> {
    /**
     * 添加题解,若内容出错则评论区见
     * @param solution 前端传输的题解数据,具体要求见下方
     * @sample SolutionDTO
     */
    fun addSolution(solution: SolutionDTO) : Result<*>
    /**
     * 获得题解
     * @param solutionId 题解id
     */
    fun getSolution(solutionId: Long) : Result<TopicSolution>
    /**
     * 获取一道题目的全部题解
     * @param topicId 题目id
     */
    fun getAllSolutions(topicId: Long) : Result<ArrayList<TopicSolution>>
    /**
     * 设置置顶，这里仅仅考虑单置顶
     * @param topicId 题目id
     * @param solutionId 题解id
     */
    fun setPined(topicId: Long, solutionId: Long) : Result<*>
    /**
     * 取消置顶
     * @param topicId 题目id
     * @param solutionId 题解id
     */
    fun disablePined(solutionId: Long) : Result<*>
    /**
     * 禁用题目
     * @param solutionId 题目id
     */
    fun disableSolution(solutionId: Long) : Result<*>
    /**
     * 启用题目
     * @param solutionId 题目id
     */
    fun enableSolution(solutionId: Long) : Result<*>
}
