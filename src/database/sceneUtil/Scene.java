package database.sceneUtil;

import database.baseInterfaces.ClassFunction;

public class Scene implements ClassFunction{
        private String Sno;     //scene No.
        private String Mno;     //movie No.
        private String Rno;     //room No.
        private String Sdate;    //when to play the movie    yy-mm-dd-hh-mm-ss
        private String seat;     //seat been used
        private double price;   //price

        public Scene() {
            this.Sno = null;
            this.Mno = null;
            this.Rno = null;
            this.Sdate = null;
            this.seat = null;
            this.price = 0;
        }

        public Scene(String Sno, String Mno, String Rno, String date,
                     String seat, double price) {
            this.Sno = Sno;
            this.Mno = Mno;
            this.Rno = Rno;
            this.Sdate = date;
            this.seat = seat;
            this.price = price;
        }

        @Override
        public String showSelf() {
            return "{" +
                    this.Sno +", " +
                    this.Mno + ", " +
                    this.Rno + ", " +
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

        public String getRno() {
            return Rno;
        }

        public void setRno(String rno) {
            Rno = rno;
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
}
