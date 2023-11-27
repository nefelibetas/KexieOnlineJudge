package com.fish.controller

import com.fish.common.Result
import com.fish.entity.pojo.Column
import com.fish.entity.vo.ColumnVO
import com.fish.service.column.ColumnService
import com.fish.service.column.ColumnTopicService
import jakarta.annotation.Resource
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.*

@RestController
class ColumnController {
    @Resource
    var columnService: ColumnService? = null

    @Resource
    var columnTopicService: ColumnTopicService? = null

    /**
     * 新增栏目
     * @param column 栏目信息
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/column/add")
    fun addColumn(@RequestBody column: @Valid Column?): Result<*> {
        return columnService!!.addColumn(column)
    }

    /**
     * 批量增加栏目
     * @param columns 栏目数组
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/column/adds")
    fun addLabels(@RequestBody columns: @Valid ArrayList<Column?>?): Result<*> {
        return columnService!!.addColumnBatch(columns)
    }

    /**
     * 获取全部栏目
     * @return 全部栏目,包括栏目内的题目(题目包括其标签)
     */
    @GetMapping("/column/gets")
    fun getColumns(): Result<ArrayList<ColumnVO>> {
        return columnService!!.getColumns()
    }

    /**
     * 获取特定栏目
     * @param columnId 栏目id
     * @return 该栏目的全部信息
     */
    @GetMapping("/column/get/{columnId}")
    fun getColumn(@PathVariable("columnId") columnId: @NotNull(message = "题目Id未填写") Long?): Result<ColumnVO> {
        return columnService!!.getColumn(columnId)
    }

    /**
     * 更新栏目信息
     * @param column 栏目对象
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/update")
    fun updateColumn(@RequestBody column: @Valid Column?): Result<*> {
        return columnService!!.updateColumn(column)
    }

    /**
     * 删除栏目
     * @param columnId 栏目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/delete/{columnId}")
    fun deleteColumn(@PathVariable("columnId") columnId: @NotNull(message = "题目Id未填写") Long?): Result<*> {
        return columnService!!.deleteColumn(columnId)
    }

    /**
     * 删除栏目(真删除)
     * @param columnId 栏目id
     * @return 响应为200表示成功
     */
    @DeleteMapping("/root/column/delete/{columnId}")
    fun deleteColumnReality(@PathVariable("columnId") columnId: @NotNull(message = "题目Id未填写") Long?): Result<*> {
        return columnService!!.deleteColumnReality(columnId)
    }
}
