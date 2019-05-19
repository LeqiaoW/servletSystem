package testDatabse;

import database.sceneUtil.Scene;
import database.sceneUtil.SceneTable;
import database.movieSystem.MovieSystemDB;

public class testSceneTable {
    public static void main(String[] args) {
        MovieSystemDB.DBinit();
        SceneTable sceneTable = MovieSystemDB.getSceneTable();
        //test insert
        Scene scene = new Scene("1", "1", "1", "IMAX",
                "英语", "3D厅","2号厅",
                "郫都区",
                "2018-04-24", "1,2,3", 50);
        sceneTable.insert(scene);
        scene.setSno("2");
        sceneTable.insert(scene);
        scene.setSno("3");
        sceneTable.insert(scene);
        scene.setSno("4");
        sceneTable.insert(scene);
        scene.setSno("5");
        sceneTable.insert(scene);
        scene.setSno("6");
        sceneTable.insert(scene);
        scene.setSno("7");
        sceneTable.insert(scene);
        scene.setSno("8");
        sceneTable.insert(scene);
        scene.setSno("9");
        sceneTable.insert(scene);
        scene.setSno("10");
        sceneTable.insert(scene);

        //test select
        Scene scene2 = sceneTable.select("1");

        //test update
        scene2.setSno("2");
        scene2.setRoomType("B");
        sceneTable.insert(scene2);
        Scene sceneUpdate = new Scene();
        sceneUpdate.setSno("1");
        sceneUpdate.setRoomType("C");
        sceneTable.update(sceneUpdate);

        //test delete, make a breakpoint here
        sceneTable.delete(scene2.getSno());
    }
}
