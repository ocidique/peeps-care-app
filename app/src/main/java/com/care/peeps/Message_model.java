package com.care.peeps;

/**
 * Created by HP on 4/27/2018.
 */

public class Message_model {
    private String createdAt;
    private String msg;
    private String from;


    public Message_model()
    {
        // default constructor
    }

    public Message_model(String createdAt, String from, String msg)
    {
        this.createdAt = createdAt;
        this.from = from;
        this.msg = msg;

    }

    public String getmsg() {

        return msg;
    }

    public void setmsg(String msg) {

        this.msg = msg;
    }

    public String getfrom() {

        return from;
    }

    public void setfrom(String from) {
        this.from = from;
    }

    public String getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
