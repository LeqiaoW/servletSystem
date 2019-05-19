package testDatabse;

import database.movieSystem.MovieSystemDB;
import database.movieUtil.Movie;
import database.movieUtil.MovieTable;
import database.theaterUtil.Theater;
import database.theaterUtil.TheaterTable;

public class testTheaterTable {
    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        TheaterTable theaterTable = MovieSystemDB.getTheaterTable();

        Theater theater = new Theater("1", "龙湖IMAX",
                "电子科技大学旁边的龙湖时代天街");
        theaterTable.insert(theater);
        theater.setTno("2");
        theaterTable.insert(theater);
        theater.setTno("3");
        theaterTable.insert(theater);
        theater.setTno("4");
        theaterTable.insert(theater);
        theater.setTno("5");
        theaterTable.insert(theater);
        theater.setTno("6");
        theaterTable.insert(theater);
        theater.setTno("7");
        theaterTable.insert(theater);
        theater.setTno("8");
        theaterTable.insert(theater);
        theater.setTno("9");
        theaterTable.insert(theater);
        theater.setTno("10");
        theaterTable.insert(theater);

        //test select
        Theater theater2 = theaterTable.select("1");

        //test update
        theater2.setTno("2");
        theater2.setTname("IMAX");
        theaterTable.insert(theater2);
        Theater theaterUpdate = new Theater();
        theaterUpdate.setTno("2");
        theaterUpdate.setTname("IMAX");
        theaterUpdate.setTaddress("龙湖");

        theaterTable.update(theaterUpdate);
        //test delete, make a breakpoint here
//        movieTable.delete(movie2.getMno());
    }
}
