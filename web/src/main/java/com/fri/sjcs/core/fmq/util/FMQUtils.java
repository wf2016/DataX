package com.fri.sjcs.core.fmq.util;

import FMQJavaAPI.FMQException;
import FMQJavaAPI.FMQInterface;
import FMQJavaAPI.FMQJavaAPI;
import org.springframework.stereotype.Component;

/**
 * 功能:
 *
 * @date: 2020-04-14 15:57
 * @author: Allen
 * @version: 0.0.4-snapshot
 * @Email: allenZyhang@163.com
 * @since: JDK 1.8
 **/

public class FMQUtils {
    
    /**
     * 连接交换平台
     *
     * @return
     */
    public static FMQInterface getConnectFMQ(String szQueueManagerName, int usListenerPort) {
        FMQInterface mFMQInterface = null;
        try {
            mFMQInterface = FMQJavaAPI.ConnectFMQ(szQueueManagerName, usListenerPort);
        } catch (FMQException e) {
            e.printStackTrace();
        } finally {
            return mFMQInterface;
        }
    }
    
    /**
     * 断开交换平台
     */
    public static void disConnectFMQ(FMQInterface fmqInterface) {
        FMQJavaAPI.DisconnectFMQ(fmqInterface);
    }
}
