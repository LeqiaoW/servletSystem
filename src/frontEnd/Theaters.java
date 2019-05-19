package frontEnd;

import com.alibaba.fastjson.JSON;
import database.sceneUtil.Scene;
import database.sceneUtil.SceneTable;
import database.movieSystem.MovieSystemDB;
import database.theaterUtil.Theater;
import database.theaterUtil.TheaterToFrontEnd;
import frontEnd.utils.Pair4Filter;
import frontEnd.utils.ServletUtils;
import logger.SimpleLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;


public class Theaters extends HttpServlet {

    public LinkedBlockingQueue<String> filter(LinkedBlockingQueue<Pair4Filter> attr){
        LinkedBlockingQueue<String> Snos = new LinkedBlockingQueue<>();
        HashMap hashMap = new HashMap();
        hashMap.put("Mno", "");
        hashMap.put("Sdate", "");
        hashMap.put("Tbrand","");
        hashMap.put("Slocation","");
        hashMap.put("roomType","");
        hashMap.put("Tno","");

        while(!attr.isEmpty()){
            Pair4Filter temp = attr.remove();
            hashMap.put(temp.attribute, temp.value);
        }

        int count = 0;
        String sql = "";

        if("" != hashMap.get("Mno")){
            if(count++>0){
                sql +=" And ";
            }
            sql += "Mno = '" + hashMap.get("Mno") + "' ";
        }
        if("" != hashMap.get("Sdate")){
            if(count++>0){
                sql +=" And ";
            }
            sql += "Sdate = '" + hashMap.get("Sdate") + "' ";
        }
        if("" != hashMap.get("Tbrand")){
            if(count++>0){
                sql +=" And ";
            }
            sql += "Tbrand = '" + hashMap.get("Tbrand") + "' ";
        }
        if("" != hashMap.get("Slocation")){
            if(count++>0){
                sql +=" And ";
            }
            sql += "Slocation = '" + hashMap.get("Slocation") + "' ";
        }
        if("" != hashMap.get("roomType")){
            if(count++>0){
                sql +=" And ";
            }
            sql += "roomType = '" + hashMap.get("roomType") + "' ";
        }
        if("" != hashMap.get("Tno")){
            if(count++>0){
                sql +=" And ";
            }
            sql += "Tno = '" + hashMap.get("Tno") + "' ";
        }

        if(count>0){
            sql = "Select Sno from " + SceneTable.sceneTableName +
                " Where " + sql;
        }else{
            sql = "Select Sno from " + SceneTable.sceneTableName;
        }
        SimpleLogger.logger.info(sql);
        ResultSet rs = null;
        try{
            rs = MovieSystemDB.getStmt().executeQuery(sql);
            while(rs.next()){
                Snos.put(rs.getString("Sno"));
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(null != rs){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return Snos;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedBlockingQueue<Pair4Filter> attr = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Theater> theaters = new LinkedBlockingQueue<>();
        try {
            attr.put(new Pair4Filter("Mno", req.getParameter("Mno")));
            attr.put(new Pair4Filter("Sdate", req.getParameter("Sdate")));
            attr.put(new Pair4Filter("Tbrand", req.getParameter("Tbrand")));
            attr.put(new Pair4Filter("Slocation", req.getParameter("Slocation")));
            attr.put(new Pair4Filter("roomType", req.getParameter("roomType")));
            attr.put(new Pair4Filter("Tno", req.getParameter("Tno")));

            LinkedBlockingQueue<String> Snos = filter(attr);
            while(!Snos.isEmpty()){
                Scene scene = MovieSystemDB.getSceneTable().select(Snos.remove());
                String Tno = scene.getTno();
                Theater theater = MovieSystemDB.getTheaterTable().select(Tno);
                theaters.put(theater);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ServletUtils.resJsonString(resp, JSON.toJSONString(theaters));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletUtils.resJsonString(resp, JSON.toJSONString(""));
    }

}
