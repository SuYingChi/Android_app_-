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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GetBottleInfo;
import com.msht.mshtLpg.mshtLpgMaster.Present.IRegisterBottlePresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.SpinnerAdapter;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.customView.TimeSelecteDialog;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.handler.MyScanHandler;
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

public class MyScanRegisterBottleFragment extends BaseFragment implements IRegisterBottleView, SurfaceHolder.Callback {
    private static final String TAG = MyScanRegisterBottleFragment.class.getSimpleName();
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
    EditText etBottleNumber;
    @BindView(R.id.property_unit)
    EditText etProduceUnit;
    @BindView(R.id.creat_time)
    TextView tvCreatTime;
    @BindView(R.id.producer)
    EditText etProducer;
    @BindView(R.id.bottle_code)
    TextView tvBottleCode;
    private IRegisterBottlePresenter iRegisterBottlePresenter;
    protected InactivityTimer inactivityTimer;
    protected SurfaceHolder surfaceHolder;
    private String bottleCode;
    private List<String> spinnerList = new ArrayList<String>();
    private boolean hasSurface;
    private Camera camera;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private boolean playBeep;
    private boolean vibrate;
    protected MediaPlayer mediaPlayer;
    private static final float BEEP_VOLUME = 0.10f;
    private MyScanHandler handler;
    protected static final long VIBRATE_DURATION = 200L;
    private String bottleWeight;
    private String bottleNum;
    private String producer;
    private String propertyUnit;
    private String createTime;
    private String nextCheckTime;
    private SpinnerAdapter spWeightAdapter;
    private TimeSelecteDialog timeSelecteDialog;
    private boolean isRestart;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CameraManager.init(LPGApplication.getLPGApplicationContext());
        iRegisterBottlePresenter = new IRegisterBottlePresenter(this);
        hasSurface = false;
        isRestart  = false;
        inactivityTimer = new InactivityTimer(this.getActivity());
        spinnerList.add("    5kg     ");
        spinnerList.add("   15kg     ");
        spinnerList.add("   50kg     ");
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
        spWeightAdapter.setData(spinnerList);
        spWeightAdapter.notifyDataSetChanged();
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              if(s.toString().length()==10){
                  hideInput(MyScanRegisterBottleFragment.this.getContext(),etInput);
              }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("suyingchi", "onSaveInstanceState: ");
        isRestart = true;

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: hasSurface"+hasSurface);
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
            Log.d(TAG, "initCamera: new MyScanHandler");
            handler = new MyScanHandler(this, decodeFormats, characterSet, viewfinderView);
        }
    }

    @Nullable
    protected MyScanDeliverUserBottleFragment.CameraInitCallBack callBack;

    /**
     * Set callback for Camera check whether Camera init success or not.
     */
    public void setCameraInitCallBack(MyScanDeliverUserBottleFragment.CameraInitCallBack callBack) {
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
        if(bottleUrl.length()==10||bottleUrl.length()== 8 ||bottleUrl.length() ==9){
            bottleCode = bottleUrl;
        }else if(bottleUrl.contains("id=")){
            int index = bottleUrl.indexOf("id=");
            bottleCode = bottleUrl.substring(index + 3).trim();
        }
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
        inactivityTimer.shutdown();

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated:    hasSurface"+hasSurface);
        if (!hasSurface) {
            hasSurface = true;
            Log.d(TAG, "surfaceCreated: initCamera(holder);");
            initCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed: ");
        hasSurface = false;
        if (camera != null) {
            //回调到这里的时候CameraManager.get().isPreviewing()应该返回true，但这里返回了false，导致二次打开扫描界面无法开启摄像头
            if (CameraManager.get().isPreviewing()) {
                if (!CameraManager.get().isUseOneShotPreviewCallback()) {
                    camera.setPreviewCallback(null);
                    Log.d(TAG, "surfaceDestroyed:  camera.setPreviewCallback(null);");
                }
                camera.stopPreview();
                CameraManager.get().getPreviewCallback().setHandler(null, 0);
                CameraManager.get().getAutoFocusCallback().setHandler(null, 0);
                CameraManager.get().setPreviewing(false);
                Log.d(TAG, "surfaceDestroyed:  camera.stopPreview();");
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
      etProducer.setText(producer);
      etBottleNumber.setText(bottleNum);
      tvCreatTime.setText(createTime);
      etProduceUnit.setText(propertyUnit);
      tvBottleCode.setText(bottleCode);
      hideInput(MyScanRegisterBottleFragment.this.getContext(),etInput);
      tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottleNum.length()==9){
                    iRegisterBottlePresenter.update_bottle_info();
                }else{
                    PopUtil.toastInBottom("必须是9位数的瓶身钢码");
                }
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
        if (handler == null) {
            handler = new MyScanHandler(this, decodeFormats, characterSet, viewfinderView);
        }
            Message reDecode = Message.obtain(handler, R.id.redecode_after_decodeSuccess);
            handler.sendMessageDelayed(reDecode, 1000);

    }

    private void hideInput(Context context, EditText et){
        InputMethodManager inputMethodManager =
                (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);

    }
    @Override
    public String getBottleCode() {
        return bottleCode;
    }

    @Override
    public String getBottleNum() {
        bottleNum =  etBottleNumber.getText().toString();
        return bottleNum;
    }

    @Override
    public String getBottleWeight() {

        return bottleWeight;
    }

    @Override
    public String getProducer() {
        producer =  etProducer.getText().toString();
        return producer;
    }

    @Override
    public String getPropertyUnit() {
        propertyUnit =  etProduceUnit.getText().toString();
        return propertyUnit;
    }

    @Override
    public String getCreateTime() {
        createTime =  tvCreatTime.getText().toString();
        return createTime;
    }

    @Override
    public String getNextCheckTime() {

        return nextCheckTime;
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        Message reDecode = Message.obtain(handler, R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }
}
