package com.fri.sjcs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fri.sjcs")
@MapperScan("com.fri.sjcs.csdm.dao.interfaces")
public class SjcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SjcsApplication.class,args);
        System.out.println("启动传输节点成功！！！");
    }
}
