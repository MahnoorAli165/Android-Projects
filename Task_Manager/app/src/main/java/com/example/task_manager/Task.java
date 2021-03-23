package com.example.task_manager;

import java.io.Serializable;

public class Task implements Serializable {

    public static int count = 1;

    private int id;
    private String name;
    private String desc;
    private String date;

    public Task() {
        this.id = 0;
        this.name = "";
        this.desc = "";
        this.date = "";
    }

    public Task(int id, String name, String desc, String date) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
