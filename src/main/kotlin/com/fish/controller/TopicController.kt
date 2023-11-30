package com.fish.controller

import com.fish.common.Result
import com.fish.entity.pojo.Label
import com.fish.entity.pojo.Topic
import com.fish.entity.vo.TopicVO
import com.fish.service.topic.TopicLabelService
import com.fish.service.topic.TopicService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.*

@RestController
class TopicController(val topicService: TopicService, val topicLabelService: TopicLabelService) {
    /**
     * 新增题目
     * @param topic 题目
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/topic/add")
    fun addTopic(topic: @Valid Topic?): Result<*> {
        return topicService.addTopic(topic!!)
    }

    /**
     * 批量增加题目
     * @param topics 题目数组
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/topic/adds")
    fun addTopicBatch(topics: @NotEmpty(message = "至少上传一道题目") ArrayList<@Valid Topic>?): Result<*> {
        return topicService.addTopicBatch(topics!!)
    }

    /**
     * 给题目批量绑定标签
     * @param topicId 题目id
     * @param labelIds 要加的所有标签id
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/topic/addLabel/{topicId}")
    fun addTopicLabel(
        @PathVariable topicId: Long?,
        @RequestBody labelIds: @NotEmpty(message = "必须选择一个标签") ArrayList<@NotNull(message = "标签id不能为空") Long>?
    ): Result<*> {
        return topicLabelService.addLabelToTopic(topicId!!, labelIds!!)
    }

    /**
     * 获取该题目id可选的标签
     * @param topicId 题目id
     * @return 所有可选的标签
     */
    @GetMapping("/admin/topic/getOptionalLabel/{topicId}")
    fun getOptionalLabels(@PathVariable topicId: Long?): Result<ArrayList<Label>> {
        return topicLabelService.getOptionalLabels(topicId!!)
    }

    /**
     * 禁用题目
     * @param topicId 题目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/topic/disable/{topicId}")
    fun disableTopic(@PathVariable topicId: Long?): Result<*> {
        return topicService.disableTopic(topicId!!)
    }

    /**
     * 启用题目
     * @param topicId 题目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/topic/enable/{topicId}")
    fun enableTopic(@PathVariable topicId: Long?): Result<*> {
        return topicService.enableTopic(topicId!!)
    }

    /**
     * 删除该题目的一些标签
     * @param topicId 题目id
     * @param labelIds 一些标签的id
     * @return 响应code为200表示成功
     */
    @DeleteMapping("/admin/topic/deleteLabel/{topicId}")
    fun deleteTopicLabel(
        @PathVariable topicId: Long?,
        @RequestBody labelIds: @NotEmpty(message = "必须选择一个标签") ArrayList<@NotNull(message = "标签id不能为空") Long>?
    ): Result<*> {
        return topicLabelService.removeLabels(topicId!!, labelIds!!)
    }

    /**
     * 修改题目
     * @param topicId 题目id
     * @param topic 题目
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/topic/update/{topicId}")
    fun updateTopic(
        @PathVariable topicId: Long?,
        @RequestBody topic: @Valid Topic?
    ): Result<*> {
        return topicService.updateTopic(topicId!!, topic!!)
    }

    /**
     * 获取所以题目
     * @return 所有启用的题目
     */
    @GetMapping("/topic/gets")
    fun getTopics(): Result<ArrayList<TopicVO>> {
        return topicService.getTopics()
    }

    /**
     * 获取对应id的题目
     * @param topicId 题目id
     * @return 该id对应的题目
     */
    @GetMapping("/topic/get/{topicId}")
    fun getTopic(@PathVariable topicId: Long?): Result<TopicVO> {
        return topicService.getTopic(topicId!!)
    }
}
