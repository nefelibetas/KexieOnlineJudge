package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.fish.service.label.LabelService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LabelController {
    @Resource
    private LabelService labelService;
    /**
     * 新增标签接口
     * @param label 标签
     * @return 响应code为200为成功
     */
    @PostMapping("/admin/label/add")
    public Result<?> addLabel(@RequestBody @Valid Label label) {
        return labelService.addLabel(label);
    }
    /**
     * 批量增加标签
     * @param labels 标签数组
     * @return 响应code为200表示成功
     */
    @PostMapping("/admin/label/adds")
    public Result<?> addLabels(@RequestBody @Valid ArrayList<Label> labels) {
        return labelService.addLabelBatch(labels);
    }
    /**
     * 获取id对应的标签
     * @param labelId 标签id
     * @return id对应标签
     */
    @GetMapping("/label/get/{labelId}")
    public Result<Label> getLabel(@PathVariable("labelId") @NotNull(message = "标签Id未填写") Long labelId) {
        return labelService.getLabel(labelId);
    }
    /**
     * 获取全部标签
     * @return 全部标签
     */
    @GetMapping("/label/gets")
    public Result<ArrayList<Label>> getLabels() {
        return labelService.getLabels();
    }
    /**
     * 修改标签
     * @param label 要修改的标签
     * @return 响应code为200表示成功
     */
    @PutMapping("/admin/label/update/{labelId}")
    public Result<?> updateLabel(@RequestBody @Valid Label label) {
        return labelService.updateLabel(label);
    }
    /**
     * 真删除标签
     * @param labelId 标签Id
     * @return 响应code为200表示成功
     */
    @DeleteMapping("/root/label/delete/{labelId}")
    public Result<?> deleteLabel(@PathVariable @NotNull(message = "标签Id未填写")  Long labelId) {
        return labelService.deleteLabel(labelId);
    }
}
