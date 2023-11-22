package com.fish.service.topic;

import com.fish.common.Result;
import com.fish.entity.pojo.Topic;
import com.fish.entity.vo.TopicVO;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface TopicService extends IService<Topic> {
    Result<?> addTopic(Topic topic);
    Result<?> addTopicBatch(ArrayList<Topic> topics);
    Result<?> deleteTopic(Long topicId);
    Result<?> updateTopic(Long topicId, Topic topicDTO);
    Result<ArrayList<TopicVO>> getTopics();
    Result<TopicVO> getTopic(Long topicId);
}
