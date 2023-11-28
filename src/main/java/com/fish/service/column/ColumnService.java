package com.fish.service.column;

import com.fish.common.Result;
import com.fish.entity.pojo.Column;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface ColumnService extends IService<Column> {
    Result<?> addColumn(Column column);
    Result<?> addColumnBatch(ArrayList<Column> columns);
    Result<ArrayList<Column>> getColumns();
    Result<Column> getColumn(Long columnId);
    Result<?> updateColumn(Column column);
    Result<?> deleteColumn(Long columnId);
    Result<?> deleteColumnReality(Long columnId);
}
