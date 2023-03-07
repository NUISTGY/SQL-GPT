package com.sqlgpt.backend.controller;

import cn.jianwoo.openai.chatgptapi.exception.ApiException;
import com.sqlgpt.backend.common.model.Query;
import com.sqlgpt.backend.common.client.GPTClient;
import com.sqlgpt.backend.common.model.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author geyu
 */
@RestController
public class RequestController {
    @Resource
    private GPTClient client;

        @PostMapping("/getmockdata")
    public R getMockData(@RequestBody Query query) throws ApiException {
        System.out.println(query);
        return client.sqlMockGeneration(query);
    }
}
