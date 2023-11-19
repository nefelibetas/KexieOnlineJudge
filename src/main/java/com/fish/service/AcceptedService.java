package com.fish.service;

import com.fish.common.Result;
import com.fish.entity.pojo.Accepted;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface AcceptedService extends IService<Accepted> {
    int addAccepted(Accepted accepted);
    Result<ArrayList<Accepted>> getAccepts();
    Result<ArrayList<Accepted>> getMyAccepts(String userId);
    Result<ArrayList<Accepted>> getTopicAccepts(Long topicId);
}
