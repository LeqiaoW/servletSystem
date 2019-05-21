package frontEnd;

import com.alibaba.fastjson.JSON;
import database.DBOpration;
import database.movieSystem.MovieSystemDB;
import database.sceneUtil.SceneTable;
import frontEnd.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.LinkedBlockingQueue;

@WebServlet(name = "Options", urlPatterns = {"/options"})
public class Options extends HttpServlet {

    public Options(){}

    public LinkedBlockingQueue<LinkedBlockingQueue<String>> getOptions(String Mno){
        String sql = "Select distinct %s from " + SceneTable.sceneTableName +
                " Where Mno = '" + Mno +"'";

        LinkedBlockingQueue<LinkedBlockingQueue<String>> ret = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<String> Sdate = new LinkedBlockingQueue<>(),
                Tbrand = new LinkedBlockingQueue<>(),
                location = new LinkedBlockingQueue<>(),
                roomType = new LinkedBlockingQueue<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DBOpration.getStmt();
            rs = stmt.executeQuery(String.format(sql, "Sdate"));
            while(rs.next()){
                Sdate.put(rs.getString("Sdate"));
            }
            rs = stmt.executeQuery(String.format(sql, "Tbrand"));
            while(rs.next()){
                Tbrand.put(rs.getString("Tbrand"));
            }
            rs = stmt.executeQuery(String.format(sql, "location"));
            while(rs.next()){
                location.put(rs.getString("location"));
            }
            rs = stmt.executeQuery(String.format(sql, "roomType"));
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
            DBOpration.closeRsStmt(rs, stmt);
        }
        return ret;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        ServletUtils.resJsonString(resp, JSON.toJSONString(""));
        String Mno = req.getParameter("Mno");
        ServletUtils.resJsonString(resp, JSON.toJSONString(getOptions(Mno)));
    }
}
