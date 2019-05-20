package database.movieSystem;

import database.theaterUtil.TheaterTable;
import database.userUtil.UserTable;
import database.movieUtil.MovieTable;
import database.sceneUtil.SceneTable;
import database.orderUtil.OrderTable;

import logger.SimpleLogger;

import java.sql.*;

public class MovieSystemDB {

    private static final String DIR = "com.mysql.jdbc.Driver";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/movieSystem" +
                    "?useSSL=false" +
                    "&useUnicode=true" +
                    "&characterEncoding=UTF-8";

    private static final String USER = "root";
    private static final String PSWD = "Csw981001.";

    private static MovieTable movieTable = null;
    private static UserTable userTable = null;
    private static SceneTable sceneTable = null;
    private static OrderTable orderTable = null;
    private static TheaterTable theaterTable = null;

    private static Connection conn = null;

    //create all the tables
    private static void createTables() {
        /**
         * warning:do not change the statements' order, because there exists foreign
         * key in these tables
         */
        userTable.createTable();
        movieTable.createTable();
        theaterTable.createTable();
        sceneTable.createTable();
        orderTable.createTable();
    }

    //drop all the tables
    private static void dropTables() {
        /**
         * warning:do not change the statements' order, because there exists foreign
         * key in these tables
         */
        orderTable.dropTable();
        sceneTable.dropTable();
        userTable.dropTable();
        movieTable.dropTable();
        theaterTable.dropTable();
    }

    public static Connection getConn() {
        return conn;
    }

    public static UserTable getUserTable() {
        return userTable;
    }

    public static MovieTable getMovieTable() {
        return movieTable;
    }

    public static SceneTable getSceneTable() {
        return sceneTable;
    }

    public static OrderTable getOrderTable() {
        return orderTable;
    }

    public static TheaterTable getTheaterTable() {
        return theaterTable;
    }

    /**
     * initialize the database, including build the connection between
     * the program and the DBMS, initializing the statement created by
     * the connection, used to executet sql query and finally create
     * tables needed in the system
     */
    public static void DBinit() {
        try {
            //initialize driver and establish connection
            Class.forName(DIR);
            conn = DriverManager.getConnection(DB_URL, USER, PSWD);
            //initialize statement
//            stmt = conn.createStatement();

            //entry of different tables
            userTable = new UserTable();
            movieTable = new MovieTable();
            theaterTable = new TheaterTable();
            sceneTable = new SceneTable();
            orderTable = new OrderTable();

            SimpleLogger.logger.info("Creating new tables...");
            createTables();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * destroy the database, including close the statement created by
     * the connection, close the connection between the program and
     * the DBMS, and finally drop tables in the system
     */
    public static void DBclose() {
        try {

            SimpleLogger.logger.info("Dropping old tables...");
            dropTables();

//            stmt.close();
            SimpleLogger.logger.info("Database connection closing");
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
