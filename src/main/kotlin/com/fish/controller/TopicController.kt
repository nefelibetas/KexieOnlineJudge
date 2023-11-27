package com.fish.controller

import com.fish.common.Result
import com.fish.entity.pojo.Label
import com.fish.entity.pojo.Topic
import com.fish.entity.vo.TopicVO
import com.fish.service.topic.TopicLabelService
import com.fish.service.topic.TopicService
import jakarta.annotation.Resource
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.*

@RestController
class TopicController {
    @Resource
    var topicService: TopicService? = null

    @Resource
    var topicLabelService: TopicLabelService? = null

    /**
     * 新增题目
     * @param topic 题目
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/topic/add")
    fun addTopic(topic: @Valid Topic?): Result<*> {
        return topicService!!.addTopic(topic)
    }

    /**
     * 批量增加题目
     * @param topics 题目数组
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/topic/adds")
    fun addTopicBatch(topics: @Valid ArrayList<Topic?>?): Result<*> {
        return topicService!!.addTopicBatch(topics)
    }

    /**
     * 给题目批量绑定标签
     * @param topicId 题目id
     * @param labelIds 要加的所有标签id
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/topic/addLabel/{topicId}")
    fun addTopicLabel(
        @PathVariable("topicId") topicId: @NotNull(message = "题目id不能为空") Long?,
        @RequestBody labelIds: @NotEmpty(message = "必须选择一个标签") ArrayList<Long?>?
    ): Result<*> {
        return topicLabelService!!.addLabelToTopic(topicId, labelIds)
    }

    /**
     * 获取该题目id可选的标签
     * @param topicId 题目id
     * @return 所有可选的标签
     */
    @GetMapping("/admin/topic/getOptionalLabel/{topicId}")
    fun getOptionalLabels(@PathVariable("topicId") topicId: @NotNull(message = "题目id不能为空") Long?): Result<ArrayList<Label>> {
        return topicLabelService!!.getOptionalLabels(topicId)
    }

    /**
     * 删除题目
     * @param topicId 题目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/topic/delete/{topicId}")
    fun deleteTopic(@PathVariable("topicId") topicId: @NotNull(message = "题目Id不能为空") Long?): Result<*> {
        return topicService!!.deleteTopic(topicId)
    }

    /**
     * 删除该题目的一些标签
     * @param topicId 题目id
     * @param labelIds 一些标签的id
     * @return 响应code为200表示成功
     */
    @DeleteMapping("/admin/topic/deleteLabel/{topicId}")
    fun deleteTopicLabel(
        @PathVariable("topicId") topicId: @NotNull(message = "题目Id不能为空") Long?,
        @RequestBody labelIds: @NotEmpty(message = "必须选择一个标签") ArrayList<Long?>?
    ): Result<*> {
        return topicLabelService!!.removeLabels(topicId, labelIds)
    }

    /**
     * 修改题目
     * @param topicId 题目id
     * @param topic 题目
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/topic/update/{topicId}")
    fun updateTopic(
        @PathVariable("topicId") topicId: @NotNull(message = "题目Id不能为空") Long?,
        @RequestBody topic: @Valid Topic?
    ): Result<*> {
        return topicService!!.updateTopic(topicId, topic)
    }

    @get:GetMapping("/topic/gets")
    val topics: Result<ArrayList<TopicVO>>
        /**
         * 获取所以题目
         * @return 所有启用的题目
         */
        get() = topicService!!.getTopics()

    /**
     * 获取对应id的题目
     * @param topicId 题目id
     * @return 该id对应的题目
     */
    @GetMapping("/topic/get/{topicId}")
    fun getTopic(@PathVariable("topicId") topicId: @NotNull(message = "题目Id不能为空") Long?): Result<TopicVO> {
        return topicService!!.getTopic(topicId)
    }
}
