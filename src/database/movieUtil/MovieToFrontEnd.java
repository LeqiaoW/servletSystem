package database.movieUtil;

import com.alibaba.fastjson.annotation.JSONField;

public class MovieToFrontEnd {

    @JSONField(name ="Mno")
    private String Mno;         //primary key
    @JSONField(name ="Mname")
    private String Mname;       //the name of the movieUtil
    @JSONField(name ="MposterPath")
    private String MposterPath;  //the path of the movieUtil's poster in the server computer
    @JSONField(name ="Mrating")
    private double Mrating;      //the rating of the movieUtil

    public MovieToFrontEnd(){
        this.Mno = null;
        this.Mname = null;
        this.MposterPath = null;
        this.Mrating = 0;
    }

    public MovieToFrontEnd(Movie movie) {
        Mno = movie.getMno();
        Mname = movie.getMname();
        MposterPath = movie.getMposterPath();
        Mrating = movie.getMrating();
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

    public double getMrating() {
        return Mrating;
    }

    public void setMrating(double mrating) {
        Mrating = mrating;
    }
}
