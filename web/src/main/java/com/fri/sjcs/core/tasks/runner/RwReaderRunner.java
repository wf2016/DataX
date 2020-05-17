package com.fri.sjcs.core.tasks.runner;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.datax.common.plugin.AbstractTaskPlugin;
import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.core.Engine;
import com.alibaba.datax.core.util.ConfigParser;
import com.alibaba.fastjson.JSON;
import com.fri.sjcs.core.fmq.pojo.FMQOperatorVo;
import com.fri.sjcs.core.fmq.service.FMQService;
import com.fri.sjcs.core.fmq.service.impl.FMQServiceImpl;
import com.fri.sjcs.core.utils.FileUtil;
import com.fri.sjcs.core.utils.RwConstant;
import com.fri.sjcs.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RwReaderRunner extends RwAbstractRunner implements Runnable{

    private String path = null;
    private List<String> filenames = new LinkedList<>();


    public void prepare(){
        //临时文件目录及文件名
        this.path = FileUtil.getNpath(FileUtil.genPath());
        try {
            Files.createDirectories(Paths.get(this.path));
        }catch (Exception e){
            e.printStackTrace();
            //通用异常处理
        }

        //生成taskid 拼装参数
        this.getJobConf().set("rdjob.job.content[0].writer.parameter.path",path);

    }

    public void start(){
        //调用 datax engine
        Configuration rdjob = ConfigParser.parse(JSON.toJSONString(this.getJobConf().get("rdjob")));
        System.setProperty("datax.home","/Users/wf/Desktop/datax/codes/DataX/target/datax/datax");
        Engine engine = new Engine();
        engine.start(rdjob);

        //将生成的文件 通过fmq发送
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(this.path))){
            for(Path path : stream){
                if( Files.isDirectory(path) ) {
                    System.out.println("无效目录");
                }else{
                    System.out.println(path.getFileName());
                    this.filenames.add(path.getFileName().toString());
                    //fmqSend();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        //通知对方fmq接收文件 启动rd任务组
    }


    @Override
    public void shutdown() {
        //删除临时文件
    }



    //fmq 发送
    public void fmqSend(String path, String filename,String[] target, String taskid){
        FMQService fmqService = new FMQServiceImpl();
        //配置发送参数
        FMQOperatorVo fmqVo = new FMQOperatorVo();
        //交换平台连接信息
        fmqVo.setSzQueueManagerName(null);//中心节点队列名称
        fmqVo.setUsListenerPort(0);//中心节点端口

        //配置发送信息
        fmqVo.setSzDataQueueName("SendQueue"); //设置队列名称
        fmqVo.setSzFileDir(path); //设置发送目录
        fmqVo.setSzFileName(filename); //配置文件名
        fmqVo.setSzDataType(filename); //设置数据类型

        //配置发送目标信息
        //String[] target = {"0000000002"};
        fmqVo.setSzTargetID(target); //目标节点id
        fmqVo.setUsTargetIDCount(target.length);
        fmqVo.setSzTargetQueueName("RcvQueue"); //目标节点 队列名称
        byte enc = 1;
        fmqVo.setCEncrypt(enc);
        fmqVo.setCPriority((byte)5);

        //执行发送操作
        //String taskid = "123456001";
        fmqService.operatorFile(taskid, RwConstant.TRANSFOR_TYPE_SEND,fmqVo);
    }

    @Override
    public void run() {
        this.prepare();
        this.start();
    }
}
