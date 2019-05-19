package database.theaterUtil;

import com.alibaba.fastjson.annotation.JSONField;

public class TheaterToFrontEnd {

    @JSONField(name = "Tname")
    private String Tname;
    @JSONField(name = "Taddress")
    private String Taddress;

    public TheaterToFrontEnd(){}

    public TheaterToFrontEnd(Theater theater){
        this.Tname = theater.getTname();
        this.Taddress = theater.getTaddress();
    }

    public String getTaddress() {
        return Taddress;
    }

    public void setTaddress(String taddress) {
        Taddress = taddress;
    }

    public String getTName() {
        return Tname;
    }

    public void setTName(String TName) {
        this.Tname = TName;
    }
}
