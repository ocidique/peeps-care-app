package com.care.peeps;

/**
 * Created by HP on 5/7/2018.
 */

public class Member_model {
    public String name;
    public Member_model(){

    }
    public Member_model(String name,String image,String status) {
        this.name = name;


    }

    public void setname(String name) {

        this.name = name;
    }


    public String getname() {

        return name;
    }
}
