package frontEnd.utils;

import database.DBOpration;
import database.sceneUtil.SceneTable;
import logger.SimpleLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Filter {

    public static LinkedBlockingQueue<String> filter(LinkedBlockingQueue<Pair4Filter> attr){
        LinkedBlockingQueue<String> Snos = new LinkedBlockingQueue<>();
        HashMap hashMap = new HashMap();
        hashMap.put("Mno", "");
        hashMap.put("Sdate", "");
        hashMap.put("Tbrand","");
        hashMap.put("location","");
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
        if("" != hashMap.get("location")){
            if(count++>0){
                sql +=" And ";
            }
            sql += "location = '" + hashMap.get("location") + "' ";
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
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = DBOpration.getStmt();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Snos.put(rs.getString("Sno"));
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            DBOpration.closeRsStmt(rs, stmt);
        }
        return Snos;
    }

}
