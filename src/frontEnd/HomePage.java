package frontEnd;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import database.movieUtil.Movie;
import database.movieUtil.MovieTable;
import database.system.MovieSystemDB;
import frontEnd.utils.ServletUtils;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "homepage", urlPatterns = {"/homepage"})
public class HomePage extends HttpServlet {

    private static final String defaultImagePath = "C://users/swchen/Desktop/test.png";
    private boolean fileExist(String filePath){
        File testFile = new File(filePath);
        return testFile.exists();
    }

    public LinkedBlockingQueue<Movie> getTop10(){
        LinkedBlockingQueue<Movie> top10 = new LinkedBlockingQueue<>();

        String sql = "Select Mno From " + MovieTable.movieTableName +
                " Order By Mrating limit 10";
        ResultSet rs = null;
        try{
            rs = MovieSystemDB.getStmt().executeQuery(sql);
            while(rs.next()){
                Movie movie = MovieSystemDB.getMovieTable().select(rs.getString(1));
                if(!fileExist(movie.getMposterPath())){
                    movie.setMposterPath(defaultImagePath);
                }
                top10.put(movie);
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
