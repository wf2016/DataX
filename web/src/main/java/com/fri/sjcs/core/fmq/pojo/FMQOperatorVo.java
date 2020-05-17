package com.fri.sjcs.core.fmq.pojo;

import lombok.Data;

/**
 * 功能: 发送文件类
 *
 * @date: 2020-04-14 16:30
 * @author: Allen
 * @version: 0.0.4-snapshot
 * @Email: allenZyhang@163.com
 * @since: JDK 1.8
 **/
@Data
public class FMQOperatorVo extends FMQVo {
    
    private String[] szTargetID; // 目的地地址列表
    private int usTargetIDCount; // 目的地地址个数
    private String szTargetQueueName;//目的地接收队列名称
    private byte cPriority;//优先级别 0 ~ 9
    private byte cEncrypt;//加密算法 0 为没有加密 保留字段
    private byte cCompress;//压缩算法 0 为没有压缩 保留字段
    private byte cSendType;//发送类型 0 保留字段
    private byte cReply;//是否回执 保留字段
    private int ulTimeOut;//超时时间 0 为此标记无效 单位秒
}
