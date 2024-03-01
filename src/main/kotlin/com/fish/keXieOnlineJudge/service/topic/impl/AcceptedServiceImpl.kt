package com.fish.keXieOnlineJudge.service.topic.impl

import com.fish.keXieOnlineJudge.entity.pojo.topic.Accepted
import com.fish.keXieOnlineJudge.mapper.topic.AcceptedMapper
import com.fish.keXieOnlineJudge.service.topic.AcceptedService
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class AcceptedServiceImpl : ServiceImpl<AcceptedMapper, Accepted>(), AcceptedService {
    override fun addAccepted(accepted: Accepted): Int {
        return mapper!!.insert(accepted)
    }

    override fun getAccepts(): ArrayList<Accepted> {
        return mapper!!.selectAll() as ArrayList<Accepted>
    }

    override fun getMyAccepts(userId: String): ArrayList<Accepted> {
        return mapper!!.getMyAccepts(userId)
    }

    override fun getTopicAccepts(topicId: Long): ArrayList<Accepted> {
        return mapper!!.getTopicAccepts(topicId)
    }
}
