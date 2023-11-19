package com.fish.service.impl;

import com.fish.entity.pojo.Accepted;
import com.fish.mapper.AcceptedMapper;
import com.fish.service.AcceptedService;
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
    public ArrayList<Accepted> getAccepts() {
        return (ArrayList<Accepted>) mapper.selectAll();
    }
    @Override
    public ArrayList<Accepted> getMyAccepts(String userId) {
        return mapper.getMyAccepts(userId);
    }
    @Override
    public ArrayList<Accepted> getTopicAccepts(Long topicId) {
        return mapper.getTopicAccepts(topicId);
    }
}
