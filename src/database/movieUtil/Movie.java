package database.movieUtil;

import com.alibaba.fastjson.annotation.JSONField;
import database.baseInterfaces.ClassFunction;

public class Movie implements ClassFunction {

    @JSONField(name ="Mno")
    private String Mno;         //primary key
    @JSONField(name ="Mname")
    private String Mname;       //the name of the movieUtil
    @JSONField(name ="MposterPath")
    private String MposterPath;  //the path of the movieUtil's poster in the server computer
    @JSONField(name ="Mdirector")
    private String director;    //the directors' id
    @JSONField(name ="actor")
    private String actor;       //the actors' id
    @JSONField(name ="Mtype")
    private int Mtype;          //the movieUtil's type
    @JSONField(name ="Mlanguage")
    private String Mlanguage;   //the language of the movieUtil
    @JSONField(name ="Mlocation")
    private String Mlocation;   //where the movieUtil made of
    @JSONField(name ="Mdate")
    private String Mdate;        //when the movieUtil show
    @JSONField(name ="Mrating")
    private double Mrating;      //the rating of the movieUtil

    public Movie() {
        this.Mno = null;
        this.Mname = null;
        this.MposterPath = null;
        this.director = null;
        this.actor = null;
        this.Mtype = 0;
        this.Mlanguage = null;
        this.Mlocation = null;
        this.Mdate = null;
        this.Mrating = 0;
    }

    public Movie(String Mno, String Mname, String MposterPath,
                 String director, String actor, int Mtype,
                 String Mlanguage, String Mlocation, String Mdate,
                 double Mrating) {
        this.Mno = Mno;
        this.Mname = Mname;
        this.MposterPath = MposterPath;
        this.director = director;
        this.actor = actor;
        this.Mtype = Mtype;
        this.Mlanguage = Mlanguage;
        this.Mlocation = Mlocation;
        this.Mdate = Mdate;
        this.Mrating = Mrating;
    }

    @Override
    public String showSelf() {
        return "{" +
                this.Mno + ", " +
                this.Mname + ", " +
                this.MposterPath + ", " +
                this.director + ", " +
                this.actor + ", " +
                Integer.toString(this.Mtype) + ", " +
                this.Mlanguage + ", " +
                this.Mlocation + ", " +
                this.Mdate + ", " +
                Double.toString(this.Mrating) + "}";
    }
    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getMposterPath() {
        return MposterPath;
    }

    public void setMposterPath(String mposterPath) {
        MposterPath = mposterPath;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getMtype() {
        return Mtype;
    }

    public void setMtype(int mtype) {
        Mtype = mtype;
    }

    public double getMrating() {
        return Mrating;
    }

    public void setMrating(double mrating) {
        Mrating = mrating;
    }

    public String getMdate() {
        return Mdate;
    }

    public void setMdate(String mdate) {
        this.Mdate = mdate;
    }

    public String getMlocation() {
        return Mlocation;
    }

    public void setMlocation(String mlocation) {
        Mlocation = mlocation;
    }

    public String getMlanguage() {
        return Mlanguage;
    }

    public void setMlanguage(String mlanguage) {
        Mlanguage = mlanguage;
    }
}
