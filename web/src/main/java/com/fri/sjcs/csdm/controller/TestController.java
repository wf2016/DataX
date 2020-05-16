package com.fri.sjcs.csdm.controller;

import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.core.Engine;
import com.alibaba.datax.core.util.ConfigParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {


    @RequestMapping("test1")
    public void test1(){
        System.setProperty("datax.home","/Users/wf/Desktop/datax/codes/DataX/target/datax/datax");
        //Configuration configuration = ConfigParser.parse("/Users/wf/Desktop/datax/documents/test.json");
        Configuration configuration = ConfigParser.parse("http://127.0.0.1:8071/test/test");

        Engine engine = new Engine();
        engine.start(configuration);
    }

    @RequestMapping("test")
    public String test(){

        System.out.println("test controller");
        String json = "{\n" +
                "  \"job\": {\n" +
                "    \"setting\": {\n" +
                "      \"speed\": {\n" +
                "        \"byte\": 10485760\n" +
                "      },\n" +
                "      \"errorLimit\": {\n" +
                "        \"record\": 0,\n" +
                "        \"percentage\": 0.02\n" +
                "      }\n" +
                "    },\n" +
                "    \"content\": [\n" +
                "      {\n" +
                "        \"reader\": {\n" +
                "          \"name\": \"mysqlreader\",\n" +
                "          \"parameter\": {\n" +
                "            \"username\": \"root\",\n" +
                "            \"password\": \"LXJTlxjt1110!*#@\",\n" +
                "            \"column\": [\n" +
                "              \"id\",\n" +
                "              \"name\",\n" +
                "              \"remark\",\n" +
                "              \"order_key\"\n" +
                "            ],\n" +
                "            \"connection\": [\n" +
                "              {\n" +
                "                \"jdbcUrl\": [\n" +
                "                  \"jdbc:mysql://134.175.47.162:3306/lxjt\"\n" +
                "                ],\n" +
                "                \"table\": [\n" +
                "                  \"exam_type\"\n" +
                "                ]\n" +
                "              }\n" +
                "            ],\n" +
                "            \"where\": \"\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"writer\": {\n" +
                "          \"name\": \"txtfilewriter\",\n" +
                "          \"parameter\": {\n" +
                "            \"path\": \"./\",\n" +
                "            \"fileName\": \"test.txt\",\n" +
                "            \"encoding\": \"utf-8\",\n" +
                "            \"dateFormat\": \"yyyy-MM-dd\",\n" +
                "            \"writeMode\": \"truncate\",\n" +
                "            \"fieldDelimiter\": \"⚡\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        return json;
    }
}
