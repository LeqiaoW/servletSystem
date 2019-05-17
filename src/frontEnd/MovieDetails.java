package frontEnd;

import com.alibaba.fastjson.JSON;
import database.movieUtil.Movie;
import database.movieUtil.MovieTable;
import database.movieUtil.MovieToFrontEnd;
import database.system.MovieSystemDB;
import frontEnd.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

@WebServlet(name = "MovieDetails", urlPatterns = {"/MovieDetails"})
public class MovieDetails extends HttpServlet {
    private static final String defaultImagePath = "C://users/swchen/Desktop/test.png";
    private boolean fileExist(String filePath){
        File testFile = new File(filePath);
        return testFile.exists();
    }

    public Movie getMovieDetails(String Mno){
        Movie movie = MovieSystemDB.getMovieTable().select(Mno);
        return movie;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String Mno = req.getParameter("Mno");
        Movie movie = getMovieDetails(Mno);
        ServletUtils.resJsonString(resp, JSON.toJSONString(movie));
    }
}
