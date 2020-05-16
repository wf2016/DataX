package com.fri.sjcs.utils;

public class RwConstant {

    public static final String readtemplate = "{\n" +
            "  \"job\": {\n" +
            "    \"setting\": {\n" +
            "      \"speed\": {\n" +
            "        \"byte\": 10485760\n" +
            "      },\n" +
            "      \"errorLimit\": {\n" +
            "        \"record\": 0,\n" +
            "        \"percentage\": 0\n" +
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
            "            \"fieldDelimiter\": \"\\t#$#\\t\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    public static final String writetemplate = "";


}
