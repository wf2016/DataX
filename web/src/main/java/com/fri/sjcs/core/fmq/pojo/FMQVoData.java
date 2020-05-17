package com.fri.sjcs.core.fmq.pojo;

import lombok.Data;

/**
 * 功能: 接受中心节点参数
 *
 * @date: 2020-05-15 13:30
 * @author: dl
 * @version: 0.0.1
 * @since: JDK 1.8
 **/
@Data
public class FMQVoData extends FMQOperatorVo {
    private String taskId;
    private String type;
}
