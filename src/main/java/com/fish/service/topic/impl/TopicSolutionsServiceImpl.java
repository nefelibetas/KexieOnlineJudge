package com.fish.service.topic.impl;

import com.fish.entity.pojo.TopicSolutions;
import com.fish.mapper.TopicSolutionsMapper;
import com.fish.service.topic.TopicSolutionsService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TopicSolutionsServiceImpl extends ServiceImpl<TopicSolutionsMapper, TopicSolutions> implements TopicSolutionsService {

}
