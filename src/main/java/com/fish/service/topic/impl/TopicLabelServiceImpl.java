package com.fish.service.topic.impl;

import com.fish.entity.pojo.TopicLabel;
import com.fish.mapper.TopicLabelMapper;
import com.fish.service.topic.TopicLabelService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TopicLabelServiceImpl extends ServiceImpl<TopicLabelMapper, TopicLabel> implements TopicLabelService {

}
