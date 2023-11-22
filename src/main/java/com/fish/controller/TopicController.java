package com.fish.controller;

import com.fish.common.Result;
import com.fish.entity.pojo.Topic;
import com.fish.entity.vo.TopicVO;
import com.fish.service.topic.TopicService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TopicController {
    @Resource
    private TopicService topicService;
    @PostMapping("/admin/topic/add")
    public Result<?> addTopic(@Valid Topic topic) {
        return topicService.addTopic(topic);
    }
    @PostMapping("/admin/topic/adds")
    public Result<?> addTopicBatch(@Valid ArrayList<Topic> topics) {
        return topicService.addTopicBatch(topics);
    }
    @PostMapping("/admin/topic/addLabel/{topicId}")
    public Result<?> addTopicLabel(@PathVariable("topicId")Long topicId) {
        return null;
    }
    @DeleteMapping("/admin/topic/delete/{topicId}")
    public Result<?> deleteTopic(
            @PathVariable("topicId")
            @NotNull(message = "题目Id不能为空") Long topicId) {
        return topicService.deleteTopic(topicId);
    }
    @PutMapping("/admin/topic/update/{topicId}")
    public Result<?> updateTopic(
            @PathVariable("topicId")
            @NotNull(message = "题目Id不能为空") Long topicId,
            @Valid Topic topic) {
        return topicService.updateTopic(topicId, topic);
    }
    @GetMapping("/topic/gets")
    public Result<ArrayList<TopicVO>> getTopics() {
        return topicService.getTopics();
    }
    @GetMapping("/topic/get/{topicId}")
    public Result<TopicVO> getTopic(
            @PathVariable("topicId")
            @NotNull(message = "题目Id不能为空") Long topicId) {
        return topicService.getTopic(topicId);
    }
}
