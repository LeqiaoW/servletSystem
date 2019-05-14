package testTableOperation;

import database.sceneUtil.Scene;
import database.sceneUtil.SceneTable;
import database.system.MovieSystemDB;

public class testSceneTable {
    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        SceneTable sceneTable = MovieSystemDB.getSceneTable();
        //test insert
        Scene scene = new Scene("1", "1", "A",
                "2018-04-24", "1,2,3", 50);
        sceneTable.insert(scene);

        //test select
        Scene scene2 = sceneTable.select("1");

        //test update
        scene2.setSno("2");
        scene2.setRno("B");
        sceneTable.insert(scene2);
        Scene sceneUpdate = new Scene();
        sceneUpdate.setSno("1");
        sceneUpdate.setRno("C");
        sceneTable.update(sceneUpdate);

        //test delete, make a breakpoint here
        sceneTable.delete(scene2.getSno());
    }
}
