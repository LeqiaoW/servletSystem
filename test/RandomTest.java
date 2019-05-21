import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

class Test {
    @JSONField(name = "a")
    private String a;
    @JSONField(name = "b")
    private String b;

    public Test() {
        a = " test A ";
        b = " test B ";
    }
}

public class RandomTest {

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        System.out.println(JSON.toJSONString(test));
    }
}
