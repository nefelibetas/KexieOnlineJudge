package com.fish.keXieOpenJudge.controller

import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.entity.dto.column.InsertColumnDTO
import com.fish.keXieOpenJudge.entity.dto.column.UpdateColumnDTO
import com.fish.keXieOpenJudge.entity.vo.ColumnVO
import com.fish.keXieOpenJudge.entity.vo.TopicVO
import com.fish.keXieOpenJudge.service.column.ColumnService
import com.fish.keXieOpenJudge.service.column.ColumnTopicService
import com.mybatisflex.core.paginate.Page
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import org.springframework.web.bind.annotation.*

@RestController
class ColumnController(val columnService: ColumnService, val columnTopicService: ColumnTopicService) {
    /**
     * 新增栏目
     * @param column 栏目信息
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/column/addWithTopic")
    fun addWithTopic(@RequestBody @Valid insertColumnDTO: InsertColumnDTO?): Result<*> {
        return columnService.addWithTopic(insertColumnDTO!!)
    }

    /**
     * 给栏目批量增加题目
     * @param columnId 栏目id
     * @param topicIds 要添加的题目的id素组
     */
    @PostMapping("/admin/column/addTopic/{columnId}")
    fun addTopicsToColumn(
        @PathVariable columnId: Long?,
        @RequestBody @NotEmpty(message = "至少选一题")  topicIds : ArrayList<Long>?
    ) : Result<*> {
        return columnTopicService.addTopicToColumn(columnId!!, topicIds!!)
    }

    /**
     * 获取全部栏目
     * @return 全部栏目,包括栏目内的题目(题目包括其标签)
     */
    @GetMapping("/column/gets")
    fun getColumns(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "5") pageSize: Int
    ): Result<Page<ColumnVO>> {
        return columnService.getColumns(pageNo, pageSize)
    }

    /**
     * 获取特定栏目
     * @param columnId 栏目id
     * @return 该栏目的全部信息
     */
    @GetMapping("/column/get/{columnId}")
    fun getColumn(@PathVariable columnId: Long?): Result<ColumnVO> {
        return columnService.getColumn(columnId!!)
    }
    @GetMapping("/column/search")
    fun searchColumn(
        @RequestParam(required = true) keyword: String,
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "5") pageSize: Int,
        ): Result<Page<ColumnVO>> {
        return columnService.search(keyword, pageNo, pageSize)
    }

    /**
     * 获取该栏目还能添加的题目
     * @param columnId 栏目id
     */
    @GetMapping("/admin/column/getOptionalTopic/{columnId}")
    fun getOptionalTopic(
        @PathVariable columnId: Long?,
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): Result<Page<TopicVO>> {
        return columnTopicService.getOptionalTopic(columnId!!, pageNo, pageSize)
    }

    /**
     * 更新栏目信息
     * @param column 栏目对象
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/update")
    fun updateColumn(@RequestBody @Valid column: UpdateColumnDTO?): Result<*> {
        return columnService.updateColumn(column!!)
    }

    /**
     * 禁用/启用 栏目
     * @param columnId 栏目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/changeStatus/{columnId}")
    fun changeStatus(
        @PathVariable columnId: Long?,
        @RequestParam(defaultValue = "false") action: Boolean
    ): Result<*> {
        return columnService.changeStatus(columnId!!, action)
    }

    /**
     * 删除栏目(真删除)
     * @param columnId 栏目id
     * @return 响应为200表示成功
     */
    @DeleteMapping("/root/column/delete/{columnId}")
    fun deleteColumn(@PathVariable columnId: Long?): Result<*> {
        return columnService.deleteColumn(columnId!!)
    }

    /**
     * 删除一个栏目内的题目
     * @param columnId 栏目id
     * @param topicIds 题目id数组
     */
    @DeleteMapping("/admin/column/deleteTopic/{columnId}")
    fun removeTopic(
        @PathVariable columnId: Long?,
        @RequestBody @NotEmpty(message = "至少选择一个题目") topicIds: ArrayList<Long>?
    ) : Result<*> {
        return columnTopicService.removeTopic(columnId!!, topicIds!!)
    }
}
