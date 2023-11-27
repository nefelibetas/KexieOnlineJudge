package com.fish.controller

import com.fish.common.Result
import com.fish.entity.pojo.Label
import com.fish.service.label.LabelService
import jakarta.annotation.Resource
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.*

@RestController
class LabelController {
    @Resource
    private val labelService: LabelService? = null

    /**
     * 新增标签接口
     * @param label 标签
     * @return 响应code为200为成功
     */
    @PostMapping("/admin/label/add")
    fun addLabel(@RequestBody label: @Valid Label?): Result<*> {
        return labelService!!.addLabel(label)
    }

    /**
     * 批量增加标签
     * @param labels 标签数组
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/label/adds")
    fun addLabels(@RequestBody labels: @Valid ArrayList<Label?>?): Result<*> {
        return labelService!!.addLabelBatch(labels)
    }

    /**
     * 获取id对应的标签
     * @param labelId 标签id
     * @return id对应标签
     */
    @GetMapping("/label/get/{labelId}")
    fun getLabel(@PathVariable("labelId") labelId: @NotNull(message = "标签Id未填写") Long?): Result<Label> {
        return labelService!!.getLabel(labelId)
    }

    @get:GetMapping("/label/gets")
    val labels: Result<ArrayList<Label>>
        /**
         * 获取全部标签
         * @return 全部标签
         */
        get() = labelService!!.getLabels()

    /**
     * 修改标签
     * @param label 要修改的标签
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/label/update/{labelId}")
    fun updateLabel(@RequestBody label: @Valid Label?): Result<*> {
        return labelService!!.updateLabel(label)
    }

    /**
     * 真删除标签
     * @param labelId 标签Id
     * @return 响应code为200表示成功
     */
    @DeleteMapping("/root/label/delete/{labelId}")
    fun deleteLabel(@PathVariable labelId: @NotNull(message = "标签Id未填写") Long?): Result<*> {
        return labelService!!.deleteLabel(labelId)
    }
}
