package com.msht.mshtLpg.mshtLpgMaster.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.msht.mshtLpg.mshtLpgMaster.BuildConfig;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.customView.LPGRefreshHeader;
import com.msht.mshtLpg.mshtLpgMaster.util.MLoggerInterceptor;
import com.msht.mshtLpg.mshtLpgMaster.util.SSLSocketClient;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import okhttp3.OkHttpClient;

public class LPGApplication extends Application {

    private static LPGApplication instance;
    private SharePreferenceUtil spu;

    static {

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new LPGRefreshHeader(context);
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                ClassicsFooter footer = new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
                footer.setDrawableArrowSize(14);//设置箭头的大小（dp单位）
                footer.setDrawableProgressSize(14);//设置图片的大小（dp单位）、
                footer.setProgressDrawable(context.getResources().getDrawable(R.drawable.loading));
                footer.setAccentColor(context.getResources().getColor(R.color.text_hint));//设置强调颜色
                footer.setTextSizeTitle(14);
                footer.setFinishDuration(0);
                return footer;
            }
        });


    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ZXingLibrary.initDisplayOpinion(this);
        BGASwipeBackHelper.init(this, null);
        OkHttpClient okHttpClient;
        if (BuildConfig.DEBUG) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new MLoggerInterceptor("http", true))
                    .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                    .readTimeout(30000L, TimeUnit.MILLISECONDS)
                    .build();
        } else {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new MLoggerInterceptor("https", true))
                    .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                    .readTimeout(30000L, TimeUnit.MILLISECONDS)
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .build();
        }
        OkHttpUtils.initClient(okHttpClient);
        spu = SharePreferenceUtil.getInstance();
    }


    public static Context getLPGApplicationContext() {

        return instance.getApplicationContext();

    }

    public static SSLSocketFactory getCertificates(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null) {
                        certificate.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            //new SecureRandom()
                            null
                    );
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳过证书信任
     *
     * @throws Exception
     */
    public SSLSocketFactory trustAllCertificate() {
        try {
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, tm, new java.security.SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 信任所有主机 对于任何证书都不做SSL检测
     * 安全验证机制，而Android采用的是X509验证
     */
    private static class MyX509TrustManager implements X509TrustManager {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

    }
}

