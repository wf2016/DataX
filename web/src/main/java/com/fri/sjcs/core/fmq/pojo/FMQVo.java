package com.fri.sjcs.core.fmq.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能:
 *
 * @date: 2020-04-14 16:26
 * @author: Allen
 * @version: 0.0.4-snapshot
 * @Email: allenZyhang@163.com
 * @since: JDK 1.8
 **/
@Data
public class FMQVo implements Serializable {
    
    private String szFileDir;//文件目录
    private String szFileName;//文件名称
    private String szDataQueueName;//数据队列名称
    private String szDataType;//数据类型
    private String szQueueManagerName;//中心节点IP
    private int usListenerPort;//中心节点Port
    
}
