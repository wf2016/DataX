package com.fri.sjcs.core.fmq.service.impl;

import FMQJavaAPI.FMQException;
import FMQJavaAPI.FMQInterface;

import com.fri.sjcs.core.fmq.pojo.FMQOperatorVo;
import com.fri.sjcs.core.fmq.service.FMQService;
import com.fri.sjcs.core.fmq.util.FMQUtils;
import com.fri.sjcs.core.utils.RwConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * 功能:
 *
 * @date: 2020-04-14 16:12
 * @author: Allen
 * @version: 0.0.4-snapshot
 * @Email: allenZyhang@163.com
 * @since: JDK 1.8
 **/

public class FMQServiceImpl implements FMQService {
    private static final Logger LOG = LoggerFactory.getLogger(FMQServiceImpl.class);
    
    /**
     * 传输文件
     *
     * @param taskId
     * @param type send:发送  receive:接收
     * @param fmqVo
     */
    public void operatorFile(final String taskId, final String type, final FMQOperatorVo fmqVo) {
        FMQInterface mFMQInterface = null;
        if (type.equalsIgnoreCase(RwConstant.TRANSFOR_TYPE_SEND)) {
            try {
                //连接平台
                mFMQInterface = FMQUtils.getConnectFMQ(fmqVo.getSzQueueManagerName(), fmqVo.getUsListenerPort());
                //CacheVar.fmqMap.put(taskId, RwConstant.MSG_SUCCESS_CONNECT_FMQ);
            } catch (Exception e) {
                LOG.error(String.format("任务ID:%s MSG:%s 参数: szQueueManagerName=%s,usListenerPort=", taskId, RwConstant.MSG_ERROR_CONNECT_FMQ, fmqVo.getSzQueueManagerName(),
                    fmqVo.getUsListenerPort()));
            }
            try {
                //发送文件
                mFMQInterface.SendFile(fmqVo.getSzTargetID(),       //发送目的地ID列表
                    fmqVo.getUsTargetIDCount(),  //发送目的个数
                    fmqVo.getSzTargetQueueName(),     //目的地队列
                    fmqVo.getSzDataType(),       //数据类型
                    fmqVo.getCPriority(),         //优先级
                    fmqVo.getCEncrypt(),         //加密算法
                    fmqVo.getCCompress(),        //压缩算法
                    fmqVo.getCSendType(),        //发送类型
                    fmqVo.getCReply(),           //是否回执
                    fmqVo.getUlTimeOut(),        //超时时间，0:消息永不失效 单位秒
                    fmqVo.getSzFileDir(),         //待发送文件所在目录
                    fmqVo.getSzFileName(),        //待发送文件名
                    fmqVo.getSzDataQueueName());               //数据队列名称，为空时表示本地默认发送队列

                mFMQInterface.Commit();
                //CacheVar.fmqMap.put(taskId, RwConstant.MSG_SUCCESS_SEND_FILE_FMQ);
            } catch (FMQException e) {
                System.out.print(e.FMQGetLastError());
                System.out.print(e.FMQGetErrorTypeCode());
                System.out.print(e.FMQGetErrorReasonCode());
                LOG.error(String.format("任务ID:%s MSG:%s 参数:%s", taskId, RwConstant.MSG_ERROR_SEND_FILE_FMQ, fmqVo.toString()));
            }
            try {
                //断开与交换平台的连接
                FMQUtils.disConnectFMQ(mFMQInterface);
                //CacheVar.fmqMap.put(taskId, RwConstant.MSG_SUCCESS_DISCONNECT_FMQ);
            } catch (Exception e) {
                LOG.error(String.format("任务ID:%s MSG:%s", taskId, RwConstant.MSG_ERROR_DISCONNECT_FMQ));
            }
        } else if(type.equalsIgnoreCase(RwConstant.TRANSFOR_TYPE_RECEIVE)){
            StringBuffer sb = new StringBuffer();
            sb.append(fmqVo.getSzFileName());
            try {
                //连接平台
                mFMQInterface = FMQUtils.getConnectFMQ("",1223);
                //CacheVar.fmqMap.put(taskId, RwConstant.MSG_SUCCESS_CONNECT_FMQ);
                
            } catch (Exception e) {
                LOG.error(String.format("任务ID:%s MSG:%s 参数: szQueueManagerName=%s,usListenerPort=", taskId, RwConstant.MSG_ERROR_CONNECT_FMQ, fmqVo.getSzQueueManagerName(),
                    fmqVo.getUsListenerPort()));
            }
            try {
                //接收文件
                mFMQInterface.GetFile(fmqVo.getSzDataType(),      //数据类型
                    fmqVo.getSzFileDir(),       //文件接收目录
                    sb,      //返回文件名称
                    fmqVo.getSzDataQueueName(),              //接收数据队列，默认为rcvqueue
                    null,            //返回文件的网关头信息,null不返回
                    true);           //重名文件是否覆盖
                //提交到操作系统
                mFMQInterface.Commit();
                //CacheVar.fmqMap.put(taskId, RwConstant.MSG_SUCCESS_RECEIVE_FILE_FMQ);
            } catch (FMQException e) {
                //打印错误信息
                System.out.print(e.FMQGetLastError());
                System.out.print(e.FMQGetErrorTypeCode());
                System.out.print(e.FMQGetErrorReasonCode());
                LOG.error(String.format("任务ID:%s MSG:%s 参数:%s", taskId, RwConstant.MSG_ERROR_RECEIVE_FILE_FMQ, fmqVo.toString()));
            }
            
            try {
                //断开与交换平台的连接
                FMQUtils.disConnectFMQ(mFMQInterface);
                //CacheVar.fmqMap.put(taskId, RwConstant.MSG_SUCCESS_DISCONNECT_FMQ);
            } catch (Exception e) {
                LOG.error(String.format("任务ID:%s MSG:%s", taskId, RwConstant.MSG_ERROR_DISCONNECT_FMQ));
            }
        }else{
            LOG.error("传输类型不匹配!");
        }
    }
    
    @Override
    public String queryFMQStatus(final String taskId) {
        
//        Object value = CacheVar.fmqMap.get(taskId);
//        if (value != null) {
//            return value.toString();
//        }
        return null;
    }
}
