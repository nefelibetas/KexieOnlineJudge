package com.fish.service.topic.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.Topic;
import com.fish.entity.vo.TopicVO;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.TopicMapper;
import com.fish.service.topic.TopicService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.fish.entity.pojo.table.TopicTableDef.TOPIC;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
    @Transactional
    @Override
    public Result<?> addTopic(Topic topic) {
        int i = mapper.insert(topic);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> addTopicBatch(ArrayList<Topic> topics) {
        int i = mapper.insertBatch(topics);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> deleteTopic(Long topicId) {
        int i = mapper.deleteById(topicId);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> updateTopic(Long topicId, Topic topic) {
        QueryWrapper wrapper = QueryWrapper.create().where(TOPIC.TOPIC_ID.eq(topicId));
        int i = mapper.updateByQuery(topic, wrapper);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Override
    public Result<ArrayList<TopicVO>> getTopics() {
        ArrayList<TopicVO> topicVOS = new ArrayList<>();
        QueryWrapper wrapper = QueryWrapper.create().where(TOPIC.ENABLED.eq(true));
        List<Topic> topics = mapper.selectListByQuery(wrapper);
        topics.forEach(topic -> topicVOS.add(new TopicVO(topic)));
        return ResultUtil.success(topicVOS);
    }
    @Override
    public Result<TopicVO> getTopic(Long topicId) {
        QueryWrapper wrapper = QueryWrapper.create().select().from(TOPIC).where(TOPIC.TOPIC_ID.eq(topicId));
        Topic topic = mapper.selectOneByQuery(wrapper);
        return ResultUtil.success(new TopicVO(topic));
    }
}