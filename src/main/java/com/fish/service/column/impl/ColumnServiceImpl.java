package com.fish.service.column.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.Column;
import com.fish.entity.vo.ColumnVO;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.ColumnMapper;
import com.fish.service.column.ColumnService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;

import static com.fish.entity.pojo.table.ColumnTableDef.COLUMN;
import static com.fish.entity.pojo.table.ColumnTopicTableDef.COLUMN_TOPIC;
import static com.fish.entity.pojo.table.LabelTableDef.LABEL;
import static com.fish.entity.pojo.table.TopicLabelTableDef.TOPIC_LABEL;
import static com.fish.entity.pojo.table.TopicTableDef.TOPIC;

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
    public Result<ArrayList<ColumnVO>> getColumns() {
        QueryWrapper wrapper = QueryWrapper.create()
                .select(COLUMN.ALL_COLUMNS, TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS).from(COLUMN)
                .innerJoin(COLUMN_TOPIC).on(COLUMN_TOPIC.COLUMN_ID.eq(COLUMN.COLUMN_ID)).and(COLUMN.ENABLED.eq(true))
                .innerJoin(TOPIC).on(TOPIC.TOPIC_ID.eq(COLUMN_TOPIC.TOPIC_ID)).and(TOPIC.ENABLED.eq(true))
                .innerJoin(TOPIC_LABEL).on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
                .innerJoin(LABEL).on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID));
        return ResultUtil.success((ArrayList<ColumnVO>) mapper.selectListByQueryAs(wrapper, ColumnVO.class));
    }
    @Override
    public Result<ColumnVO> getColumn(Long columnId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .select(COLUMN.ALL_COLUMNS, TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS).from(COLUMN)
                .innerJoin(COLUMN_TOPIC).on(COLUMN_TOPIC.COLUMN_ID.eq(COLUMN.COLUMN_ID)).and(COLUMN.ENABLED.eq(true)).and(COLUMN.COLUMN_ID.eq(columnId))
                .innerJoin(TOPIC).on(TOPIC.TOPIC_ID.eq(COLUMN_TOPIC.TOPIC_ID)).and(TOPIC.ENABLED.eq(true))
                .innerJoin(TOPIC_LABEL).on(TOPIC.TOPIC_ID.eq(TOPIC_LABEL.TOPIC_ID))
                .innerJoin(LABEL).on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID));
        ColumnVO columnVO = mapper.selectOneByQueryAs(wrapper, ColumnVO.class);
        return ResultUtil.success(columnVO);
    }
    @Transactional
    @Override
    public Result<?> updateColumn(Column column) {
        if (Objects.isNull(column.getColumnId()))
            throw new ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT);
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
