package com.sqlgpt.backend;

import cn.jianwoo.openai.chatgptapi.auth.OpenAiAuth;
import cn.jianwoo.openai.chatgptapi.bo.CompletionReq;
import cn.jianwoo.openai.chatgptapi.bo.CompletionRes;
import cn.jianwoo.openai.chatgptapi.bo.MessageReq;
import cn.jianwoo.openai.chatgptapi.constants.Role;
import cn.jianwoo.openai.chatgptapi.exception.ApiException;
import cn.jianwoo.openai.chatgptapi.service.PostApiService;
import cn.jianwoo.openai.chatgptapi.service.impl.ChatGptApiPost;
import cn.jianwoo.openai.chatgptapi.constants.Model;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

/**
 * 描述： 测试类
 *
 * @author geyu
 * 2023-03-6
 */
public class OpenAiClientTest {

    private String apiKey;
    private String hostname;
    private int port;
    PostApiService client;

    @Before
    public void before(){
        // 初始化服务
        // <host>不带 http或者https前缀!
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname,port));
        client = new ChatGptApiPost(new OpenAiAuth(apiKey, proxy));
    }

    @Test
    public void sqlMockGeneration() throws ApiException {
        // 请求服务
        CompletionReq req = CompletionReq.builder().model(Model.GPT_35_TURBO.getName())
                .messages(
                        Collections.singletonList(MessageReq.builder().role(Role.USER.getName()).content("你好GPT!").build()))
                .build();
        CompletionRes res = client.completionsChat(req);
        System.out.println(res.getChatContent());
//        System.out.println(JSONObject.toJSONString(res));
    }
}
