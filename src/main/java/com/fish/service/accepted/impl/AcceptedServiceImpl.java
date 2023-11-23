package com.fish.service.accepted.impl;

import com.fish.entity.pojo.Accepted;
import com.fish.mapper.AcceptedMapper;
import com.fish.service.accepted.AcceptedService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AcceptedServiceImpl extends ServiceImpl<AcceptedMapper, Accepted> implements AcceptedService {
    @Override
    public int addAccepted(@Valid Accepted accepted) {
        return mapper.insert(accepted);
    }
    @Override
    public ArrayList<Accepted> getAccepts() {
        return (ArrayList<Accepted>) mapper.selectAll();
    }
    @Override
    public ArrayList<Accepted> getMyAccepts(@NotBlank(message = "用户Id未填写") String userId) {
        return mapper.getMyAccepts(userId);
    }
    @Override
    public ArrayList<Accepted> getTopicAccepts(@NotNull(message = "题目Id未填写") Long topicId) {
        return mapper.getTopicAccepts(topicId);
    }
}
