package com.fri.sjcs.core.fmq.service;


import com.fri.sjcs.core.fmq.pojo.FMQOperatorVo;

/**
 * 功能:
 *
 * @date: 2020-04-14 16:12
 * @author: allenZyhang@163.com
 * @version: 0.0.4-snapshot
 * @since: JDK 1.8
 **/
public interface FMQService {
    
    void operatorFile(String taskId, String type, FMQOperatorVo fmqVo);
    
    String queryFMQStatus(String taskId);
}
