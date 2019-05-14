package database.sceneUtil;

import database.baseInterfaces.TableOperation;
import database.movieUtil.MovieTable;
import database.system.MovieSystemDB;
import logger.SimpleLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SceneTable implements TableOperation {
    public static final String sceneTableName = "scene";

    /**
     *  create table with name @sceneTableName
     */
    @Override
    public void createTable() {
        String sql = "Create Table "+ sceneTableName +"(" +
                "Sno Char(12) Primary Key," +
                "Mno Char(12),"+
                "Rno Char(20)," +
                "Sdate Char(20)," +
                "seat Char(200)," +
                "price Double," +
                " Foreign Key (Mno) References " +
                MovieTable.movieTableName + "(Mno)" +
                " On Delete Cascade On Update Cascade"+
                ")Default Charset = utf8";
        SimpleLogger.logger.info(sql);
        try {
            MovieSystemDB.getStmt().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  drop table with name @sceneTableName
     */
    @Override
    public void dropTable() {
        String sql = "Drop table " + sceneTableName;
        try {
            MovieSystemDB.getStmt().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param o an object of class Scene containing the information needed to
     *              be inserted into table
     * @return a value tells that if the operation is executed successfully
     */
    @Override
    public boolean insert(Object o) {
        String sql = "Insert Into " + sceneTableName + " Values(?,?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        Scene scene = (Scene) o;
        try {
            pstmt = MovieSystemDB.getConn().prepareStatement(sql);

            pstmt.setString(1, scene.getSno());
            pstmt.setString(2, scene.getMno());
            pstmt.setString(3, scene.getRno());
            pstmt.setString(4, scene.getSdate());
            pstmt.setString(5, scene.getSeat());
            pstmt.setDouble(6, scene.getPrice());

            if (pstmt.executeUpdate() > 0) {
                SimpleLogger.logger.info("insert " + scene.showSelf()
                        + " to table '" + sceneTableName + "'");
                return true;
            }
            SimpleLogger.logger.error("failed to insert " + scene.showSelf()
                    + " to table '" + sceneTableName + "'");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != pstmt) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        SimpleLogger.logger.error("failed to insert " + scene.showSelf()
                + " to table '" + sceneTableName + "'");
        return false;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param Sno the value tells the function which record to select in
     *              the table, as UnoPK is the primary key of a record
     */
    @Override
    public Scene select(String Sno) {
        String sql = "Select * FROM " + sceneTableName + " Where Sno = '" +Sno +"'";
        ResultSet rs = null;
        try {

            rs = MovieSystemDB.getStmt().executeQuery(sql);
            if (rs.next()) {

                Scene scene = new Scene();

                scene.setSno(rs.getString("Sno"));
                scene.setMno(rs.getString("Mno"));
                scene.setRno(rs.getString("Rno"));
                scene.setSdate(rs.getString("Sdate"));
                scene.setSeat(rs.getString("seat"));
                scene.setPrice(rs.getDouble("price"));
                SimpleLogger.logger.info("select " + scene.showSelf() +
                        " from table '" + sceneTableName + "'");
                return scene;
            } else {
                // TODO: 2019/5/2 说明传递给前端首页的电影信息有误
                SimpleLogger.logger.error("failed to select item with Sno values " +
                        Sno + " from table '" + sceneTableName + "'");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //close ResultSet
            try {
                if (null != rs) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        SimpleLogger.logger.error("failed to select item with Sno values " +
                Sno + " from table '" + sceneTableName + "'");
        return null;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param o an object of class Scene containing the information needed to
     *              be updated into table
     */
    @Override
    public boolean update(Object o) {

        String sql = "Update " + sceneTableName + " Set ";
        int count = 0;

        Scene scene = (Scene)o;
        //make sql statement
        //----------------------------------
        if (scene.getMno() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Mno = '" + scene.getMno()+ "'");
        }
        if (scene.getRno() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Rno = '" + scene.getRno() + "'");
        }
        if (scene.getSdate() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Sdate = '" + scene.getSdate() + "'");
        }
        if (scene.getSeat() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" seat = '" + scene.getSeat() + "'");
        }
        if (scene.getPrice() != 0) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" price = '" + scene.getPrice() + "'");
        }
        sql += " Where Sno = '" + scene.getSno() + "'";
        //----------------------------------

        try {
            if (count > 0) {
                if (MovieSystemDB.getStmt().executeUpdate(sql) > 0) {
                    SimpleLogger.logger.info("update item with Sno '" + scene.getSno() +
                            "' in table '" + sceneTableName + "'");
                    return true;
                }
            }
            //not existing pk
            SimpleLogger.logger.info("failed to update item with Sno '" + scene.getSno() +
                    "' in table '" + sceneTableName + "'");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //not existing pk
        SimpleLogger.logger.info("failed to update item with Sno '" + scene.getSno() +
                "' in table '" + sceneTableName + "'");
        return false;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param Sno the value tells the function which record to delete in
     *            the table, as Uno is the primary key of a record
     */
    @Override
    public boolean delete(String Sno) {
        String sql = "Delete From " + sceneTableName + " Where Sno = '" +Sno+"'";
        try {
            if (MovieSystemDB.getStmt().executeUpdate(sql) > 0) {
                SimpleLogger.logger.info("delete item with Sno '" + Sno + "' from table '"
                        + sceneTableName + "'");
                return true;
            }
            SimpleLogger.logger.error("failed to delete item with Sno '" + Sno +
                    "' from table '" + sceneTableName + "'");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SimpleLogger.logger.error("failed to delete item with Sno '" + Sno +
                "' from table '" + sceneTableName + "'");
        return false;
    }
}
