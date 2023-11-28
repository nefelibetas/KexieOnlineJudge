package com.fish.service.column;

import com.fish.common.Result;
import com.fish.entity.pojo.ColumnTopic;
import com.fish.entity.vo.ColumnVO;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface ColumnTopicService extends IService<ColumnTopic> {
    Result<?> addRow(ColumnTopic columnTopic);
    Result<?> deleteRow(ColumnTopic columnTopic);
}
