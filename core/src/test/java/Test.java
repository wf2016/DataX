import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.core.Engine;
import com.alibaba.datax.core.util.ConfigParser;
import com.alibaba.datax.core.util.container.CoreConstant;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Test {

    @org.junit.Test
    public void test1() throws Throwable {
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
                "            \"fieldDelimiter\": \"\\t#$#\\t\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        Configuration configuration = Configuration.from(json);
        // todo config优化，只捕获需要的plugin
        String readerPluginName = configuration.getString(
                CoreConstant.DATAX_JOB_CONTENT_READER_NAME);
        String writerPluginName = configuration.getString(
                CoreConstant.DATAX_JOB_CONTENT_WRITER_NAME);

        String preHandlerName = configuration.getString(
                CoreConstant.DATAX_JOB_PREHANDLER_PLUGINNAME);

        String postHandlerName = configuration.getString(
                CoreConstant.DATAX_JOB_POSTHANDLER_PLUGINNAME);

        Set<String> pluginList = new HashSet<String>();
        pluginList.add(readerPluginName);
        pluginList.add(writerPluginName);

        if(StringUtils.isNotEmpty(preHandlerName)) {
            pluginList.add(preHandlerName);
        }
        if(StringUtils.isNotEmpty(postHandlerName)) {
            pluginList.add(postHandlerName);
        }
        try {
            configuration.merge(ConfigParser.parsePluginConfig(new ArrayList<String>(pluginList)), false);
        }catch (Exception e){
            //吞掉异常，保持log干净。这里message足够。
            //LOG.warn(String.format("插件[%s,%s]加载失败，1s后重试... Exception:%s ", readerPluginName, writerPluginName, e.getMessage()));
            System.out.println(String.format("插件[%s,%s]加载失败，1s后重试... Exception:%s ", readerPluginName, writerPluginName, e.getMessage()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                //
            }
            configuration.merge(ConfigParser.parsePluginConfig(new ArrayList<String>(pluginList)), false);
        }
//        String args[] = {"http://127.0.0.1:8071/test/test"};
//        Engine.entry(args);

    }

    @org.junit.Test
    public void test(){
        System.out.println("start test...");
        System.setProperty("datax.home","/Users/wf/Desktop/datax/codes/DataX/target/datax/datax");
        //Configuration configuration = ConfigParser.parse("/Users/wf/Desktop/datax/documents/test.json");
        Configuration configuration = ConfigParser.parse("http://127.0.0.1:8071/test/test");

        Engine engine = new Engine();
        engine.start(configuration);
    }
}
