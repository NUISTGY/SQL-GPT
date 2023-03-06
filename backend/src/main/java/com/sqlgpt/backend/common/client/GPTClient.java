package com.sqlgpt.backend.common.client;

import cn.jianwoo.openai.chatgptapi.auth.OpenAiAuth;
import cn.jianwoo.openai.chatgptapi.bo.CompletionReq;
import cn.jianwoo.openai.chatgptapi.bo.CompletionRes;
import cn.jianwoo.openai.chatgptapi.bo.MessageReq;
import cn.jianwoo.openai.chatgptapi.constants.Model;
import cn.jianwoo.openai.chatgptapi.constants.Role;
import cn.jianwoo.openai.chatgptapi.exception.ApiException;
import cn.jianwoo.openai.chatgptapi.service.PostApiService;
import cn.jianwoo.openai.chatgptapi.service.impl.ChatGptApiPost;
import com.sqlgpt.backend.common.model.Query;
import com.sqlgpt.backend.common.model.R;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

/**
 * 描述： GPT-API
 *
 * @author geyu
 * 2023-03-6
 */
@Data
@Component
@ConfigurationProperties(prefix="gpt.sql")
public class GPTClient {

    private String apiKey;
    private String hostname;
    private int port;
    PostApiService client;

    public void GPTClientFactory(){
        // 初始化服务
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname,port));
        client = new ChatGptApiPost(new OpenAiAuth(apiKey, proxy));
    }

    public R sqlMockGeneration(Query query) throws ApiException {
        GPTClientFactory();
        // 请求服务
        CompletionReq req = CompletionReq.builder().model(Model.GPT_35_TURBO.getName())
                .messages(
                        Collections.singletonList(MessageReq.builder().role(Role.USER.getName()).content(query.toString()).build()))
                .build();
        CompletionRes res = client.completionsChat(req);
        return new R(8000, "success", res);
    }
}
