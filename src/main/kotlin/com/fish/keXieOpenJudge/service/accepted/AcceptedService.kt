package com.fish.keXieOpenJudge.service.accepted

import com.fish.keXieOpenJudge.entity.pojo.assessment.Accepted
import com.mybatisflex.core.service.IService

interface AcceptedService : IService<Accepted> {
    fun addAccepted(accepted: Accepted): Int
    fun getAccepts(): ArrayList<Accepted>
    fun getMyAccepts(userId: String): ArrayList<Accepted>
    fun getTopicAccepts(topicId: Long): ArrayList<Accepted>
}
