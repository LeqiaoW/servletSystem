package testFrontEnd;

import com.alibaba.fastjson.JSON;
import database.movieUtil.MovieToFrontEnd;
import database.movieSystem.MovieSystemDB;
import frontEnd.HomePage;

import java.util.concurrent.LinkedBlockingQueue;

public class TestHomePage {
    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        HomePage homePage = new HomePage();
        LinkedBlockingQueue<MovieToFrontEnd> top10= homePage.getTop10();
        System.out.println(JSON.toJSONString(top10));
    }
}
