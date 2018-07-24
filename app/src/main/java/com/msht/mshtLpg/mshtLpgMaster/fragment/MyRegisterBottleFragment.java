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
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GetBottleInfo;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IRegisterBottlePresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ScanBottleQRCodeRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.adapter.SpinnerAdapter;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.handler.MyCaptureHandler;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IRegisterBottleView;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;
import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRegisterBottleFragment extends BaseFragment implements IRegisterBottleView, SurfaceHolder.Callback {
    @BindView(R.id.scan_delive_topbar)
    TopBarView topBarView;
    @BindView(R.id.et_scan_qrcode_steel_bottle_number)
    EditText etInput;
    @BindView(R.id.btn_scan_qrcode_query_steel_bottle)
    TextView tvQuery;
    @BindView(R.id.tv_scan_delive_bottom)
    TextView tvNext;
    @BindView(R.id.scan_delive_steel_bottle_preview_view)
    SurfaceView surfaceView;
    @BindView(R.id.scan_delive_steel_bottle_qrcode_viewfinder_view)
    ViewfinderView viewfinderView;
    @BindView(R.id.weight)
    Spinner spWeight;
    @BindView(R.id.bottle_number)
    TextView tvBottleNumber;
    @BindView(R.id.property_unit)
    TextView tvProduceUnit;
    @BindView(R.id.creat_time)
    TextView tvProduceTime;
    @BindView(R.id.producer)
    TextView tvProducer;
    @BindView(R.id.bottle_code)
    TextView tvBottleCode;
    private IRegisterBottlePresenter iRegisterBottlePresenter;
    protected InactivityTimer inactivityTimer;
    protected SurfaceHolder surfaceHolder;
    private String bottleCode;
    private List<VerifyBottleBean> list = new ArrayList<VerifyBottleBean>();
    private List<String> spinnerList = new ArrayList<String>();
    private boolean hasSurface;
    private Camera camera;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private boolean playBeep;
    private boolean vibrate;
    protected MediaPlayer mediaPlayer;
    private static final float BEEP_VOLUME = 0.10f;
    private MyCaptureHandler handler;
    protected static final long VIBRATE_DURATION = 200L;
    private String bottleWeight;
    private String bottleNum;
    private String producer;
    private String propertyUnit;
    private String createTime;
    private String nextCheckTime;
    private SpinnerAdapter spWeightAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CameraManager.init(LPGApplication.getLPGApplicationContext());
        iRegisterBottlePresenter = new IRegisterBottlePresenter(this);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this.getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_bottle_layout, null);

        if (view != null) {
            ButterKnife.bind(this, view);
        } else {
            return null;
        }
        surfaceHolder = surfaceView.getHolder();
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleCode = etInput.getText().toString();
                iRegisterBottlePresenter.getBottleInfo();
            }
        });

        spWeightAdapter = new SpinnerAdapter(getActivity());
        spWeight.setAdapter(spWeightAdapter);
        spinnerList.add("5kg        ");
        spinnerList.add("15kg        ");
        spinnerList.add("50kg        ");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    protected final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    protected void initCamera(SurfaceHolder surfaceHolder) {
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

    @Nullable
    protected MyCaptureFragment.CameraInitCallBack callBack;

    /**
     * Set callback for Camera check whether Camera init success or not.
     */
    public void setCameraInitCallBack(MyCaptureFragment.CameraInitCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    @Override
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String bottleUrl = result.getText();
        int index = bottleUrl.indexOf("id=");
        bottleCode = bottleUrl.substring(index + 3).trim();
        PopUtil.toastInBottom(result.getText());
        iRegisterBottlePresenter.getBottleInfo();
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
    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    @Override
    public Handler getHandler() {
        return handler;
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
    public void onGetBottleInfoSuccess(GetBottleInfo getBottleInfoBean) {
      bottleCode = getBottleInfoBean.getData().getBottleCode();
      bottleWeight= getBottleInfoBean.getData().getBottleWeight()+"";
      bottleNum = getBottleInfoBean.getData().getBottleNum();
      producer = getBottleInfoBean.getData().getProducer();
      propertyUnit=  getBottleInfoBean.getData().getPropertyUnit();
      createTime =getBottleInfoBean.getData().getCreateTime();
      nextCheckTime = getBottleInfoBean.getData().getNextCheckTime();
      tvProducer.setText(producer);
      tvBottleNumber.setText(bottleNum);
      tvProduceTime.setText(createTime);
      spWeightAdapter.setData(spinnerList);
      spWeightAdapter.notifyDataSetChanged();
      tvProduceUnit.setText(propertyUnit);
      tvBottleCode.setText(bottleCode);
      tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRegisterBottlePresenter.update_bottle_info();
            }
        });
      spWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           switch (position){
               case 0:
                   bottleWeight = 5+"";
                   break;
               case 1:
                   bottleWeight = 15+"";
                   break;
               case 2:
                   bottleWeight = 50+"";
                   break;
                   default:
                       break;
           }
       }

       @Override
       public void onNothingSelected(AdapterView<?> parent) {

       }
   });
    }

    @Override
    public void onUpdateBottleInfoSuccess(ErrorBean errorBean) {
        PopUtil.toastInBottom("修改钢瓶注册信息成功");
        Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }


    @Override
    public String getBottleCode() {
        return bottleCode;
    }

    @Override
    public String getBottleNum() {
        return bottleNum;
    }

    @Override
    public String getBottleWeight() {
        return bottleWeight;
    }

    @Override
    public String getProducer() {
        return producer;
    }

    @Override
    public String getPropertyUnit() {
        return propertyUnit;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public String getNextCheckTime() {
        return nextCheckTime;
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }
}
