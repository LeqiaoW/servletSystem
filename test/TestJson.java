import com.alibaba.fastjson.JSON;
import database.movieUtil.Movie;
import database.movieUtil.MovieToFrontEnd;
import database.system.MovieSystemDB;
import frontEnd.HomePage;

import java.util.concurrent.LinkedBlockingQueue;

public class TestJson {
    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        HomePage homePage = new HomePage();
        LinkedBlockingQueue<MovieToFrontEnd> top10 = homePage.getTop10();
        System.out.println(JSON.toJSONString(top10));
    }
}
