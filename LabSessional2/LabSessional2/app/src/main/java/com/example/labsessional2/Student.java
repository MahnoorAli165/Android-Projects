package com.example.labsessional2;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String reg_no;
    private long phone;


    public Student( String name, String reg_no,  long phone) {

        this.name = name;
        this.reg_no = reg_no;
        this.phone = phone;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    public Student(String data) {
        String data_[] = data.split("[|]");

        this.name = data_[0];
        this.reg_no = data_[1];
        this.phone = Long.parseLong(data_[2]);

    }



    @Override
    public String toString() {
        return "Student{" +

                ", name='" + name + '\'' +
                ", reg_no=" + reg_no +
                ", phone='" + phone+ '\'' +

                '}';
    }
}