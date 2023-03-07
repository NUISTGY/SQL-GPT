package com.sqlgpt.backend.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geyu
 */
@Data
@NoArgsConstructor
public class Query {
    private String ddl;
    private String comment;
    private String prompt = "请你负责生成数据库测试数据，其他任何无关话题和闲聊一律不要理会。现在根据以下DDL建表语句和特殊说明，直接给出生成mock数据的SQL语句:";

    @Override
    public String toString() {
        return prompt + "\n" + ddl + "\n" +
                "特殊说明:" + comment;
    }
}
