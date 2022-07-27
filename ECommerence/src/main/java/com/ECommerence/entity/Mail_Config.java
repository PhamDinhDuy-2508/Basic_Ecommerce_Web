package com.ECommerence.entity;

abstract class Mail_Config  {
    public Mail_Config(){}
    public static String Host_Name = "smtp.gmail.com" ;
    public static final int SSL_PORT = 465;

    public static final int TSL_PORT = 587;
    public static final String APP_EMAIL = "duy.pham2508@hcmut.edu.vn"; // your email

    public static final String APP_PASSWORD = "25082000"; // your password

    public static  String RECEIVE_EMAIL = "";

    public static String getReceiveEmail() {
        return RECEIVE_EMAIL;
    }

    public static void setReceiveEmail(String receiveEmail) {
        RECEIVE_EMAIL = receiveEmail;
    }

    public  String getHost_Name() {
        return this.Host_Name ;
    }
}

