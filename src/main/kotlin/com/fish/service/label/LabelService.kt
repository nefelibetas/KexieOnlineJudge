package com.fish.service.label

import com.fish.common.Result
import com.fish.entity.dto.InsertLabelsDTO
import com.fish.entity.pojo.Label
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService

interface LabelService : IService<Label> {
    /**
     * 新增id
     * @param label 标签
     * @return 封装好的响应信息
     */
    fun addLabel(label: Label): Result<*>

    /**
     * 批量增加标签
     * @param labels 标签数组
     * @return 封装好的响应信息
     */
    fun addLabelBatch(labels: InsertLabelsDTO): Result<*>

    /**
     * 获取全部标签
     * @return 封装好的响应信息
     */
    fun getLabels(pageNo: Int, pageSize: Int): Result<Page<Label>>

    /**
     * 获取id对应的标签
     * @param labelId 标签id
     * @return 封装好的响应信息
     */
    fun getLabel(labelId: Long): Result<Label>

    /**
     * 更新标签内容
     * @param label 标签，需要校验标签对象内id是否为空
     * @return 封装好的响应信息
     */
    fun updateLabel(label: Label): Result<*>

    /**
     * 真删除标签，需要root权限。<br></br>
     * 在删除前,需要清除其他表中的记录
     * @param labelId 标签id
     * @return 封装好的响应信息
     */
    fun deleteLabel(labelId: Long): Result<*>
}
