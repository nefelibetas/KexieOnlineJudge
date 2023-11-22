package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.fish.service.topic.LabelService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LabelController {
    @Resource
    private LabelService labelService;
    @PostMapping("/admin/label/add")
    public Result<?> addLabel(
            @RequestBody
            @Valid Label label) {
        return labelService.addLabel(label);
    }
    @PostMapping("/admin/label/adds")
    public Result<?> addLabels(
            @RequestBody
            @Valid ArrayList<Label> labels) {
        return labelService.addLabelBatch(labels);
    }
    @GetMapping("/label/get/{labelId}")
    public Result<Label> getLabel(
            @PathVariable("labelId")
            @NotNull(message = "标签Id未填写") Long labelId) {
        return labelService.getLabel(labelId);
    }
    @GetMapping("/label/gets")
    public Result<ArrayList<Label>> getLabels() {
        return labelService.getLabels();
    }
    @PutMapping("/admin/label/update")
    public Result<?> updateLabel(
            @RequestBody
            @Valid Label label) {
        return labelService.updateLabel(label);
    }
    @DeleteMapping("/root/label/delete/{labelId}")
    public Result<?> deleteLabel(
            @PathVariable
            @NotNull(message = "标签Id未填写")  Long labelId) {
        return labelService.deleteLabel(labelId);
    }
}
