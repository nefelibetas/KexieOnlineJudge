package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.pojo.Label;
import com.fish.service.LabelService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LabelController {
    @Resource
    private LabelService labelService;
    @PostMapping("/admin/label/add")
    public Result<?> addLabel(@RequestBody Label label) {
        return labelService.addLabel(label);
    }
    @PostMapping("/admin/label/adds")
    public Result<?> addLabels(@RequestBody ArrayList<Label> labels) {
        return labelService.addLabelBatch(labels);
    }
    @GetMapping("/label/get/{labelId}")
    public Result<Label> getLabel(@PathVariable("labelId") Long labelId) {
        return labelService.getLabel(labelId);
    }
    @GetMapping("/label/gets")
    public Result<ArrayList<Label>> getLabels() {
        return labelService.getLabels();
    }
    @PutMapping("/admin/label/update")
    public Result<?> updateLabel(@RequestBody Label label) {
        return labelService.updateLabel(label);
    }
    @DeleteMapping("/root/label/delete/{labelId}")
    public Result<?> deleteLabel(@PathVariable Long labelId) {
        return labelService.deleteLabel(labelId);
    }
}
