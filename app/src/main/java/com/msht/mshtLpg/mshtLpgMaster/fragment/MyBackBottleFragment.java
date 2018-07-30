package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IScanbottleCodePresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.BackBottleDetailPostActivity;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ScanBottleQRCodeRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.handler.MyCaptureHandler;
import com.msht.mshtLpg.mshtLpgMaster.util.BottleCaculteUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBackBottleView;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;
import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBackBottleFragment extends BaseFragment implements IBackBottleView, SurfaceHolder.Callback {

    @BindView(R.id.scan_delive_steel_bottle_qrcode_viewfinder_view)
    ViewfinderView viewfinderView;
    @BindView(R.id.scan_delive_steel_bottle_preview_view)
    SurfaceView surfaceView;
    @BindView(R.id.scan_delive_topbar)
    TopBarView topBarView;
    @BindView(R.id.et_scan_qrcode_steel_bottle_number)
    EditText etInput;
    @BindView(R.id.btn_scan_qrcode_query_steel_bottle)
    TextView queryBtn;
    @BindView(R.id.scan_rcl_deliver_steel_bottle)
    RecyclerView recyclerView;
    @BindView(R.id.scan_qrcode_delive_five_steel_bottle_number)
    TextView fiveBottleNumber;
    @BindView(R.id.scan_qrcode_delive_fifteen_steel_bottle_number)
    TextView fifteenBottleNumber;
    @BindView(R.id.scan_qrcode_delive_fifty_steel_bottle_number)
    TextView fiftyBottleNumber;
    @BindView(R.id.tv_save_btn)
    TextView nextBtn;
    @BindView(R.id.tv_scan_delive_steel_bottle)
    TextView title;


    private IScanbottleCodePresenter iScanbottleCodePresenter;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private int orderFiveNum;
    private int orderFifteenNum;
    private int orderFiftyNum;
    private SurfaceHolder surfaceHolder;
    private String bottleCode;
    private String orderId;
    private List<VerifyBottleBean> list = new ArrayList<VerifyBottleBean>();
    private ScanBottleQRCodeRclAdapter adapter;
    private Camera camera;
    private MyCaptureHandler handler;
    protected Vector<BarcodeFormat> decodeFormats;
    protected String characterSet;
    private boolean playBeep;
    private MediaPlayer mediaPlayer;
    private boolean vibrate;
    protected static final long VIBRATE_DURATION = 200L;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            orderFiveNum = bundle.getInt(Constants.ORDER_FIVE_NUM, 0);
            orderFifteenNum = bundle.getInt(Constants.ORDER_FIFTEEN_NUM, 0);
            orderFiftyNum = bundle.getInt(Constants.ORDER_FIFTY_NUM, 0);
            orderId = bundle.getString(Constants.ORDER_ID);
        } else {
            return;
        }
        CameraManager.init(LPGApplication.getLPGApplicationContext());
        iScanbottleCodePresenter = new IScanbottleCodePresenter(this);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this.getActivity());
    }

    @Override
    public void showLoading() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scan_delive_steel_bottle_layout, null);
        ButterKnife.bind(this, view);
        surfaceHolder = surfaceView.getHolder();
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        topBarView.setTitle("回收钢瓶");
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleCode = etInput.getText().toString();
                iScanbottleCodePresenter.queryBackBottleByQRCode();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkList(list)) {
                    Intent intent = new Intent(getActivity(), BackBottleDetailPostActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.ORDER_ID, orderId);
                    bundle.putSerializable(Constants.EMPTY_BOTTLE_LIST, (Serializable) list);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        adapter = new ScanBottleQRCodeRclAdapter(list, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        title.setText("请扫描并回收用户钢瓶");
        return view;
    }

    private boolean checkList(List<VerifyBottleBean> list) {
        if (BottleCaculteUtil.getBottleNum(list, 5) < orderFiveNum) {
            PopUtil.toastInBottom("5kg钢瓶未达到订单数");
            return false;
        } else if (BottleCaculteUtil.getBottleNum(list, 15) < orderFifteenNum) {
            PopUtil.toastInBottom("15kg钢瓶未达到订单数");
            return false;
        } else if (BottleCaculteUtil.getBottleNum(list, 50) < orderFiftyNum) {
            PopUtil.toastInBottom("50kg钢瓶未达到订单数");
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
        inactivityTimer.shutdown();
    }

    @Override
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String bottleUrl = result.getText();
        if (bottleUrl.length() == 10) {
            bottleCode = bottleUrl;
        } else if (bottleUrl.contains("id=")) {
            int index = bottleUrl.indexOf("id=");
            bottleCode = bottleUrl.substring(index + 3).trim();
        }
        iScanbottleCodePresenter.queryBackBottleByQRCode();
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
        if (handler == null) {
            handler = new MyCaptureHandler(this, decodeFormats, characterSet, viewfinderView);
        }
    }

    protected final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
    private static final float BEEP_VOLUME = 0.10f;

    protected void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
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

    @Override
    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Nullable
    protected CameraInitCallBack callBack;

    public interface CameraInitCallBack {
        /**
         * Callback for Camera init result.
         *
         * @param e If is's null,means success.otherwise Camera init failed with the Exception.
         */
        void callBack(Exception e);
    }

    /**
     * Set callback for Camera check whether Camera init success or not.
     */
    public void setCameraInitCallBack(CameraInitCallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public void onGetBottleInfoSuccess(VerifyBottleBean verifyBottleBean) {
        if (BottleCaculteUtil.isContainBottle(list, verifyBottleBean.getData().getBottleCode())) {
            PopUtil.toastInBottom("钢瓶已添加");
        } else if (verifyBottleBean.getData().getBottleWeight() == 5 && BottleCaculteUtil.getBottleNum(list, 5) >= orderFiveNum) {
            PopUtil.toastInBottom("5kg钢瓶已达到订单数");
        } else if (verifyBottleBean.getData().getBottleWeight() == 15 && BottleCaculteUtil.getBottleNum(list, 15) >= orderFifteenNum) {
            PopUtil.toastInBottom("15kg钢瓶已达到订单数");
        } else if (verifyBottleBean.getData().getBottleWeight() == 50 && BottleCaculteUtil.getBottleNum(list, 50) >= orderFiftyNum) {
            PopUtil.toastInBottom("50kg钢瓶已达到订单数");
        } else {
            list.add(verifyBottleBean);
            adapter.notifyDataSetChanged();
            fiveBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 5)));
            fifteenBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 15)));
            fiftyBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 50)));
        }
        if (handler == null) {
            handler = new MyCaptureHandler(this, decodeFormats, characterSet, viewfinderView);
        }
            Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
            handler.sendMessageDelayed(reDecode, 1000);

    }

    @Override
    public String getBottleCode() {
        return bottleCode;
    }

    @Override
    public String getVerifyType() {
        return 3 + "";
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
    public void onError(String s) {
        super.onError(s);
        Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }
}
