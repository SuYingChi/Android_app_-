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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IScanbottleCodePresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.ScanCodeDeliverSteelBottleActivity;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ScanBottleQRCodeRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.handler.MyCaptureHandler;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.BottleCaculteUtil;
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
import butterknife.ButterKnife;

public class MyDeliverUserBottleFragment extends BaseFragment implements SurfaceHolder.Callback, IScanCodeDeliverSteelBottleView {


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
    private static final String TAG = "MyDeliverUserBottleFragment";
    protected MyCaptureHandler handler;
    protected boolean hasSurface;
    protected Vector<BarcodeFormat> decodeFormats;
    protected String characterSet;
    private InactivityTimer inactivityTimer;
    protected MediaPlayer mediaPlayer;
    protected boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    protected boolean vibrate;
    private SurfaceHolder surfaceHolder;
    protected Camera camera;

    private List<VerifyBottleBean> list = new ArrayList<VerifyBottleBean>();
    private List<VerifyBottleBean> empList = new ArrayList<VerifyBottleBean>();
    private ScanBottleQRCodeRclAdapter adapter;
    private IScanbottleCodePresenter iScanbottleCodePresenter;
    private String bottleCode;
    private CaptureActivityListener captureActivityListener;
    private int orderFiveNum = 0;
    private int orderFifteenNum = 0;
    private int orderFiftyNum = 0;
    private OrderDetailBean bean;
    private int fragmentType;
    private ScanCodeDeliverSteelBottleActivity activity;
    private String verifyType="";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            fragmentType = bundle.getInt(Constants.SCANFRAGMENT_TYPE);
            //扫描交付用户钢瓶
            if (fragmentType == 1) {
                verifyType = "2";
                orderFiveNum = bundle.getInt(Constants.ORDER_FIVE_NUM, 0);
                orderFifteenNum = bundle.getInt(Constants.ORDER_FIFTEEN_NUM, 0);
                orderFiftyNum = bundle.getInt(Constants.ORDER_FIFTY_NUM, 0);
            }//扫描回收空瓶
            else if (fragmentType == 2) {
                verifyType = "3";
                orderFiveNum = bundle.getInt(Constants.ORDER_FIVE_NUM, 0);
                orderFifteenNum = bundle.getInt(Constants.ORDER_FIFTEEN_NUM, 0);
                orderFiftyNum = bundle.getInt(Constants.ORDER_FIFTY_NUM, 0);
            }
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
                captureActivityListener.onCaptureFragmenBackBtnClick(fragmentType);
            }
        });
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==8||s.toString().length()==9){
                    AppUtil.hideInput(MyDeliverUserBottleFragment.this.getContext(),etInput);
                }
            }
        });
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleCode = etInput.getText().toString();
                iScanbottleCodePresenter.queryBottleByQRCode();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentType == 1) {
                    captureActivityListener.onClickNextBtnAndSendVerifyBottleList(fragmentType, list);
                } else if (fragmentType == 2) {
                    captureActivityListener.onClickNextBtnAndSendVerifyBottleList(fragmentType, empList);
                }

            }
        });
        if (fragmentType == 1) {
            adapter = new ScanBottleQRCodeRclAdapter(list, getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            fiveBottleNumber.setText(BottleCaculteUtil.getBottleNum(list, 5) + "");
            fifteenBottleNumber.setText(BottleCaculteUtil.getBottleNum(list, 15) + "");
            fiftyBottleNumber.setText(BottleCaculteUtil.getBottleNum(list, 50) + "");
            title.setText("请扫描要交付用户钢瓶");


        } else if (fragmentType == 2) {
            adapter = new ScanBottleQRCodeRclAdapter(empList, getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            fiveBottleNumber.setText(BottleCaculteUtil.getBottleNum(list, 5) + "");
            fifteenBottleNumber.setText(BottleCaculteUtil.getBottleNum(list, 15) + "");
            fiftyBottleNumber.setText(BottleCaculteUtil.getBottleNum(list, 50) + "");
            title.setText("请扫描并回收用户钢瓶");
        }

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        String s = fragmentType == 1 ? "交付钢瓶" : "回收钢瓶";
        PopUtil.toastInBottom(s);
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
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String bottleUrl = result.getText();
        if(bottleUrl.length()==10||bottleUrl.length()==8){
            bottleCode = bottleUrl;
        }else if(bottleUrl.contains("id=")){
            int index = bottleUrl.indexOf("id=");
            bottleCode = bottleUrl.substring(index + 3).trim();
        }
        iScanbottleCodePresenter.queryBottleByQRCode();
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
            if ( CameraManager.get().isPreviewing()) {
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

    protected static final long VIBRATE_DURATION = 200L;

    protected void playBeepSoundAndVibrate() {
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
    protected final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };


    @Nullable
   protected CameraInitCallBack callBack;

    /**
     * Set callback for Camera check whether Camera init success or not.
     */
    public void setCameraInitCallBack(CameraInitCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onGetBottleInfoSuccess(VerifyBottleBean verifyBottleBean) {
        if (isContainBottle(list,verifyBottleBean.getData().getBottleCode())) {
            PopUtil.toastInBottom("钢瓶已添加");
        }else if(fragmentType == 1) {
             if (verifyBottleBean.getData().getBottleWeight() == 5&&BottleCaculteUtil.getBottleNum(list,5) >= orderFiveNum ) {
                PopUtil.toastInBottom("5kg钢瓶已达到订单数");
            } else if (verifyBottleBean.getData().getBottleWeight() == 15&&BottleCaculteUtil.getBottleNum(list,15) >= orderFifteenNum) {
                PopUtil.toastInBottom("15kg钢瓶已达到订单数");
            } else if (verifyBottleBean.getData().getBottleWeight() == 50&&BottleCaculteUtil.getBottleNum(list,50) >= orderFiftyNum) {
                PopUtil.toastInBottom("50kg钢瓶已达到订单数");
            } else if(verifyBottleBean.getData().getIsHeavy()== 0){
                list.add(verifyBottleBean);
                adapter.notifyDataSetChanged();
                fiveBottleNumber.setText(String.format("%d",BottleCaculteUtil.getBottleNum(list,5)));
                fifteenBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list,15)));
                fiftyBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list,50)));
            }else {
                 PopUtil.toastInBottom("不能交付空瓶");
             }
        }else if(fragmentType == 2){
            if (verifyBottleBean.getData().getBottleWeight() == 5&&BottleCaculteUtil.getBottleNum(empList,5) >= orderFiveNum ) {
                PopUtil.toastInBottom("5kg钢瓶已达到订单数");
            } else if (verifyBottleBean.getData().getBottleWeight() == 15&&BottleCaculteUtil.getBottleNum(empList,15) >= orderFifteenNum) {
                PopUtil.toastInBottom("15kg钢瓶已达到订单数");
            } else if (verifyBottleBean.getData().getBottleWeight() == 50&&BottleCaculteUtil.getBottleNum(empList,50) >= orderFiftyNum) {
                PopUtil.toastInBottom("50kg钢瓶已达到订单数");
            } else if(verifyBottleBean.getData().getIsHeavy()== 1){
                empList.add(verifyBottleBean);
                adapter.notifyDataSetChanged();
                fiveBottleNumber.setText(String.format("%d",BottleCaculteUtil.getBottleNum(empList,5)));
                fifteenBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(empList,15)));
                fiftyBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(empList,50)));
            }else {
                PopUtil.toastInBottom("不能回收重瓶");
            }
        }
        if (handler == null) {
            handler = new MyCaptureHandler(this, decodeFormats, characterSet, viewfinderView);
        }
            Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
            handler.sendMessageDelayed(reDecode, 1000);

    }

    protected boolean isContainBottle(List<VerifyBottleBean> list,String bottleCode) {
        for (VerifyBottleBean bean : list) {
            if (bean.getData().getBottleCode().equals(bottleCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getBottleCode() {
        return bottleCode;
    }

    @Override
    public int getFragmentType() {
        return fragmentType;
    }

    @Override
    public String getVerifyType() {

        return verifyType;
    }


    public interface CameraInitCallBack {
        /**
         * Callback for Camera init result.
         *
         * @param e If is's null,means success.otherwise Camera init failed with the Exception.
         */
        void callBack(Exception e);
    }

    public interface CaptureActivityListener {
        //fragment发送扫描的List<VerifyBottleBean> list给actvity
        void onClickNextBtnAndSendVerifyBottleList(int fragmentType, List<VerifyBottleBean> list);

        void onCaptureFragmenBackBtnClick(int fragmentType);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.captureActivityListener = (CaptureActivityListener) getActivity();
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }
}
