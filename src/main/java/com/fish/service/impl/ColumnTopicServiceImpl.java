package com.fish.service.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.ColumnTopic;
import com.fish.entity.pojo.Topic;
import com.fish.entity.vo.TopicVO;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.ColumnTopicMapper;
import com.fish.mapper.TopicMapper;
import com.fish.service.ColumnTopicService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.fish.entity.pojo.table.ColumnTopicTableDef.COLUMN_TOPIC;
import static com.fish.entity.pojo.table.TopicTableDef.TOPIC;

@Service
public class ColumnTopicServiceImpl extends ServiceImpl<ColumnTopicMapper, ColumnTopic> implements ColumnTopicService {
    @Resource
    private TopicMapper topicMapper;
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
    @Override
    public Result<ArrayList<TopicVO>> getAllTopicInColumn(Long columnId) {
        QueryWrapper wrapper = QueryWrapper.create().select()
                .from(TOPIC)
                .innerJoin(COLUMN_TOPIC).on(TOPIC.TOPIC_ID.eq(COLUMN_TOPIC.TOPIC_ID))
                .where(COLUMN_TOPIC.COLUMN_ID.eq(columnId));
        List<Topic> topics = topicMapper.selectListByQuery(wrapper);
        ArrayList<TopicVO> topicVOS = new ArrayList<>();
        topics.forEach(topic -> topicVOS.add(new TopicVO(topic)));
        return ResultUtil.success(topicVOS);
    }
}
