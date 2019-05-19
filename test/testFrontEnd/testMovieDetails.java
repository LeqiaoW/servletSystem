package testFrontEnd;

import com.alibaba.fastjson.JSON;
import database.movieUtil.Movie;
import database.movieSystem.MovieSystemDB;
import frontEnd.MovieDetails;

public class testMovieDetails {
    public static void main(String[] args) {

        MovieSystemDB.DBinit();
        MovieDetails movieDetails = new MovieDetails();
        Movie movie  = movieDetails.getMovieDetails("1");
        System.out.println(JSON.toJSONString(movie));
    }
}
