package com.care.peeps;

/**
 * Created by HP on 4/19/2018.
 */

public class Room_model {
    public String name;
    public String image;
    public String status;

    public Room_model(){

    }
    public Room_model(String name,String image,String status) {
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
}
