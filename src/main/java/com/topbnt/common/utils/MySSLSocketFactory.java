package com.topbnt.common.utils;

import com.topbnt.manager.impl.MyX509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
public class MySSLSocketFactory extends SSLSocketFactory {
    static{
        mySSLSocketFactory = new MySSLSocketFactory(createSContext());
    }
    private static MySSLSocketFactory mySSLSocketFactory = null;
    private static SSLContext createSContext(){
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslcontext.init(null, new TrustManager[]{new MyX509TrustManager()}, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return null;
        }
        return sslcontext;
    }

    @SuppressWarnings("deprecation")
    private MySSLSocketFactory(SSLContext sslContext) {
        super(sslContext);
        this.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    public static MySSLSocketFactory getInstance(){
        if(mySSLSocketFactory != null){
            return mySSLSocketFactory;
        }else{
            return mySSLSocketFactory = new MySSLSocketFactory(createSContext());
        }
    }

}
