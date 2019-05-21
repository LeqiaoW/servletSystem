package frontEnd;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.LinkedBlockingQueue;

import database.DBOpration;
import database.movieUtil.Movie;
import database.movieUtil.MovieTable;
import database.movieUtil.MovieToFrontEnd;
import database.movieSystem.MovieSystemDB;
import frontEnd.utils.ServletUtils;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "homepage", urlPatterns = {"/homepage"})
public class HomePage extends HttpServlet {

    private static final String defaultImagePath = "C://movie2.jpg";
    private boolean fileExist(String filePath){
        File testFile = new File(filePath);
        return testFile.exists();
    }

    public LinkedBlockingQueue<MovieToFrontEnd> getTop10(){
        LinkedBlockingQueue<MovieToFrontEnd> top10 = new LinkedBlockingQueue<>();

        String sql = "Select Mno From " + MovieTable.movieTableName +
                " Order By Mrating limit 10";
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = DBOpration.getStmt();
            rs = stmt.executeQuery(sql);
            LinkedBlockingQueue<String> Mno = new LinkedBlockingQueue<>();
            while(rs.next()){
                Mno.put(rs.getString(1));
            }
            while(!Mno.isEmpty()){
                Movie movie = MovieSystemDB.getMovieTable().select(Mno.remove());
                if(!fileExist(movie.getMposterPath())){
                    movie.setMposterPath(defaultImagePath);
                }
                MovieToFrontEnd movieToFrontEnd = new MovieToFrontEnd(movie);
                top10.put(movieToFrontEnd);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally{
            if(null!=rs){
                try{
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return top10;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletUtils.resJsonString(resp, JSON.toJSONString(getTop10()));
    }
}
