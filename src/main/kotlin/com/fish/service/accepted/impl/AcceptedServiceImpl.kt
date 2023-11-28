package com.fish.service.accepted.impl

import com.fish.entity.pojo.Accepted
import com.fish.mapper.AcceptedMapper
import com.fish.service.accepted.AcceptedService
import com.mybatisflex.spring.service.impl.ServiceImpl
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
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
