package testDatabse;

import database.orderUtil.Order;
import database.orderUtil.OrderTable;
import database.movieSystem.MovieSystemDB;

public class testOrderTable {
    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        OrderTable orderTable = MovieSystemDB.getOrderTable();
        //test insert
        Order order = new Order("1", "2018-04-24", "1", "1");
        orderTable.insert(order);

        //test select
        Order order2 = orderTable.select("1");

        //test update
        order2.setOno("2");
        orderTable.insert(order2);
        Order orderUpdate = new Order();
        orderUpdate.setOno("1");
        orderUpdate.setOdate("2018-05-05");
        orderTable.update(orderUpdate);

        //test delete, make a breakpoint here
        orderTable.delete(order2.getOno());
    }

}
