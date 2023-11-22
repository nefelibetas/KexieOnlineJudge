package com.fish.service.column.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.Column;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.ColumnMapper;
import com.fish.service.column.ColumnService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements ColumnService {
    @Transactional
    @Override
    public Result<?> addColumn(Column column) {
        int i = mapper.insert(column);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> addColumnBatch(ArrayList<Column> columns) {
        if (columns.isEmpty())
            throw new ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT);
        int i = mapper.insertBatch(columns);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Override
    public Result<ArrayList<Column>> getColumns() {
        return ResultUtil.success(mapper.getColumns());
    }
    @Override
    public Result<Column> getColumn(Long columnId) {
        return ResultUtil.success(mapper.getColumn(columnId));
    }
    @Transactional
    @Override
    public Result<?> updateColumn(Column column) {
        int i = mapper.update(column);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> deleteColumn(Long columnId) {
        int i = mapper.deleteColumn(columnId);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> deleteColumnReality(Long columnId) {
        int i = mapper.deleteById(columnId);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
}
