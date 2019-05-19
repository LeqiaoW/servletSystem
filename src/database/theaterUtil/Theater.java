package database.theaterUtil;

import database.baseInterfaces.ClassFunction;

public class Theater implements ClassFunction {
    private String Tno;
    private String Tname;
    private String Taddress;

    public Theater() {
        this.Tno = null;
        this.Tname = null;
        this.Taddress = null;
    }

    public Theater(String Tno, String Tname, String Taddress) {
        this.Tno = Tno;
        this.Tname = Tname;
        this.Taddress = Taddress;
    }

    @Override
    public String showSelf() {
        return "{" +
                this.Tno + ", " +
                this.Tname + ", " +
                this.Taddress +
                "}";
    }

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getTaddress() {
        return Taddress;
    }

    public void setTaddress(String taddress) {
        Taddress = taddress;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }
}
