package listener;

import database.system.MovieSystemDB;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBinit implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent arg0) {
//        MovieSystemDB.DBclose();
    }
    public void contextInitialized(ServletContextEvent arg0) {
        MovieSystemDB.DBinit();
    }
}
