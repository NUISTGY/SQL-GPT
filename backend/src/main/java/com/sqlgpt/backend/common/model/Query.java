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
    private String count;
    private String prompt = "根据以下DDL建表语句和字段说明，生成" + count + "条mock数据的SQL语句:";

    @Override
    public String toString() {
        return prompt + "\n" + ddl + "\n" +
                "字段说明:" + comment;
    }
}
