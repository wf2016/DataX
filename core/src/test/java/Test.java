import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.core.Engine;
import com.alibaba.datax.core.util.ConfigParser;

public class Test {

    @org.junit.Test
    public void test(){
        System.out.println("start test...");
        System.setProperty("datax.home","/Users/wf/Desktop/datax/codes/DataX/target/datax/datax");
        Configuration configuration = ConfigParser.parse("/Users/wf/Desktop/datax/documents/test.json");

        Engine engine = new Engine();
        engine.start(configuration);
    }
}
