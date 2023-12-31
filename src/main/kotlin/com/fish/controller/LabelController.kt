package com.fish.controller

import com.fish.common.Result
import com.fish.entity.dto.InsertLabelsDTO
import com.fish.entity.pojo.Label
import com.fish.service.label.LabelService
import com.mybatisflex.core.paginate.Page
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
class LabelController(val labelService: LabelService) {
    /**
     * 新增标签接口
     * @param label 标签
     * @return 响应code为200为成功
     */
    @PostMapping("/admin/label/add")
    fun addLabel(@RequestBody @Valid label: Label?): Result<*> {
        return labelService.addLabel(label!!)
    }

    /**
     * 批量增加标签
     * @param labels 标签数组
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/label/adds")
    fun addLabels(@RequestBody @Valid labels: InsertLabelsDTO?): Result<*> {
        return labelService.addLabelBatch(labels!!)
    }

    /**
     * 获取id对应的标签
     * @param labelId 标签id
     * @return id对应标签
     */
    @GetMapping("/label/get/{labelId}")
    fun getLabel(@PathVariable("labelId") labelId: Long?): Result<Label> {
        return labelService.getLabel(labelId!!)
    }

    /**
     * 获取全部标签
     * @return 全部标签
     */
    @GetMapping("/label/gets")
    fun getLabels(
        @RequestParam(defaultValue = "1") pageNo: Int,
        @RequestParam(defaultValue = "20") pageSize: Int
    ): Result<Page<Label>> {
        return labelService.getLabels(pageNo, pageSize)
    }

    /**
     * 修改标签
     * @param label 要修改的标签
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/label/update")
    fun updateLabel(@RequestBody @Valid label: Label?): Result<*> {
        return labelService.updateLabel(label!!)
    }

    /**
     * 真删除标签
     * @param labelId 标签Id
     * @return 响应code为200表示成功
     */
    @DeleteMapping("/root/label/delete/{labelId}")
    fun deleteLabel(@PathVariable labelId: Long?): Result<*> {
        return labelService.deleteLabel(labelId!!)
    }
}
