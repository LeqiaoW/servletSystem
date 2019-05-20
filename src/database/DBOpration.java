package database;

import database.movieSystem.MovieSystemDB;

import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;

public class DBOpration {

    public static Statement getStmt(){
        try {
            return MovieSystemDB.getConn().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeStmt(Statement stmt){
        if(null!=stmt){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRs(ResultSet rs) {
        if(null!=rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRsStmt(ResultSet rs, Statement stmt) {
        closeRs(rs);
        closeStmt(stmt);
    }

    public static void executeSql(String sql){
        Statement stmt =getStmt();
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeStmt(stmt);
        }
    }


}
