package com.fish.service.column.impl

import com.fish.entity.pojo.ColumnTopic
import com.fish.mapper.ColumnTopicMapper
import com.fish.service.column.ColumnTopicService
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class ColumnTopicServiceImpl : ServiceImpl<ColumnTopicMapper, ColumnTopic>(), ColumnTopicService
