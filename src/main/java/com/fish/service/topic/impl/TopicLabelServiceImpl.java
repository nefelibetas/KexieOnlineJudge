package com.fish.service.topic.impl;

import static com.fish.entity.pojo.table.LabelTableDef.LABEL;
import static com.fish.entity.pojo.table.TopicLabelTableDef.TOPIC_LABEL;
import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.fish.entity.pojo.TopicLabel;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.LabelMapper;
import com.fish.mapper.TopicLabelMapper;
import com.fish.service.topic.TopicLabelService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class TopicLabelServiceImpl extends ServiceImpl<TopicLabelMapper, TopicLabel> implements TopicLabelService {
    @Resource
    LabelMapper labelMapper;
    @Transactional
    @Override
    public Result<?> addLabelToTopic(Long topicId, ArrayList<Long> labelIds) {
        ArrayList<TopicLabel> topicLabels = new ArrayList<>();
        labelIds.forEach(labelId -> topicLabels.add(new TopicLabel(topicId, labelId)));
        int i = mapper.insertBatch(topicLabels);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Override
    public Result<ArrayList<Label>> getOptionalLabels(Long topicId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .select(LABEL.ALL_COLUMNS).from(LABEL)
                .where(LABEL.LABEL_ID.notIn(
                        QueryWrapper.create()
                                .select(LABEL.LABEL_ID)
                                .from(LABEL)
                                .innerJoin(TOPIC_LABEL).on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
                                .and(TOPIC_LABEL.TOPIC_ID.eq(topicId))
                ));
        ArrayList<Label> labels = (ArrayList<Label>) labelMapper.selectListByQuery(wrapper);
        return ResultUtil.success(labels);
    }
    @Transactional
    @Override
    public Result<?> removeLabels(Long topicId, ArrayList<Long> labelsIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .select().from(TOPIC_LABEL)
                .where(TOPIC_LABEL.TOPIC_ID.eq(topicId))
                .and(TOPIC_LABEL.LABEL_ID.in(labelsIds));
        int i = mapper.deleteByQuery(wrapper);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
}
