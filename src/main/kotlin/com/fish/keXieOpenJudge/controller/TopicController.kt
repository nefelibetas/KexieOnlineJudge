package com.fish.keXieOpenJudge.controller

import com.fish.keXieOpenJudge.entity.dto.topic.InsertTopicDTO
import com.fish.keXieOpenJudge.entity.dto.topic.UpdateTopicDTO
import com.fish.keXieOpenJudge.entity.pojo.label.Label
import com.fish.keXieOpenJudge.entity.vo.TopicVO
import com.fish.keXieOpenJudge.service.topic.TopicLabelService
import com.fish.keXieOpenJudge.service.topic.TopicService
import com.fish.keXieOpenJudge.common.Result
import com.mybatisflex.core.paginate.Page
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.springframework.web.bind.annotation.*

@RestController
class TopicController(val topicService: TopicService, val topicLabelService: TopicLabelService) {
    /**
     * 新增题目
     * @param topic 题目
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/topic/add")
    fun addTopic(@RequestBody @Valid insertTopicDTO: InsertTopicDTO?): Result<*> {
        return topicService.addTopicWithExample(insertTopicDTO!!)
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
        @RequestBody @NotEmpty(message = "必须选择一个标签") labelIds: ArrayList<Long>?
    ): Result<*> {
        return topicLabelService.addLabelToTopic(topicId!!, labelIds!!)
    }

    /**
     * 获取该题目id可选的标签
     * @param topicId 题目id
     * @return 所有可选的标签
     */
    @GetMapping("/admin/topic/getOptionalLabel/{topicId}")
    fun getOptionalLabels(
        @PathVariable topicId: Long?,
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "20") pageSize: Int
    ): Result<Page<Label>> {
        return topicLabelService.getOptionalLabels(topicId!!, pageNo, pageSize)
    }

    /**
     * 禁用/启用 题目
     * @param topicId 题目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/topic/changeStatus/{topicId}")
    fun changeStatus(
        @PathVariable topicId: Long?,
        @RequestParam(defaultValue = "false") action: Boolean
    ): Result<*> {
        return topicService.changeStatus(topicId!!, action)
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
        @RequestBody @NotEmpty(message = "必须选择一个标签") labelIds: ArrayList<Long>?
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
        @RequestBody @Valid updateTopicDTO: UpdateTopicDTO
    ): Result<*> {
        return topicService.updateTopic(topicId!!, updateTopicDTO)
    }

    /**
     * 获取所以题目
     * @return 所有启用的题目
     */
    @GetMapping("/topic/gets")
    fun getTopics(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "20") pageSize: Int
    ): Result<Page<TopicVO>> {
        return topicService.getTopics(pageNo, pageSize)
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

    @GetMapping("/topic/search")
    fun search(
        @RequestParam(required = true) keyword: String,
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "20") pageSize: Int
    ): Result<Page<TopicVO>> {
        return topicService.search(keyword, pageNo, pageSize)
    }

}
