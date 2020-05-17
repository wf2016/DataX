package com.fri.sjcs.core.tasks.runner;

import com.alibaba.datax.common.plugin.AbstractTaskPlugin;
import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.core.Engine;
import com.fri.sjcs.core.fmq.pojo.FMQOperatorVo;
import com.fri.sjcs.core.fmq.service.FMQService;
import com.fri.sjcs.core.fmq.service.impl.FMQServiceImpl;
import com.fri.sjcs.core.utils.FileUtil;
import com.fri.sjcs.core.utils.RwConstant;
import com.fri.sjcs.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RwWriterRunner extends RwAbstractRunner implements Runnable{

    private String path = null;
    private List<String> filenames = new LinkedList<>();
    FMQService fmqService = new FMQServiceImpl();


    public void prepare(){
        //临时目录及文件名生成
        this.path = FileUtil.getNpath(FileUtil.genPath());
        try {
            Files.createDirectories(Paths.get(this.path));
        }catch (Exception e){
            e.printStackTrace();
            //通用异常处理
        }

        //获取接收文件参数
        this.filenames = this.getJobConf().getList("wrjob.filenames",String.class);

    }

    public void start(){
        //fmq接收文件
        for (String filename: this.filenames) {
            fmqReceive(this.path,filename);
            //拼装 datax engin参数
            this.getJobConf().set("wrjob.jobjson.job.content[0].reader.parameter.path", StringUtils.join(new String[]{path,filename}, File.separator));
            //启动 datax engen
            Configuration wrjob = this.getJobConf().from((Map<String, Object>) this.getJobConf().get("wrjob.jobjson"));
            Engine engine = new Engine();
            engine.start(wrjob);
        }

    }


    @Override
    public void shutdown() {
        //删除临时文件

        //通知任务已经结束

    }

    public void fmqReceive(String path, String filename){
        //配置发送参数
        FMQOperatorVo fmqVo = new FMQOperatorVo();
        //交换平台连接信息
        fmqVo.setSzQueueManagerName(null);//中心节点队列名称
        fmqVo.setUsListenerPort(0);//中心节点端口

        //配置接收信息
        fmqVo.setSzDataType(filename);
        fmqVo.setSzFileDir(path);
        fmqVo.setSzDataQueueName("RcvQueue");
        fmqVo.setSzFileName(filename);

        //执行发送操作
        String taskid = UuidUtil.get32UUID();
        fmqService.operatorFile(taskid, RwConstant.TRANSFOR_TYPE_RECEIVE,fmqVo);
    }

    @Override
    public void run() {
        this.prepare();
        this.start();
    }
}
