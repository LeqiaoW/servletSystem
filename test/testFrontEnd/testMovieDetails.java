package testFrontEnd;

import com.alibaba.fastjson.JSON;
import database.movieUtil.Movie;
import database.movieUtil.MovieToFrontEnd;
import database.system.MovieSystemDB;
import frontEnd.HomePage;
import frontEnd.MovieDetails;

import java.util.concurrent.LinkedBlockingQueue;

public class testMovieDetails {
    public static void main(String[] args) {

        MovieSystemDB.DBinit();
        MovieDetails movieDetails = new MovieDetails();
        Movie movie  = movieDetails.getMovieDetails("1");
        System.out.println(JSON.toJSONString(movie));
    }
}
