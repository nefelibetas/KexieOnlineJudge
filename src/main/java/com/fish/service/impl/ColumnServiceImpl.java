package com.fish.service.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.Column;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.ColumnMapper;
import com.fish.service.ColumnService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements ColumnService {
    @Override
    public Result<?> addColumn(Column column) {
        if (!StringUtils.hasLength(column.getColumnName()) || !StringUtils.hasLength(column.getColumnDescribe()))
            throw new ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT);
        int i = mapper.insert(column);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
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
        if (Objects.isNull(columnId))
            throw new ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT);
        return ResultUtil.success(mapper.getColumn(columnId));
    }
    @Override
    public Result<?> updateColumn(Column column) {
        if (!StringUtils.hasLength(column.getColumnName()) && !StringUtils.hasLength(column.getColumnDescribe()))
            throw new ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT);
        int i = mapper.update(column);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Override
    public Result<?> deleteColumn(Long columnId) {
        if (Objects.isNull(columnId))
            throw new ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT);
        int i = mapper.deleteColumn(columnId);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
}
