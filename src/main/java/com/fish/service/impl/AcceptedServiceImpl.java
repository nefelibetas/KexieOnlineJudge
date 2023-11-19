package com.fish.service.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.Accepted;
import com.fish.mapper.AcceptedMapper;
import com.fish.service.AcceptedService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AcceptedServiceImpl extends ServiceImpl<AcceptedMapper, Accepted> implements AcceptedService {
    @Override
    public int addAccepted(Accepted accepted) {
        return mapper.insert(accepted);
    }
    @Override
    public Result<ArrayList<Accepted>> getAccepts() {
        return ResultUtil.success((ArrayList<Accepted>) mapper.selectAll());
    }
    @Override
    public Result<ArrayList<Accepted>> getMyAccepts(String userId) {
        return ResultUtil.success(mapper.getMyAccepts(userId));
    }
    @Override
    public Result<ArrayList<Accepted>> getTopicAccepts(Long topicId) {
        return ResultUtil.success(mapper.getTopicAccepts(topicId));
    }
}