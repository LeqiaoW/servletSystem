package database.movieUtil;

import database.baseInterfaces.TableOperation;
import database.system.MovieSystemDB;
import logger.SimpleLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieTable implements TableOperation {
    public static final String movieTableName = "movie";

    /**
     *  create table with name @movieTableName
     */
    @Override
    public void createTable() {
        String sql = "Create Table "+ movieTableName +"(" +
                "Mno Char(12) Primary Key," +
                "Mname Char(20)," +
                "MposterPath Char(50)," +
                "director Char(50)," +
                "actor Char(100)," +
                "Mtype Int," +
                "Mlanguage Char(20)," +
                "Mlocation Char(20)," +
                "Mdate Date," +
                "Mrating Decimal(3,2))" +
                " Default Charset = utf8";
        try {
            MovieSystemDB.getStmt().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  drop table with name @movieTableName
     */
    @Override
    public void dropTable() {
        String sql = "Drop table " + movieTableName;
        try {
            MovieSystemDB.getStmt().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param o an object of class Movie containing the information needed to
     *              be inserted into table
     * @return a value tells that if the operation is executed successfully
     */
    @Override
    public boolean insert(Object o) {
        String sql = "Insert Into " + movieTableName + " Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        Movie movie = (Movie)o;
        try {
            pstmt = MovieSystemDB.getConn().prepareStatement(sql);

            pstmt.setString(1, movie.getMno());
            pstmt.setString(2, movie.getMname());
            pstmt.setString(3, movie.getMposterPath());
            pstmt.setString(4, movie.getDirector());
            pstmt.setString(5, movie.getActor());
            pstmt.setInt(6, movie.getMtype());
            pstmt.setString(7, movie.getMlanguage());
            pstmt.setString(8, movie.getMlocation());
            pstmt.setString(9, movie.getMdate());
            pstmt.setDouble(10, movie.getMrating());

            if (pstmt.executeUpdate() > 0) {
                SimpleLogger.logger.info("insert " + movie.showSelf()
                        + " to table '" + movieTableName + "'");
                return true;
            }
            SimpleLogger.logger.error("failed to insert " + movie.showSelf()
                    + " to table '" + movieTableName + "'");
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
        SimpleLogger.logger.error("failed to insert " + movie.showSelf()
                + " to table '" + movieTableName + "'");
        return false;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param MnoPK the value tells the function which record to select in
     *              the table, as MnoPK is the primary key of a record
     */
    @Override
    public Movie select(String MnoPK) {
        String sql = "Select * FROM " + movieTableName + " Where Mno = '" + MnoPK + "'";
        ResultSet rs = null;
        try {

            rs = MovieSystemDB.getStmt().executeQuery(sql);
            if (rs.next()) {

                Movie movie = new Movie();

                movie.setMno(rs.getString("Mno"));
                movie.setMname(rs.getString("Mname"));
                movie.setMposterPath(rs.getString("MposterPath"));
                movie.setDirector(rs.getString("director"));
                movie.setActor(rs.getString("actor"));
                movie.setMtype(rs.getInt("Mtype"));
                movie.setMlanguage(rs.getString("Mlanguage"));
                movie.setMlocation(rs.getString("Mlocation"));
                movie.setMdate(rs.getString("Mdate"));
                movie.setMrating(rs.getDouble("Mrating"));

                SimpleLogger.logger.info("select " + movie.showSelf() +
                        " from table '" + movieTableName + "'");
                return movie;
            } else {
                // TODO: 2019/5/2 说明传递给前端首页的电影信息有误
                SimpleLogger.logger.error("failed to select item with Mno values " +
                        MnoPK + " from table '" + movieTableName + "'");
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
        SimpleLogger.logger.error("failed to select item with Mno values " +
                MnoPK + " from table '" + movieTableName + "'");
        return null;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param o an object of class Movie containing the information needed to
     *              be updated into table
     */
    @Override
    public boolean update(Object o) {

        String sql = "Update " + movieTableName + " Set ";
        int count = 0;

        Movie movie = (Movie)o;
        //make sql statement
        //----------------------------------
        if (movie.getMname() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Mname = '" + movie.getMname() + "'");
        }
        if (movie.getMposterPath() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" MPosterPath = '" + movie.getMposterPath() + "'");
        }
        if (movie.getDirector() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" director = '" + movie.getDirector() + "'");
        }
        if (movie.getActor() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" actor = '" + movie.getActor() + "'");
        }
        if (movie.getMtype() != 0) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Mtype = '" + movie.getMtype() + "'");
        }
        if (movie.getMlanguage() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Mlanguage = '" + movie.getMlanguage() + "'");
        }
        if (movie.getMlocation() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Mlocation = '" + movie.getMlocation() + "'");
        }
        if (movie.getMdate() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Mdate = '" + movie.getMdate() + "'");
        }
        if (movie.getMrating() != 0) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Mrating = '" + movie.getMrating() + "'");
        }
        sql += " Where Mno = '" + movie.getMno() + "'";
        //----------------------------------

        SimpleLogger.logger.info(sql);
        try {
            if (count > 0) {
                if (MovieSystemDB.getStmt().executeUpdate(sql) > 0) {
                    SimpleLogger.logger.info("update item with Mno '" + movie.getMno() +
                            "' in table '" + movieTableName + "'");
                    return true;
                }
            }
            //not existing Mno
            SimpleLogger.logger.info("failed to update item with Mno '" +
                    movie.showSelf() + "' in table '" + movieTableName + "'");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //not existing Mno
        SimpleLogger.logger.info("failed to update item with Mno '" +
                movie.showSelf() + "' in table '" + movieTableName + "'");
        return false;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param Mno the value tells the function which record to delete in
     *            the table, as Mno is the primary key of a record
     */
    @Override
    public boolean delete(String Mno) {
        String sql = "Delete From " + movieTableName + " Where Mno = '" + Mno + "'";
        try {
            if (MovieSystemDB.getStmt().executeUpdate(sql) > 0) {
                SimpleLogger.logger.info("delete item with Mno '" + Mno + "' from table '"
                        + movieTableName + "'");
                return true;
            }
            SimpleLogger.logger.error("failed to delete item with Mno '" + Mno +
                    "' from table '" + movieTableName + "'");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SimpleLogger.logger.error("failed to delete item with Mno '" + Mno +
                "' from table '" + movieTableName + "'");
        return false;
    }

}
