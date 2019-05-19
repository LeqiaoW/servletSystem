package database.sceneUtil;

import database.baseInterfaces.ClassFunction;

public class Scene implements ClassFunction {
    private String Sno;     //scene No.
    private String Mno;     //movie No.
    private String Tno;
    private String Tbrand;
    private String language;
    private String roomType;
    private String roomName;
    private String location;
    private String Sdate;    //when to play the movie    yy-mm-dd-hh-mm-ss
    private String seat;     //seat been used
    private double price;   //price

    public Scene() {
        this.Sno = null;
        this.Mno = null;
        this.Tno = null;
        this.Tbrand = null;
        this.language = null;
        this.roomType = null;
        this.roomName = null;
        this.location = null;
        this.Sdate = null;
        this.seat = null;
        this.price = 0;
    }

    public Scene(String Sno, String Mno, String Tno, String Tbrand,
                 String language, String roomType, String roomName,
                 String location, String Sdate,
                 String seat, double price) {

        this.Sno = Sno;
        this.Mno = Mno;
        this.Tno = Tno;
        this.Tbrand = Tbrand;
        this.language = language;
        this.roomType = roomType;
        this.roomName = roomName;
        this.location = location;
        this.Sdate = Sdate;
        this.seat = seat;
        this.price = price;
    }

    @Override
    public String showSelf() {
        return "{" +
                this.Sno + ", " +
                this.Mno + ", " +
                this.Tno + ", " +
                this.Tbrand + ", " +
                this.language + ", " +
                this.roomType + ", " +
                this.roomName + ", " +
                this.location + ", " +
                this.Sdate + ", " +
                this.seat + ", " +
                Double.toString(this.price) + "}";
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getSdate() {
        return Sdate;
    }

    public void setSdate(String sdate) {
        this.Sdate = sdate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getTbrand() {
        return Tbrand;
    }

    public void setTbrand(String tbrand) {
        Tbrand = tbrand;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
