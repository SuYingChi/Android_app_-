package com.msht.mshtLpg.mshtLpgMaster.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.callback.FileCallBack;
import com.msht.mshtLpg.mshtLpgMaster.util.FileUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

public class DownLoadApkService extends Service{
    private String url;
    private String fileName;
    private RemoteViews contentView;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            url = intent.getStringExtra("url");
            if(url != null && !TextUtils.isEmpty(url)){
                fileName = this.url.substring(this.url.lastIndexOf("/")+1, this.url.length());
                OkHttpUtils.get().build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath()+"/LpgDownloads/", fileName) {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        FileUtil.deleteFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/LpgDownloads/");
                        builder = new NotificationCompat.Builder(DownLoadApkService.this);
                        builder.setSmallIcon(R.mipmap.logo);
                        builder.setContentTitle("下载");
                        builder.setContentText("安装包正在下载...");
                        /*** 自定义  Notification 的显示****/
                        contentView = new RemoteViews(getPackageName(), R.layout.item_notification);
                        contentView.setProgressBar(R.id.progress, 100, 0, false);
                        contentView.setTextViewText(R.id.tv_progress, "0%");
                        contentView.setTextViewText(R.id.id_tv_download,"安装包正在下载...");
                        builder.setContent(contentView);
                        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(R.layout.item_notification, builder.build());
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        notifyNotification((long)progress/total,total);
                    }
                    @Override
                    public void onError(Call call, Exception e, int i) {
                          PopUtil.toastInBottom(e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int i) {
                          notifyNotification(100,100);
                          PopUtil.toastInBottom("安装包下载完成");
                          installApk(DownLoadApkService.this,file);
                    }
                });
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }
    private  void installApk(Context context, File file) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            Uri apkUri= FileProvider.getUriForFile(context,"com.msht.mshtLpg.mshtLpgMaster.fileProvider",file);
            Intent install=new Intent();
            install.setAction(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri,"application/vnd.android.package-archive");
            context.startActivity(install);
        }else {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
        stopSelf();
    }
    private void notifyNotification(long percent,long length){
        contentView.setTextViewText(R.id.tv_progress, percent+"%");
        contentView.setProgressBar(R.id.progress, (int)length,(int)percent, false);
        contentView.setTextViewText(R.id.id_tv_download,"安装包下载完成");
        builder.setContent(contentView);
        notificationManager.notify(R.layout.item_notification, builder.build());
    }
}
