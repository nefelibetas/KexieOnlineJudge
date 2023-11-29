package com.fish.controller

import com.fish.common.Result
import com.fish.entity.pojo.Column
import com.fish.entity.vo.ColumnVO
import com.fish.entity.vo.TopicVO
import com.fish.service.column.ColumnService
import com.fish.service.column.ColumnTopicService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.*

@RestController
class ColumnController(val columnService: ColumnService, val columnTopicService: ColumnTopicService) {
    /**
     * 新增栏目
     * @param column 栏目信息
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/column/add")
    fun addColumn(@RequestBody column: @Valid Column?): Result<*> {
        return columnService.addColumn(column!!)
    }
    /**
     * 批量增加栏目
     * @param columns 栏目数组
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/column/adds")
    fun addColumns(@RequestBody columns: @NotEmpty(message = "至少包含一个栏目") ArrayList<@Valid Column>?): Result<*> {
        return columnService.addColumnBatch(columns!!)
    }

    /**
     * 给栏目批量增加题目
     * @param columnId 栏目id
     * @param topicIds 要添加的题目的id素组
     */
    @PostMapping("/admin/column/addTopic/{columnId}")
    fun addTopicsToColumn(
        @PathVariable("columnId") columnId: @NotNull(message = "栏目id未填写") Long?,
        @RequestBody topicIds : @NotEmpty(message = "至少选一题") ArrayList<@NotNull(message = "题目id未填写") Long>?
    ) : Result<*> {
        return columnTopicService.addTopicToColumn(columnId!!, topicIds!!)
    }

    /**
     * 获取全部栏目
     * @return 全部栏目,包括栏目内的题目(题目包括其标签)
     */
    @GetMapping("/column/gets")
    fun getColumns(): Result<ArrayList<ColumnVO>> {
        return columnService.getColumns()
    }

    /**
     * 获取特定栏目
     * @param columnId 栏目id
     * @return 该栏目的全部信息
     */
    @GetMapping("/column/get/{columnId}")
    fun getColumn(@PathVariable("columnId") columnId: @NotNull(message = "题目Id未填写") Long?): Result<ColumnVO> {
        return columnService.getColumn(columnId!!)
    }

    /**
     * 获取该栏目还能添加的题目
     * @param columnId 栏目id
     */
    @GetMapping("/admin/column/getOptionalTopic/{columnId}")
    fun getOptionalTopic(@PathVariable("columnId") columnId: @NotNull(message = "栏目id不能为空") Long?) : Result<ArrayList<TopicVO>> {
        return columnTopicService.getOptionalTopic(columnId!!)
    }

    /**
     * 更新栏目信息
     * @param column 栏目对象
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/update")
    fun updateColumn(@RequestBody column: @Valid Column?): Result<*> {
        return columnService.updateColumn(column!!)
    }

    /**
     * 禁用栏目
     * @param columnId 栏目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/disable/{columnId}")
    fun disableColumn(@PathVariable("columnId") columnId: @NotNull(message = "题目Id未填写") Long?): Result<*> {
        return columnService.disableColumn(columnId!!)
    }

    /**
     * 启用栏目
     * @param columnId 栏目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/enable/{columnId}")
    fun enableColumn(@PathVariable("columnId") columnId: @NotNull(message = "题目Id未填写") Long?): Result<*> {
        return columnService.enableColumn(columnId!!)
    }

    /**
     * 删除栏目(真删除)
     * @param columnId 栏目id
     * @return 响应为200表示成功
     */
    @DeleteMapping("/root/column/delete/{columnId}")
    fun deleteColumn(@PathVariable("columnId") columnId: @NotNull(message = "题目Id未填写") Long?): Result<*> {
        return columnService.deleteColumn(columnId!!)
    }

    /**
     * 删除一个栏目内的题目
     * @param columnId 栏目id
     * @param topicIds 题目id数组
     */
    @DeleteMapping("/admin/column/deleteTopic/{columnId}")
    fun removeTopic(
        @PathVariable("columnId") columnId: @NotNull(message = "题目id不能为空") Long?,
        @RequestBody topicIds: @NotEmpty(message = "至少选择一个题目") ArrayList<@NotNull(message = "题目id不能为空") Long>?
    ) : Result<*> {
        return columnTopicService.removeTopic(columnId!!, topicIds!!)
    }
}
