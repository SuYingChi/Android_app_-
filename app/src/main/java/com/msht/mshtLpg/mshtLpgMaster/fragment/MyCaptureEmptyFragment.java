package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.content.Context;
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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.msht.mshtLpg.mshtLpgMaster.Bean.ScanBottleQRCodeBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IScanbottleCodePresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ScanBottleQRCodeRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.handler.MyCaptureHandler;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IScanCodeDeliverSteelBottleView;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;
import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;

public class MyCaptureEmptyFragment extends MyCaptureFragment implements SurfaceHolder.Callback, IScanCodeDeliverSteelBottleView {

    @BindView(R.id.scan_empty_steel_bottle_qrcode_viewfinder_view)
    ViewfinderView viewfinderView;
    @BindView(R.id.scan_empty_steel_bottle_preview_view)
    SurfaceView surfaceView;
    @BindView(R.id.scan_empty_topbar)
    TopBarView topBarView;
    @BindView(R.id.et_scan_qrcode_steel_bottle_number)
    EditText etInput;
    @BindView(R.id.btn_scan_qrcode_query_steel_bottle)
    TextView queryBtn;
    @BindView(R.id.scan_rcl_empty_steel_bottle)
    RecyclerView recyclerView;
    @BindView(R.id.scan_qrcode_empty_five_steel_bottle_number)
    TextView fiveBottleNumber;
    @BindView(R.id.scan_qrcode_empty_fifteen_steel_bottle_number)
    TextView fifteenBottleNumber;
    @BindView(R.id.scan_qrcode_empty_fifty_steel_bottle_number)
    TextView fiftyBottleNumber;
    @BindView(R.id.tv_save_btn)
    TextView saveBtn;


    private static final String TAG = "MyCaptureEmptyFragment";
    private MyCaptureHandler handler;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private List<ScanBottleQRCodeBean> list = new ArrayList<ScanBottleQRCodeBean>();
    private ScanBottleQRCodeRclAdapter adapter;
    private IScanbottleCodePresenter iScanbottleCodePresenter;
    private String bottleId;
    private CaptureEmptyFrgamnetBtnClickListener captureEmptyFrgamnetBtnClickListener;
    private int fiveNumber = 0;
    private int fifteenNumber = 0;
    private int fiftyNumber = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        Bundle bundle = getArguments();
        fiveNumber = bundle.getInt(Constants.SCANED_FIVE_NUM);
        fifteenNumber = bundle.getInt(Constants.SCANED_FIFTEEN_NUM);
        fiftyNumber = bundle.getInt(Constants.SCANED_FIFTY_NUM);
        CameraManager.init(LPGApplication.getLPGApplicationContext());
        iScanbottleCodePresenter = new IScanbottleCodePresenter(this);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this.getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.scan_empty_steel_bottle_layout, null);
        surfaceHolder = surfaceView.getHolder();
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureEmptyFrgamnetBtnClickListener.onEmptyBottleClickReturnBtn();
            }
        });
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleId = etInput.getText().toString();
                iScanbottleCodePresenter.scanbottle();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureEmptyFrgamnetBtnClickListener.onEmptyBottleClickNextBtn();
            }
        });

        return view;
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

        adapter = new ScanBottleQRCodeRclAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        fiveBottleNumber.setText(adapter.getFiveNum()+"");
        fifteenBottleNumber.setText(adapter.getFifteenNum()+"");
        fiftyBottleNumber.setText(adapter.getFiftyNum()+"");
    }

    @Override
    public void onPause() {
        super.onPause();

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


    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    @Override
    public void handleDecode(Result result, Bitmap barcode) {
        Log.d(TAG, "handleDecode: ");
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();

        if (result == null || TextUtils.isEmpty(result.getText())) {
            PopUtil.toastInBottom("扫描钢瓶二维码失败，请输入钢瓶编号手动查询");
        } else {
            String bottleUrl = result.getText();
            int index = bottleUrl.indexOf("id=");
            bottleId = bottleUrl.substring(index + 3);
            iScanbottleCodePresenter.scanbottle();
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            camera = CameraManager.get().getCamera();
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

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        if (camera != null) {
            if (camera != null && CameraManager.get().isPreviewing()) {
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
    public Handler getHandler() {
        return handler;
    }

    @Override
    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
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

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };


    @Nullable
    EmptyCameraInitCallBack callBack;

    /**
     * Set callback for Camera check whether Camera init success or not.
     */
    public void setEmptyCameraInitCallBack(EmptyCameraInitCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onGetBottleInfoSuccess(ScanBottleQRCodeBean scanBottleQRCodeBean) {

        if (fiveNumber <= adapter.getFiveNum()) {
            PopUtil.toastInBottom("回收的5kg空瓶數量只能小于或等于同规格的交付重瓶的数量");
        } else if (fifteenNumber <= adapter.getFifteenNum()) {
            PopUtil.toastInBottom("回收的15kg空瓶數量只能小于或等于同规格的交付重瓶的数量");

        } else if (fiftyNumber <= adapter.getFiftyNum()) {
            PopUtil.toastInBottom("回收的50kg空瓶數量只能小于或等于同规格的交付重瓶的数量");

        } else if (list.contains(scanBottleQRCodeBean)) {
            PopUtil.toastInBottom("钢瓶已添加");

        } else {
            list.add(scanBottleQRCodeBean);
            adapter.notifyDataSetChanged();
            fiveBottleNumber.setText(adapter.getFiveNum()+"");
            fifteenBottleNumber.setText(adapter.getFifteenNum()+"");
            fiftyBottleNumber.setText(adapter.getFiftyNum()+"");

            Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
            handler.sendMessageDelayed(reDecode, 1000);
        }
    }

    @Override
    public String getBottleId() {
        return bottleId;
    }


    @Override
    public void onError(String s) {
        super.onError(s);
        Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }

    @Override
    public int getFiveNum() {
        return Integer.valueOf(fiveBottleNumber.getText().toString());
    }

    @Override
    public int getFifteenNum() {
        return Integer.valueOf(fifteenBottleNumber.getText().toString());
    }

    @Override
    public int getFiftyNum() {
        return Integer.valueOf(fiftyBottleNumber.getText().toString());
    }

    public interface EmptyCameraInitCallBack {
        /**
         * Callback for Camera init result.
         *
         * @param e If is's null,means success.otherwise Camera init failed with the Exception.
         */
        void callBack(Exception e);
    }

    public interface CaptureEmptyFrgamnetBtnClickListener {
        void onEmptyBottleClickNextBtn();

        void onEmptyBottleClickReturnBtn();
    }

    public void setScanEmptyBottleFrgamnetClickListener(CaptureEmptyFrgamnetBtnClickListener captureEmptyFrgamnetBtnClickListener) {
        this.captureEmptyFrgamnetBtnClickListener = captureEmptyFrgamnetBtnClickListener;
    }
}

