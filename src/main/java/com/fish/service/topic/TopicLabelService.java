package com.fish.service.topic;

import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.fish.entity.pojo.TopicLabel;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface TopicLabelService extends IService<TopicLabel> {
    /**
     * 给题目添加标签
     * @param topicId 题目id
     * @param labelIds 一些标签id
     * @return 封装好的响应信息
     */
    Result<?> addLabelToTopic(Long topicId, ArrayList<Long> labelIds);
    /**
     * 获取可选的id
     * @param topicId 题目id
     * @return 获取该题目id可选的标签
     */
    Result<ArrayList<Label>> getOptionalLabels(Long topicId);
    /**
     * 给题目批量移除标签
     * @param topicId 题目id
     * @param labelsIds 一些标签id
     * @return 封装好的响应信息
     */
    Result<?> removeLabels(Long topicId, ArrayList<Long> labelsIds);
}
