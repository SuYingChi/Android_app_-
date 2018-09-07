package com.msht.mshtlpgmaster.fragment;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.msht.mshtlpgmaster.application.LPGApplication;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;

import java.io.IOException;
import java.util.Vector;

//子类自己实现Iview 和创建对应的presenter，用来在扫码后发起网络请求做相应的操作
public class BaseScanFragmengt extends BaseFragment implements SurfaceHolder.Callback {

    protected boolean hasSurface;
    protected boolean isRestart;
    protected InactivityTimer inactivityTimer;
    protected MediaPlayer mediaPlayer;
    protected boolean playBeep;
    protected static final float BEEP_VOLUME = 0.10f;
    protected Camera camera;
    protected static final long VIBRATE_DURATION = 200L;
    protected boolean vibrate;
    protected String bottleCode;
    protected Vector<BarcodeFormat> decodeFormats;
    protected String characterSet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CameraManager.init(LPGApplication.getLPGApplicationContext());
        hasSurface = false;
        isRestart = false;
        inactivityTimer = new InactivityTimer(this.getActivity());
        //子类添加自己的presen
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //子类必须要加载有sufaceview 和viewfinderview的布局
        //View view = inflater.inflate(R.layout.register_bottle_layout, null);
        //surfaceHolder = surfaceView.getHolder();
        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
        //这部分留给子类添加
       /* if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }*/

    }

    protected void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    com.uuzuche.lib_zxing.R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    protected final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
    @Nullable
    protected MyScanDeliverUserBottleFragment.CameraInitCallBack callBack;

    /**
     * Set callback for Camera check whether Camera init success or not.
     */
    public void setCameraInitCallBack(MyScanDeliverUserBottleFragment.CameraInitCallBack callBack) {
        this.callBack = callBack;
    }

    protected void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            camera = CameraManager.get().getCamera();
            camera.startPreview();
        } catch (Exception e) {
            if (callBack != null) {
                callBack.callBack(e);
            }
            return;
        }
        if (callBack != null) {
            callBack.callBack(null);
        }
        //子类需要创建自己的handler
       /* if (handler == null) {
            handler = new MyScanHandler(this, decodeFormats, characterSet, viewfinderView);
        }*/

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        if (camera != null) {
            if (CameraManager.get().isPreviewing()) {
                if (!CameraManager.get().isUseOneShotPreviewCallback()) {
                    camera.setPreviewCallback(null);
                }
                camera.stopPreview();
                CameraManager.get().getPreviewCallback().setHandler(null, 0);
                CameraManager.get().getAutoFocusCallback().setHandler(null, 0);
                CameraManager.get().setPreviewing(false);
            }
        }
    }

    @Override
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String bottleUrl = result.getText();
        if (bottleUrl.length() == 10 || bottleUrl.length() == 8 || bottleUrl.length() == 9) {
            bottleCode = bottleUrl;
        } else if (bottleUrl.contains("id=")) {
            int index = bottleUrl.indexOf("id=");
            bottleCode = bottleUrl.substring(index + 3).trim();
        }
        //子类有需要再增加扫码后的处理逻辑
    }

    protected void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        //需要重写 再次发送扫码请求
    }

    @Override
    public void onPause() {
        super.onPause();
        //重写 暂停preview camera
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭计时器
        //重写
    }

    @Override
    public Handler getHandler() {
        //重写 返回扫码结果回调handler给扫码子线程
        return null;
    }

    @Override
    public void drawViewfinder() {
        //重写 绘制扫码组件
    }
}
