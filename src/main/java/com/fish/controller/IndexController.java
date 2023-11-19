package com.fish.controller;

import com.fish.common.Result;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping({"/", "/index"})
    public Result<String> index(@RequestParam(required = false) boolean flag) {
        if (flag)
            throw new ServiceException(ServiceExceptionEnum.METHOD_NOT_SUPPORT);
        return ResultUtil.success("测试成功");
    }
}
