package testDatabse;

import database.movieSystem.MovieSystemDB;
import database.userUtil.User;
import database.userUtil.UserTable;

public class testUserTable {
    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        UserTable userTable = MovieSystemDB.getUserTable();

        //test insert
        User user = new User("1", "Kelvin", "wdnmd", "13568994472",
                99999999, "null");
        userTable.insert(user);

        //test select
        User user2 = userTable.select("1");

        //test update
        user2.setUno("2");
        user2.setUnama("陈大屌");
        userTable.insert(user2);
        User userUpdate = new User();
        userUpdate.setUno("1");
        userUpdate.setUnama("陈叔炜");
        userTable.update(userUpdate);

        //test delete, make a breakpoint here
        userTable.delete(user2.getUno());
    }
}
