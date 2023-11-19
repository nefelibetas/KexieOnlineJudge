package com.fish.service;

import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface LabelService extends IService<Label> {
    Result<?> addLabel(Label label);
    Result<?> addLabelBatch(ArrayList<Label> labels);
    Result<ArrayList<Label>> getLabels();
    Result<Label> getLabel(Long labelId);
    Result<?> updateLabel(Label label);
    Result<?> deleteLabel(Long labelId);
}
