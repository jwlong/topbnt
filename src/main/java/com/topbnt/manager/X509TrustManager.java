package com.topbnt.manager;

import javax.net.ssl.TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
public interface X509TrustManager  extends TrustManager{
    public void checkClientTrusted(X509Certificate[] chain,String authType) throws CertificateException;
    public void checkServerTrusted(X509Certificate[] chain,String authType) throws CertificateException;
    public X509Certificate[] getAcceptedIssuers();
}
