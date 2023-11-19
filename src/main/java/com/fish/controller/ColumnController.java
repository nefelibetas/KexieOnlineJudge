package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.pojo.Column;
import com.fish.service.ColumnService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ColumnController {
    @Resource
    private ColumnService columnService;
    @PostMapping("/admin/column/add")
    public Result<?> addColumn(Column column) {
        return columnService.addColumn(column);
    }
    @PostMapping("/admin/column/adds")
    public Result<?> addLabels(@RequestBody ArrayList<Column> columns) {
        return columnService.addColumnBatch(columns);
    }
    @GetMapping("/column/gets")
    public Result<ArrayList<Column>> getColumns() {
        return columnService.getColumns();
    }
    @GetMapping("/column/get/{columnId}")
    public Result<Column> getColumn(@PathVariable("columnId")Long columnId) {
        return columnService.getColumn(columnId);
    }
    @PutMapping("/admin/column/update")
    public Result<?> updateColumn(Column column) {
        return columnService.updateColumn(column);
    }
    @DeleteMapping("/admin/column/delete/{columnId}")
    public Result<?> deleteColumn(@PathVariable Long columnId) {
        return columnService.deleteColumn(columnId);
    }
}
