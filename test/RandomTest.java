import com.alibaba.fastjson.JSON;
import database.movieSystem.MovieSystemDB;
import database.movieUtil.Movie;
import database.sceneUtil.Scene;
import database.theaterUtil.Theater;
import database.theaterUtil.TheaterToFrontEnd;
import frontEnd.Theaters;
import frontEnd.utils.Pair4Filter;
import frontEnd.utils.ServletUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
public class RandomTest {

    private static Logger logger = LogManager.getLogger(RandomTest.class.getName());


    public static void main(String[] args) throws InterruptedException {
        MovieSystemDB.DBinit();
        Theaters theaterss = new Theaters();
        LinkedBlockingQueue<Pair4Filter> attr = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Theater> theaters = new LinkedBlockingQueue<>();
        try {
            attr.put(new Pair4Filter("Mno", "1"));
            attr.put(new Pair4Filter("Sdate", ""));
            attr.put(new Pair4Filter("Tbrand", ""));
            attr.put(new Pair4Filter("Slocation", ""));
            attr.put(new Pair4Filter("roomType", ""));
            attr.put(new Pair4Filter("Tno", ""));

            LinkedBlockingQueue<String> Snos = theaterss.filter(attr);
            while(!Snos.isEmpty()){
                Scene scene = MovieSystemDB.getSceneTable().select(Snos.remove());
                if(null == scene){
                    continue;
                }
                String Tno = scene.getTno();
                Theater theater = MovieSystemDB.getTheaterTable().select(Tno);
                TheaterToFrontEnd theaterToFrontEnd = new TheaterToFrontEnd(theater);
                theaters.put(theater);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(JSON.toJSONString(theaters));

    }
}
