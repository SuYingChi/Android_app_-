package com.msht.mshtlpgmaster.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.msht.mshtlpgmaster.BuildConfig;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.callback.FileCallBack;
import com.msht.mshtlpgmaster.util.AppUtil;
import com.msht.mshtlpgmaster.util.FileUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

public class DownLoadApkService extends Service {
    private String url;
    private String fileName;
    private RemoteViews contentView;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private String TAG = "DownLoadApk";
    public static final String ONCLICK = "com.app.onclick";
    public File file;
    private BroadcastReceiver receiver_onclick = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ONCLICK)) {
                Log.e(TAG, "onReceive: 点击通知收到广播" );
                notificationManager.cancel(R.layout.item_notification);
                installApk();
            }
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            url = intent.getStringExtra("url");
            if (!TextUtils.isEmpty(url)) {
                fileName = this.url.substring(this.url.lastIndexOf("/") + 1, this.url.length());
                OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath() + "/LpgDownloads/", fileName) {
                    public long total = 0;
                    public float prepercent = 0;
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        FileUtil.deleteFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/LpgDownloads/");
                        creatNotification(0, total,"安装包正在下载...");
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        if (this.total == 0) {
                            this.total = total;
                        }
                        Log.e(TAG, "安装包正在下载... inProgress: progress/ " + AppUtil.formattedDecimalToPercentage((double) (progress / total)) + "   progress   " + progress + "     total=  " + total);
                        float currentPercent = progress / total;
                        if(currentPercent - prepercent>=0.01) {
                            notifyNotification(progress, total, "安装包正在下载...");
                            prepercent = currentPercent;
                        }

                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        PopUtil.toastInBottom("下载安装包发生异常" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int i) {
                        DownLoadApkService.this.file=file;
                        PopUtil.toastInBottom("安装包下载完成");
                        Log.e(TAG, "onResponse: 安装包下载完成");
                        IntentFilter filter_click = new IntentFilter();
                        filter_click.addAction(ONCLICK);
                        //注册广播
                        registerReceiver(receiver_onclick, filter_click);
                        Intent Intent_pre = new Intent(ONCLICK);
                        //得到PendingIntent
                        PendingIntent pendIntent_click = PendingIntent.getBroadcast(DownLoadApkService.this, 0, Intent_pre, PendingIntent.FLAG_UPDATE_CURRENT);
                        //设置监听
                        contentView.setOnClickPendingIntent(R.id.rlt_notification,pendIntent_click);
                        notifyNotification(100, 100,"安装包下载完成");
                    }
                });
            } else {
                PopUtil.toastInBottom("安装包下载链接为空");
                stopSelf();
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }

    //安装文件(适配Android7.0)
    public void installApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //先设置new task启动再add，不然会导致Permission Denial: opening provider android.support.v4.content.FileProvider from ProcessRecord
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, "com.msht.mshtlpgmaster.fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
        stopSelf();
    }

    private void notifyNotification(float progress, long total, String text) {
        contentView.setTextViewText(R.id.tv_progress, AppUtil.formattedDecimalToPercentage((double) (progress / total)));
        contentView.setProgressBar(R.id.progress, (int) total, (int) progress, false);
        contentView.setTextViewText(R.id.id_tv_download, text);
        builder.setContent(contentView);
        notificationManager.notify(R.layout.item_notification, builder.build());
    }

    private void creatNotification(float progress, long total,String s) {
        builder = new NotificationCompat.Builder(DownLoadApkService.this);
        builder.setSmallIcon(R.mipmap.logo);
        builder.setContentTitle("下载");
        builder.setContentText(s);
        //*** 自定义  Notification 的显示****//
        contentView = new RemoteViews(getPackageName(), R.layout.item_notification);
        contentView.setProgressBar(R.id.progress, (int) total, (int) progress, false);
        contentView.setTextViewText(R.id.tv_progress, AppUtil.formattedDecimalToPercentage((double) (progress / total)));
        contentView.setTextViewText(R.id.id_tv_download, "安装包正在下载...");
        builder.setContent(contentView);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(R.layout.item_notification, builder.build());
    }
}
