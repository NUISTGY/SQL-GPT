package com.sqlgpt.backend.common.model;

import cn.jianwoo.openai.chatgptapi.bo.CompletionRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private int code;
    private String msg;
    private CompletionRes data;
}
