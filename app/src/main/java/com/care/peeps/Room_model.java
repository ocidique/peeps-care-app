package com.care.peeps;

import java.io.Serializable;

/**
 * Created by HP on 4/19/2018.
 */

public class Room_model implements Serializable {
    public String name;
    public String image;
    public String status;
    private boolean isSelected;

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String roomid;

    public Room_model(){

    }
    public Room_model(String name,String image,String status,String roomid) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.roomid = roomid;

    }

    public void setRoomname(String name) {

        this.name = name;
    }


    public String getname() {

        return name;
    }
   public String getImage() {
        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }
    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
