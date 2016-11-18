package com.example.arexnt.mydaygram;

import java.io.Serializable;

/**
 * Created by arexnt on 2016/9/27.
 */

public class data implements Serializable{

    private String content;
    private String week;
    private String day;

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getWeek(){
        return week;
    }
    public void setWeek(String week){
        this.week = week;
    }
    public String getDay(){
        return day;
    }
    public void setDay(String day){
        this.day = day;
    }
    public data(String content, String week, String day){
        this.content = content;
        this.week = week;
        this.day = day;
    }
}
