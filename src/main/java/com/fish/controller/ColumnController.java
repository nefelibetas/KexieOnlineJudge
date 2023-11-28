package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.pojo.Column;
import com.fish.service.ColumnService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ColumnController {
    @Resource
    private ColumnService columnService;
    @PostMapping("/admin/column/add")
    public Result<?> addColumn(
            @Valid
            @RequestBody Column column) {
        return columnService.addColumn(column);
    }
    @PostMapping("/admin/column/adds")
    public Result<?> addLabels(
            @Valid
            @RequestBody ArrayList<Column> columns) {
        return columnService.addColumnBatch(columns);
    }
    @GetMapping("/column/gets")
    public Result<ArrayList<Column>> getColumns() {
        return columnService.getColumns();
    }
    @GetMapping("/column/get/{columnId}")
    public Result<Column> getColumn(
            @PathVariable("columnId")
            @NotNull(message = "题目Id未填写") Long columnId) {
        return columnService.getColumn(columnId);
    }
    @PutMapping("/admin/column/update")
    public Result<?> updateColumn(
            @Valid
            @RequestBody Column column) {
        return columnService.updateColumn(column);
    }
    @DeleteMapping("/admin/column/delete/{columnId}")
    public Result<?> deleteColumn(
            @PathVariable("columnId")
            @NotNull(message = "题目Id未填写") Long columnId) {
        return columnService.deleteColumn(columnId);
    }
    @DeleteMapping("/root/column/delete/{columnId}")
    public Result<?> deleteColumnReality(
            @PathVariable("columnId")
            @NotNull(message = "题目Id未填写") Long columnId) {
        return columnService.deleteColumnReality(columnId);
    }
}
