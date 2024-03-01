package com.fish.kexieOnlineJudge.service.exam.impl

import com.fish.kexieOnlineJudge.common.Result
import com.fish.kexieOnlineJudge.entity.dto.exam.InsertExamDTO
import com.fish.kexieOnlineJudge.entity.dto.exam.UpdateExamDTO
import com.fish.kexieOnlineJudge.entity.pojo.exam.Exam
import com.fish.kexieOnlineJudge.entity.pojo.exam.table.ExamParticipateTableDef.EXAM_PARTICIPATE
import com.fish.kexieOnlineJudge.entity.pojo.exam.table.ExamTableDef.EXAM
import com.fish.kexieOnlineJudge.entity.pojo.exam.table.ExamTopicTableDef.EXAM_TOPIC
import com.fish.kexieOnlineJudge.entity.pojo.label.table.LabelTableDef.LABEL
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.TopicLabelTableDef.TOPIC_LABEL
import com.fish.kexieOnlineJudge.entity.pojo.topic.table.TopicTableDef.TOPIC
import com.fish.kexieOnlineJudge.entity.vo.ExamVO
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import com.fish.kexieOnlineJudge.mapper.exam.ExamMapper
import com.fish.kexieOnlineJudge.mapper.exam.ExamParticipateMapper
import com.fish.kexieOnlineJudge.service.exam.ExamService
import com.fish.kexieOnlineJudge.service.exam.ExamTopicService
import com.fish.kexieOnlineJudge.utils.ResultUtil.success
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.update.UpdateChain
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExamServiceImpl(val examParticipateMapper: ExamParticipateMapper, val examTopicService: ExamTopicService): ServiceImpl<ExamMapper, Exam>(), ExamService {
    override fun getExams(pageNo: Int, pageSize: Int): Result<Page<ExamVO>> {
        val wrapper = QueryWrapper.create()
            .select(EXAM.ALL_COLUMNS, TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS)
            .from(EXAM)
            .leftJoin<QueryWrapper>(EXAM_TOPIC)
            .on(EXAM.EXAM_ID.eq(EXAM_TOPIC.EXAM_ID))
            .and(EXAM.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC)
            .on(TOPIC.TOPIC_ID.eq(EXAM_TOPIC.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC_LABEL.TOPIC_ID.eq(TOPIC.TOPIC_ID))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
        val examVOList = mapper.paginateWithRelationsAs(Page.of(pageNo, pageSize), wrapper, ExamVO::class.java)
        for (record in examVOList.records) {
            val queryWrapper = QueryWrapper.create().from(EXAM_PARTICIPATE).where(EXAM_PARTICIPATE.EXAM_ID.eq(record.examId))
            record.participantNumber = examParticipateMapper.selectCountByQuery(queryWrapper)
        }
        return success(examVOList)
    }

    override fun getExamById(examId: Long): Result<ExamVO> {
        val wrapper = QueryWrapper.create()
            .select(EXAM.ALL_COLUMNS, TOPIC.ALL_COLUMNS, LABEL.ALL_COLUMNS)
            .from(EXAM)
            .leftJoin<QueryWrapper>(EXAM_TOPIC)
            .on(EXAM.EXAM_ID.eq(EXAM_TOPIC.EXAM_ID))
            .and(EXAM.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC)
            .on(TOPIC.TOPIC_ID.eq(EXAM_TOPIC.TOPIC_ID))
            .and(TOPIC.ENABLED.eq(true))
            .leftJoin<QueryWrapper>(TOPIC_LABEL)
            .on(TOPIC_LABEL.TOPIC_ID.eq(TOPIC.TOPIC_ID))
            .leftJoin<QueryWrapper>(LABEL)
            .on(LABEL.LABEL_ID.eq(TOPIC_LABEL.LABEL_ID))
            .where(EXAM.EXAM_ID.eq(examId))
        val examVO = mapper.selectOneWithRelationsByQueryAs(wrapper, ExamVO::class.java)
        val queryWrapper = QueryWrapper.create().from(EXAM_PARTICIPATE).where(EXAM_PARTICIPATE.EXAM_ID.eq(examId))
        examVO?.let {
            examVO.participantNumber = examParticipateMapper.selectCountByQuery(queryWrapper)
            return success(examVO)
        }
        throw ServiceException(ServiceExceptionEnum.NOT_FOUND)
    }

    @Transactional
    override fun addExam(insertExamDTO: InsertExamDTO): Result<*> {
        val i = mapper.insertExamDTO(insertExamDTO)
        val (code, _, _) = examTopicService.addTopicsToExam(insertExamDTO.examId!!, insertExamDTO.examTopics!!)
        if ((i >0) and (code == 200))
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun updateExam(updateExamDTO: UpdateExamDTO): Result<*> {
        val i = mapper.updateExamDTO(updateExamDTO)
        if (i > 0)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

    @Transactional
    override fun changeStatus(examId: Long, action: Boolean): Result<*> {
        val update = UpdateChain.of(Exam::class.java)
            .set(Exam::enabled.column, action)
            .where(Exam::examId.eq(examId))
            .update()
        if (update)
            return success<Any>()
        throw ServiceException(ServiceExceptionEnum.OPERATE_ERROR)
    }

}