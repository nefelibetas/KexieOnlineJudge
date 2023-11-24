package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.pojo.Column;
import com.fish.entity.vo.ColumnVO;
import com.fish.service.column.ColumnService;
import com.fish.service.column.ColumnTopicService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ColumnController {
    @Resource
    ColumnService columnService;
    @Resource
    ColumnTopicService columnTopicService;
    /**
     * 新增栏目
     * @param column 栏目信息
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/column/add")
    public Result<?> addColumn(
            @Valid
            @RequestBody Column column) {
        return columnService.addColumn(column);
    }
    /**
     * 批量增加栏目
     * @param columns 栏目数组
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/column/adds")
    public Result<?> addLabels(
            @Valid
            @RequestBody ArrayList<Column> columns) {
        return columnService.addColumnBatch(columns);
    }
    /**
     * 获取全部栏目
     * @return 全部栏目,包括栏目内的题目(题目包括其标签)
     */
    @GetMapping("/column/gets")
    public Result<ArrayList<ColumnVO>> getColumns() {
        return columnService.getColumns();
    }
    /**
     * 获取特定栏目
     * @param columnId 栏目id
     * @return 该栏目的全部信息
     */
    @GetMapping("/column/get/{columnId}")
    public Result<ColumnVO> getColumn(@PathVariable("columnId") @NotNull(message = "题目Id未填写") Long columnId) {
        return columnService.getColumn(columnId);
    }
    /**
     * 更新栏目信息
     * @param column 栏目对象
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/update")
    public Result<?> updateColumn(@Valid @RequestBody Column column) {
        return columnService.updateColumn(column);
    }
    /**
     * 删除栏目
     * @param columnId 栏目id
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/column/delete/{columnId}")
    public Result<?> deleteColumn(@PathVariable("columnId") @NotNull(message = "题目Id未填写") Long columnId) {
        return columnService.deleteColumn(columnId);
    }
    /**
     * 删除栏目(真删除)
     * @param columnId 栏目id
     * @return 响应为200表示成功
     */
    @DeleteMapping("/root/column/delete/{columnId}")
    public Result<?> deleteColumnReality(@PathVariable("columnId") @NotNull(message = "题目Id未填写") Long columnId) {
        return columnService.deleteColumnReality(columnId);
    }
}
