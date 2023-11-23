package com.fish.service.topic;

import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.fish.entity.pojo.TopicLabel;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface TopicLabelService extends IService<TopicLabel> {
    Result<?> addLabelToTopic(Long topicId, ArrayList<Long> labelIds);
    Result<ArrayList<Label>> getOptionalLabels(Long topicId);
    Result<?> removeLabels(Long topicId, ArrayList<Long> labelsIds);
}
