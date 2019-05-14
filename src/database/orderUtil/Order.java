package database.orderUtil;

import database.baseInterfaces.ClassFunction;

public class Order  implements ClassFunction{
    private String Ono;
    private String Odate;
    private String Sno;
    private String Uno;

    public Order() {
        this.Ono = null;
        this.Odate = null;
        this.Sno = null;
        this.Uno = null;
    }

    public Order(String Ono, String Odate, String Sno, String Uno) {
        this.Ono = Ono;
        this.Odate = Odate;
        this.Sno = Sno;
        this.Uno = Uno;
    }

    @Override
    public String showSelf() {
        return "{" +
                this.Ono + ", " +
                this.Odate + ", " +
                this.Sno  + ", "+
                this.Uno + "}";
    }
    public String getOno() {
        return Ono;
    }

    public void setOno(String ono) {
        Ono = ono;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getOdate() {
        return Odate;
    }

    public void setOdate(String odate) {
        Odate = odate;
    }

    public String getUno() {
        return Uno;
    }

    public void setUno(String uno) {
        Uno = uno;
    }
}
