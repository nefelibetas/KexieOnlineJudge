package com.fish.service.column.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.ColumnTopic;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.ColumnTopicMapper;
import com.fish.service.column.ColumnTopicService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.fish.entity.pojo.table.ColumnTopicTableDef.COLUMN_TOPIC;

@Service
public class ColumnTopicServiceImpl extends ServiceImpl<ColumnTopicMapper, ColumnTopic> implements ColumnTopicService {

}
