package com.fish.keXieOnlineJudge.service.topic

import com.fish.keXieOnlineJudge.entity.pojo.topic.Accepted
import com.mybatisflex.core.service.IService

interface AcceptedService : IService<Accepted> {
    fun addAccepted(accepted: Accepted): Int
    fun getAccepts(): ArrayList<Accepted>
    fun getMyAccepts(userId: String): ArrayList<Accepted>
    fun getTopicAccepts(topicId: Long): ArrayList<Accepted>
}
