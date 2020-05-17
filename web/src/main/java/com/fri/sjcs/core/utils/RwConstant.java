package com.fri.sjcs.core.utils;

import org.apache.commons.lang.StringUtils;

import java.io.File;

public class RwConstant {

    // ----------------------------- 环境变量 ---------------------------------

    public static String RW_HOME = System.getProperty("rw.home");

    public static String RW_CONF_PATH = StringUtils.join(new String[] {
            RW_HOME, "conf", "rwjob.json" }, File.separator);

    public static String RW_DATA_PATH = StringUtils.join(new String[] {
            RW_HOME, "data" }, File.separator);

    // ----------------------------- fmq ---------------------------------
    public static final String TRANSFOR_TYPE_SEND="SEND";//传输类型_发送文件
    public static final String TRANSFOR_TYPE_RECEIVE="RECEIVE";//传输类型_接收文件.

    public static final String MSG_SUCCESS_CONNECT_FMQ="连接交换平台成功!";
    public static final String MSG_SUCCESS_DISCONNECT_FMQ="断开交换平台连接成功!";
    public static final String MSG_SUCCESS_SEND_FILE_FMQ="发送文件成功!";
    public static final String MSG_SUCCESS_RECEIVE_FILE_FMQ="接受文件成功!";
    public static final String MSG_ERROR_CONNECT_FMQ="连接交换平台失败!";
    public static final String MSG_ERROR_DISCONNECT_FMQ="断开交换平台连接失败!";
    public static final String MSG_ERROR_SEND_FILE_FMQ="发送文件失败!";
    public static final String MSG_ERROR_RECEIVE_FILE_FMQ="接收文件失败!";
}
