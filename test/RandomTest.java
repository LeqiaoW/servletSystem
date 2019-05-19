import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import database.movieSystem.MovieSystemDB;
import database.movieUtil.Movie;
import database.sceneUtil.Scene;
import database.theaterUtil.Theater;
import database.theaterUtil.TheaterToFrontEnd;
import frontEnd.Options;
import frontEnd.Theaters;
import frontEnd.utils.Pair4Filter;
import frontEnd.utils.ServletUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

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
