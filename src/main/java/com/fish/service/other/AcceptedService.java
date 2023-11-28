package com.fish.service.other;

import com.fish.entity.pojo.Accepted;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface AcceptedService extends IService<Accepted> {
    int addAccepted(Accepted accepted);
    ArrayList<Accepted> getAccepts();
    ArrayList<Accepted> getMyAccepts(String userId);
    ArrayList<Accepted> getTopicAccepts(Long topicId);
}
