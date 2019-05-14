package testTableOperation;

import database.system.MovieSystemDB;
import database.movieUtil.Movie;
import database.movieUtil.MovieTable;

public class testMovieTable {
    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        MovieTable movieTable = MovieSystemDB.getMovieTable();

        String posterPath = "images/wdnmd.jpg";

        //test insert
        Movie movie = new Movie("1", "复仇者联盟4", posterPath, "罗素", "Chris-evans", 1,
                "English", "China", "2019-04-24", 9.5);
        movieTable.insert(movie);
        movie.setMno("2");
        movieTable.insert(movie);
        movie.setMno("3");
        movieTable.insert(movie);
        movie.setMno("4");
        movieTable.insert(movie);
        movie.setMno("5");
        movieTable.insert(movie);
        movie.setMno("6");
        movieTable.insert(movie);
        movie.setMno("7");
        movieTable.insert(movie);
        movie.setMno("8");
        movieTable.insert(movie);
        movie.setMno("9");
        movieTable.insert(movie);
        movie.setMno("10");
        movieTable.insert(movie);

        //test select
        Movie movie2 = movieTable.select("1");

        //test update
        movie2.setMno("2");
        movie2.setMname("我不是药神assdfa");
        movieTable.insert(movie2);
        Movie movieUpdate = new Movie();
        movieUpdate.setMno("1");
        movieUpdate.setMname("我不是药神");
        movieTable.update(movieUpdate);

        //test delete, make a breakpoint here
//        movieTable.delete(movie2.getMno());
    }
}
