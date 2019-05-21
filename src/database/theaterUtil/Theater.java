package database.theaterUtil;

import database.baseInterfaces.ClassFunction;

public class Theater implements ClassFunction {
    private String Tno;
    private String Tname;
    private String Taddress;
    private String Ttel;

    public Theater() {
        this.Tno = null;
        this.Tname = null;
        this.Taddress = null;
        this.Ttel = null;
    }

    public Theater(String Tno, String Tname, String Taddress, String Ttel) {
        this.Tno = Tno;
        this.Tname = Tname;
        this.Taddress = Taddress;
        this.Ttel = Ttel;
    }

    @Override
    public String showSelf() {
        return "{" +
                this.Tno + ", " +
                this.Tname + ", " +
                this.Taddress +", "+
                this.Ttel +
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

    public String getTtel() {
        return Ttel;
    }

    public void setTtel(String ttel) {
        Ttel = ttel;
    }
}
