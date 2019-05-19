package testFrontEnd;

import com.alibaba.fastjson.JSON;
import database.movieSystem.MovieSystemDB;
import frontEnd.Options;

public class TestOptions {

    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        Options options = new Options();
        System.out.println(JSON.toJSON(options.getOptions("1")));
    }
}
