package com.topbnt.common.utils;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public class ApplicationConfig {
    private static ApplicationConfig instance  = new ApplicationConfig();
    public ApplicationConfig getInstance(){
        return  instance;
    }

    public String getLocalTestUrl(){
        return "http://90bab5e3.ngrok.io";
    }
    public String getServerUrl(){
        return "www.topbnt.com";
    }
}
