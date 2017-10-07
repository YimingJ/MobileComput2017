package com.example.moveup;

/**
 * Created by jiangyiming on 10/6/17.
 */

public class User {
    /**
     * User email
     */
    @com.google.gson.annotations.SerializedName("email")
    private String uEmail;

    /**
     * User Id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String uId;

    /**
     * User Username
     */
    @com.google.gson.annotations.SerializedName("username")
    private String uName;

    /**
     *  User password
     */
    @com.google.gson.annotations.SerializedName("password")
    private String uPwd;

    /**
     *  User height
     */
    @com.google.gson.annotations.SerializedName("height")
    private String uHeight;

    /**
     *  User weight
     */
    @com.google.gson.annotations.SerializedName("weight")
    private String uWeight;


    /**
     * User constructor
     */
    public User() {

    }


//    public User(String text, String id) {
//        this.setText(text);
//        this.setId(id);
//    }


    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getuHeight() {
        return uHeight;
    }

    public void setuHeight(String uHeight) {
        this.uHeight = uHeight;
    }

    public String getuWeight() {
        return uWeight;
    }

    public void setuWeight(String uWeight) {
        this.uWeight = uWeight;
    }
}
