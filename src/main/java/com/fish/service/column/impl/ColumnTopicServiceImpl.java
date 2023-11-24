package com.fish.service.column.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.ColumnTopic;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.ColumnTopicMapper;
import com.fish.service.column.ColumnTopicService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.fish.entity.pojo.table.ColumnTopicTableDef.COLUMN_TOPIC;

@Service
public class ColumnTopicServiceImpl extends ServiceImpl<ColumnTopicMapper, ColumnTopic> implements ColumnTopicService {
    @Transactional
    @Override
    public Result<?> addRow(ColumnTopic columnTopic) {
        int i = mapper.insert(columnTopic);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> deleteRow(ColumnTopic columnTopic) {
        QueryWrapper wrapper = QueryWrapper.create()
                .select()
                .from(COLUMN_TOPIC)
                .where(COLUMN_TOPIC.COLUMN_ID.eq(columnTopic.getColumnId()).and(COLUMN_TOPIC.TOPIC_ID.eq(columnTopic.getTopicId())));
        int i = mapper.deleteByQuery(wrapper);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
}
