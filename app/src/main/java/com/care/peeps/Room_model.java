package com.care.peeps;

/**
 * Created by HP on 4/19/2018.
 */

public class Room_model {
    public String name;
    //public String image;



    public Room_model(){

    }
    public Room_model(String name) {
        this.name = name;
        //this.image = image;

    }

    public void setRoomname(String name) {

        this.name = name;
    }


    public String getname() {

        return name;
    }
   /* public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }*/

}
