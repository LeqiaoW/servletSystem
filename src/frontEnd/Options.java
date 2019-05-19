package frontEnd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import database.movieSystem.MovieSystemDB;
import database.sceneUtil.Scene;
import database.sceneUtil.SceneTable;
import database.theaterUtil.Theater;
import frontEnd.utils.Pair4Filter;
import frontEnd.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class Options extends HttpServlet {
//
//    @JSONField(name = "Sdate")
//    private LinkedBlockingQueue<String> Sdate;
//    @JSONField(name = "Tbrand")
//    private LinkedBlockingQueue<String> Tbrand;
//    @JSONField(name = "location")
//    private LinkedBlockingQueue<String> location;
//    @JSONField(name = "roomType")
//    private LinkedBlockingQueue<String> roomType;

    public Options(){
//        this.Sdate = new LinkedBlockingQueue<>();
//        this.Tbrand = new LinkedBlockingQueue<>();
//        this.location = new LinkedBlockingQueue<>();
//        this.roomType = new LinkedBlockingQueue<>();
    }

    public LinkedBlockingQueue<LinkedBlockingQueue<String>> getOptions(String Mno){
        String sql = "Select distinct %s from " + SceneTable.sceneTableName +
                " Where Mno = '" + Mno +"'";

        LinkedBlockingQueue<LinkedBlockingQueue<String>> ret = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<String> Sdate = new LinkedBlockingQueue<>(),
                Tbrand = new LinkedBlockingQueue<>(),
                location = new LinkedBlockingQueue<>(),
                roomType = new LinkedBlockingQueue<>();
        ResultSet rs = null;
        try {
            rs = MovieSystemDB.getStmt().executeQuery(String.format(sql, "Sdate"));
            while(rs.next()){
                Sdate.put(rs.getString("Sdate"));
            }
            rs = MovieSystemDB.getStmt().executeQuery(String.format(sql, "Tbrand"));
            while(rs.next()){
                Tbrand.put(rs.getString("Tbrand"));
            }
            rs = MovieSystemDB.getStmt().executeQuery(String.format(sql, "location"));
            while(rs.next()){
                location.put(rs.getString("location"));
            }
            rs = MovieSystemDB.getStmt().executeQuery(String.format(sql, "roomType"));
            while(rs.next()){
                roomType.put(rs.getString("roomType"));
            }

            ret.put(Sdate);
            ret.put(Tbrand);
            ret.put(location);
            ret.put(roomType);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(null!=rs){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Mno = req.getParameter("Mno");
        ServletUtils.resJsonString(resp, JSON.toJSONString(getOptions(Mno)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletUtils.resJsonString(resp, JSON.toJSONString(""));
    }
}
