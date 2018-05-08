package com.care.peeps;

import java.io.Serializable;

/**
 * Created by HP on 5/8/2018.
 */

public class Room_check_model implements Serializable {
    public String name;
    public String image;
    public String status;
    private boolean isSelected;

    public Room_check_model(){

    }
    public Room_check_model(String name,String image,String status) {
        this.name = name;
        this.image = image;
        this.status = status;

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
