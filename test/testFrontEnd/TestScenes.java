package testFrontEnd;

import com.alibaba.fastjson.JSON;
import database.movieSystem.MovieSystemDB;
import frontEnd.Theaters;
import frontEnd.utils.Filter;
import frontEnd.utils.Pair4Filter;

import java.util.concurrent.LinkedBlockingQueue;

public class TestScenes {
    public static void main(String[] args) throws InterruptedException {

        MovieSystemDB.DBinit();
        Theaters scenes = new Theaters();
        LinkedBlockingQueue<Pair4Filter> attr= new LinkedBlockingQueue<>();
        attr.put(new Pair4Filter("Mno", "1"));
        attr.put(new Pair4Filter("Tno", "1"));
        System.out.println(JSON.toJSONString(Filter.filter(attr)));
    }
}
