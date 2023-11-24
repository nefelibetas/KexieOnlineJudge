package com.fish.service.topic;

import com.fish.common.Result;
import com.fish.entity.pojo.Topic;
import com.fish.entity.vo.TopicVO;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface TopicService extends IService<Topic> {
    /**
     * 新增题目
     * @param topic 题目
     * @return 封装好的响应信息
     */
    Result<?> addTopic(Topic topic);
    /**
     * 批量新增题目
     * @param topics 题目数组
     * @return 封装好的响应信息
     */
    Result<?> addTopicBatch(ArrayList<Topic> topics);
    /**
     * 删除对应题目
     * @param topicId 题目id
     * @return 封装好的响应信息
     */
    Result<?> deleteTopic(Long topicId);
    /**
     * 更新题目信息
     * @param topicId 题目id
     * @param topic 题目传输对象
     * @return 封装好的响应信息
     */
    Result<?> updateTopic(Long topicId, Topic topic);
    /**
     * 获取所有题目
     * @return 封装好的响应信息
     */
    Result<ArrayList<TopicVO>> getTopics();
    /**
     * 获取id对应的题目
     * @param topicId 题目id
     * @return 封装好的响应信息
     */
    Result<TopicVO> getTopic(Long topicId);
}
