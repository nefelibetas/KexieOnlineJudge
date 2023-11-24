package com.fish.service.label;

import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface LabelService extends IService<Label> {
    /**
     * 新增id
     * @param label 标签
     * @return 封装好的响应信息
     */
    Result<?> addLabel(Label label);
    /**
     * 批量增加标签
     * @param labels 标签数组
     * @return 封装好的响应信息
     */
    Result<?> addLabelBatch(ArrayList<Label> labels);
    /**
     * 获取全部标签
     * @return 封装好的响应信息
     */
    Result<ArrayList<Label>> getLabels();
    /**
     * 获取id对应的标签
     * @param labelId 标签id
     * @return 封装好的响应信息
     */
    Result<Label> getLabel(Long labelId);
    /**
     * 更新标签内容
     * @param label 标签，需要校验标签对象内id是否为空
     * @return 封装好的响应信息
     */
    Result<?> updateLabel(Label label);
    /**
     * 真删除标签，需要root权限。<br/>
     * 在删除前,需要清除其他表中的记录
     * @param labelId 标签id
     * @return 封装好的响应信息
     */
    Result<?> deleteLabel(Long labelId);
}
