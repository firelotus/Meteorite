package com.firelotus.meteoritelibrary.tools;

import android.content.Context;

import com.firelotus.meteoritelibrary.utils.Tls12SocketFactory;
import com.tamic.novate.Novate;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by firelotus on 2017/12/13.
 */

public enum NovateManager {
    INSTANCE;
    public Novate novate;
    private Context context;
    NovateManager(){
    }

    /**
     * Application中初始化
     * @param context
     * @param baseUrl
     */
    public void init(Context context,String baseUrl){
        if(novate == null){
            novate = new Novate.Builder(context)
                    .baseUrl(baseUrl)
                    .addSSLSocketFactory(getTrustAllSSLSocketFactory())
                    .addHostnameVerifier(getTrustAllHostnameVerifier())
                    .addInterceptor(new NetworkInterceptor(context))
                    //.addCache(false)//解决无网络请求异常后,有网也无法获取数据问题
                    .build();
            this.context = context;
        }
    }

    /**
     * 默认信任所有证书
     *
     * @return
     */
    protected static SSLSocketFactory getTrustAllSSLSocketFactory() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] x509Certificates,
                        String s) {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] x509Certificates,
                        String s) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }};

            SSLContext sslContext = SSLContext.getInstance("TLS");

            sslContext.init(null, trustAllCerts, new SecureRandom());
            //SFLog.d("sslContext.getSocketFactory()==>>"+sslContext.getSocketFactory());
            return new Tls12SocketFactory(sslContext.getSocketFactory());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 网址不校验
     *
     * @return
     */
    protected static HostnameVerifier getTrustAllHostnameVerifier() {

        HostnameVerifier TRUSTED_VERIFIER = new HostnameVerifier() {

            public boolean verify(String hostname, SSLSession session) {
                //SFLog.d("verify==>>true");
                return true;
            }
        };

        return TRUSTED_VERIFIER;
    }

}
