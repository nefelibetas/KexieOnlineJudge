package com.fish.service.topic.impl;

import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.mapper.LabelMapper;
import com.fish.service.topic.LabelService;
import com.fish.utils.ResultUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {
    @Transactional
    @Override
    public Result<?> addLabel(Label label) {
        int i = mapper.insert(label);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> addLabelBatch(ArrayList<Label> labels) {
        if (labels.isEmpty())
            throw new ServiceException(ServiceExceptionEnum.KEY_ARGUMENT_NOT_INPUT);
        int i = mapper.insertBatch(labels);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Override
    public Result<ArrayList<Label>> getLabels() {
        ArrayList<Label> labels = (ArrayList<Label>) mapper.selectAll();
        return ResultUtil.success(labels);
    }
    @Override
    public Result<Label> getLabel(Long labelId) {
        Label label = mapper.selectOneById(labelId);
        if (!Objects.isNull(label))
            return ResultUtil.success(label);
        throw new ServiceException(ServiceExceptionEnum.NOT_FOUND);
    }
    @Transactional
    @Override
    public Result<?> updateLabel(Label label) {
        int i = mapper.update(label);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
    @Transactional
    @Override
    public Result<?> deleteLabel(Long labelId) {
        int i = mapper.deleteById(labelId);
        if (i > 0)
            return ResultUtil.success();
        throw new ServiceException(ServiceExceptionEnum.OPERATE_ERROR);
    }
}