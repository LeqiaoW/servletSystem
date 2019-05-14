package database.orderUtil;

import database.baseInterfaces.TableOperation;
import database.sceneUtil.SceneTable;
import database.system.MovieSystemDB;
import database.userUtil.UserTable;
import logger.SimpleLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderTable implements TableOperation {
    //order is a Reserved-Word in mysql, thus using 'orders' instead of using 'order'
    private static final String tableName = "orders";

    /**
     * create table with name @userTableName
     */
    @Override
    public void createTable() {
        String sql = "Create Table " + tableName + "(" +
                "Ono Char(12) Primary Key," +
                "Odate Char(20)," +
                "Sno Char(12),"+
                "Uno Char(12), " +
                " Foreign Key(Sno) References " +
                SceneTable.sceneTableName + "(Sno)" +
                " On Delete Cascade On Update Cascade,"+
                " Foreign Key(Uno) References " +
                UserTable.userTableName + "(Uno)" +
                " On Delete Cascade On Update Cascade"+
                " )Default Charset = utf8";
        try {
            MovieSystemDB.getStmt().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * drop table with name @userTableName
     */
    @Override
    public void dropTable() {
        String sql = "Drop table " + tableName;
        try {
            MovieSystemDB.getStmt().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param o an object of class Order containing the information needed to
     *          be inserted into table
     * @return a value tells that if the operation is executed successfully
     */
    @Override
    public boolean insert(Object o) {
        String sql = "Insert Into " + tableName + " Values(?, ?, ?,?)";
        PreparedStatement pstmt = null;
        Order order = (Order) o;
        try {
            pstmt = MovieSystemDB.getConn().prepareStatement(sql);

            pstmt.setString(1, order.getOno());
            pstmt.setString(2, order.getOdate());
            pstmt.setString(3, order.getSno());
            pstmt.setString(4, order.getUno());

            if (pstmt.executeUpdate() > 0) {
                SimpleLogger.logger.info("insert " + order.showSelf()
                        + " to table '" + tableName + "'");
                return true;
            }
            SimpleLogger.logger.error("failed to insert " + order.showSelf()
                    + " to table '" + tableName + "'");
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
        SimpleLogger.logger.error("failed to insert " + order.showSelf()
                + " to table '" + tableName + "'");
        return false;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param Ono the value tells the function which record to select in
     *            the table, as UnoPK is the primary key of a record
     */
    @Override
    public Order select(String Ono) {
        String sql = "Select * FROM " + tableName + " Where Ono = '" + Ono + "'";
        ResultSet rs = null;
        try {

            rs = MovieSystemDB.getStmt().executeQuery(sql);
            if (rs.next()) {

                Order order = new Order();

                order.setOno(rs.getString("Ono"));
                order.setOdate(rs.getString("Odate"));
                order.setSno(rs.getString("Sno"));
                order.setUno(rs.getString("Uno"));

                SimpleLogger.logger.info("select " + order.showSelf() +
                        " from table '" + tableName + "'");

                return order;
            } else {
                // TODO: 2019/5/2 说明传递给前端首页的电影信息有误
                SimpleLogger.logger.error("failed to select item with Ono values " +
                        Ono + " from table '" + tableName + "'");
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
        SimpleLogger.logger.error("failed to select item with Ono values " +
                Ono + " from table '" + tableName + "'");
        return null;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param o an object of class Order containing the information needed to
     *          be updated into table
     */
    @Override
    public boolean update(Object o) {

        String sql = "Update " + tableName + " Set ";
        int count = 0;

        Order order = (Order) o;
        //make sql statement
        //----------------------------------
        if (order.getOdate() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Odate = '" + order.getOdate() + "'");
        }
        if (order.getSno() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Sno = '" + order.getSno() + "'");
        }
        if (order.getUno() != null) {
            if (0 < count++) {
                sql += ", ";
            }
            sql += (" Uno = '" + order.getUno() + "'");
        }
        sql += " Where Ono = '" + order.getOno() + "'";
        //----------------------------------

        try {
            if (count > 0) {
                if (MovieSystemDB.getStmt().executeUpdate(sql) > 0) {
                    SimpleLogger.logger.info("update item with Ono '" + order.getOno() +
                            "' in table '" + tableName + "'");
                    return true;
                }
            }
            //not existing Mno
            SimpleLogger.logger.info("failed to update item with Ono '" +
                    order.showSelf() + "' in table '" + tableName + "'");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //not existing Mno
        SimpleLogger.logger.info("failed to update item with Ono '" +
                order.showSelf() + "' in table '" + tableName + "'");
        return false;
    }

    /**
     * the return value tells that if the operation is executed successfully
     *
     * @param Ono the value tells the function which record to delete in
     *            the table, as Uno is the primary key of a record
     */
    @Override
    public boolean delete(String Ono) {
        String sql = "Delete From " + tableName + " Where Ono = '" + Ono + "'";
        try {
            if (MovieSystemDB.getStmt().executeUpdate(sql) > 0) {
                SimpleLogger.logger.info("delete item with Ono '" + Ono + "' from table '"
                        + tableName + "'");
                return true;
            }
            SimpleLogger.logger.error("failed to delete item with Ono '" + Ono +
                    "' from table '" + tableName + "'");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SimpleLogger.logger.error("failed to delete item with Ono '" + Ono +
                "' from table '" + tableName + "'");
        return false;
    }
}
