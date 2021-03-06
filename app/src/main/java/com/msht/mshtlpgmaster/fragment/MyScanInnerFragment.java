package com.msht.mshtlpgmaster.fragment;

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
import com.msht.mshtlpgmaster.Bean.ScanInnerFetchBottleBean;
import com.msht.mshtlpgmaster.Bean.VerifyBottleBean;
import com.msht.mshtlpgmaster.Present.IInnerFetchPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.adapter.ScanBottleQRCodeRclAdapter;
import com.msht.mshtlpgmaster.application.LPGApplication;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.customView.TopBarView;
import com.msht.mshtlpgmaster.handler.MyScanHandler;
import com.msht.mshtlpgmaster.util.AppUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.IinnerFetchView;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;
import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class MyScanInnerFragment extends BaseFragment implements IinnerFetchView, SurfaceHolder.Callback {
    private static final String TAG = MyScanInnerFragment.class.getSimpleName();
    private int fragmentType;
    private IInnerFetchPresenter iScanInnerPresenter;
    protected InactivityTimer inactivityTimer;
    protected SurfaceHolder surfaceHolder;
    private String bottleCode;
    private InnnerFetchActivityListener innnerFetchActivityListener;
    private List<VerifyBottleBean> list = new ArrayList<VerifyBottleBean>();
    private String employerId;
    private ScanBottleQRCodeRclAdapter adapter;
    private boolean isEmployerQuerySuccess = false;
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
    private RecyclerView recyclerView;
    private SurfaceView surfaceView;
    private TopBarView topBarView;
    private TextView tvNext;
    private TextView tvQuery;
    private EditText etInput;
    private ViewfinderView viewfinderView;
    private TextView tvEmployer;
    private TextView tvTitle;
    private String verifyType;
    private int innerType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            fragmentType = bundle.getInt(Constants.SCANFRAGMENT_TYPE);
            //扫描员工二维码FRAGMENT
            innerType = bundle.getInt("innerType");
        } else {
            return;
        }
        Log.d(TAG, "onCreate: fragmentType     " + fragmentType);
        CameraManager.init(LPGApplication.getLPGApplicationContext());
        iScanInnerPresenter = new IInnerFetchPresenter(this);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this.getActivity());
    }


    @Override
    public void onInnerFetchComfirmSuccess(ScanInnerFetchBottleBean bean) {
        if (innerType == 1) {
            PopUtil.toastInBottom("内部领瓶成功");
        } else if (innerType == 2) {
            PopUtil.toastInBottom("内部返瓶成功");
        }
        Objects.requireNonNull(getActivity()).finish();
    }


    @Override
    public String getEmployeeCode() {
        if (fragmentType == 1) {
            return employerId;
        } else {
            return innnerFetchActivityListener.getEmpolyerId();
        }
    }


    @Override
    public String getBottleIds() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i).getData().getId() + "";
            if (i == 0) {
                stringBuilder.append(id);
            } else {
                stringBuilder.append(",").append(id);
            }
        }
        return stringBuilder.toString();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: fragmentType     " + fragmentType);
        View view = null;
        if (fragmentType == 1) {
            view = inflater.inflate(R.layout.inner_fetch_no_rcl_layout, null);
            tvEmployer = (TextView) view.findViewById(R.id.tv_employer);
            tvTitle = (TextView) view.findViewById(R.id.tv_scan_delive_steel_bottle);
            tvTitle.setText("请扫描员工二维码");
        } else if (fragmentType == 2) {
            view = inflater.inflate(R.layout.inner_fetch_layout, null);
            recyclerView = (RecyclerView) view.findViewById(R.id.scan_rcl_deliver_steel_bottle);
            adapter = new ScanBottleQRCodeRclAdapter(list, getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            tvTitle = (TextView) view.findViewById(R.id.tv_scan_delive_steel_bottle);
            tvTitle.setText("请扫描钢瓶二维码");
        }
        surfaceView = (SurfaceView) view.findViewById(R.id.scan_delive_steel_bottle_preview_view);
        surfaceHolder = surfaceView.getHolder();
        topBarView = (TopBarView) view.findViewById(R.id.scan_delive_topbar);
        tvNext = (TextView) view.findViewById(R.id.tv_scan_delive_bottom);
        tvQuery = (TextView) view.findViewById(R.id.btn_scan_qrcode_query_steel_bottle);
        etInput = (EditText) view.findViewById(R.id.et_scan_qrcode_steel_bottle_number);
        viewfinderView = (ViewfinderView) view.findViewById(R.id.scan_delive_steel_bottle_qrcode_viewfinder_view);

        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innnerFetchActivityListener.onCaptureFragmenBackBtnClick(fragmentType);
            }
        });
        if (innerType == 1) {
            topBarView.setTitle("内部领瓶");
        } else if (innerType == 2) {
            topBarView.setTitle("内部返瓶");
        }

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentType == 2) {
                    if (getInnerType() == 1) {
                        iScanInnerPresenter.innerFetchComfirm();
                    } else if (getInnerType() == 2) {
                        iScanInnerPresenter.innerReturnComfirm();
                    }
                } else if (fragmentType == 1 && isEmployerQuerySuccess) {
                    innnerFetchActivityListener.onClickNextBtnAndSendEmployerId(employerId);
                } else if (fragmentType == 1 && !isEmployerQuerySuccess) {
                    PopUtil.toastInBottom("请验证领取钢瓶的员工");
                }

            }
        });

        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentType == 1) {
                    employerId = etInput.getText().toString();
                    iScanInnerPresenter.innerFetchQueryEmpolyer();
                } else if (fragmentType == 2) {
                    bottleCode = etInput.getText().toString();
                    iScanInnerPresenter.innerFetchQueryBottle();
                }
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
                if (s.toString().length() == 10) {
                    AppUtil.hideInput(MyScanInnerFragment.this.getContext(), etInput);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: fragmentType     " + fragmentType);
        super.onAttach(context);
        this.innnerFetchActivityListener = (InnnerFetchActivityListener) getActivity();

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: fragmentType     " + fragmentType);
        String s = "";
        if (innerType == 1) {
            s = fragmentType == 1 ? "扫描员工二维码" : "扫描领瓶二维码";
        } else if (innerType == 2) {
            s = "扫描返瓶二维码";
        }
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
        if (fragmentType == 1) {
            employerId = bottleUrl;
            tvEmployer.setText(employerId);
            iScanInnerPresenter.innerFetchQueryEmpolyer();
        } else if (fragmentType == 2) {
            if (bottleUrl.length() == 10 || bottleUrl.length() == 8 || bottleUrl.length() == 9) {
                bottleCode = bottleUrl;
            } else if (bottleUrl.contains("id=")) {
                int index = bottleUrl.indexOf("id=");
                bottleCode = bottleUrl.substring(index + 3).trim();
            }
            iScanInnerPresenter.innerFetchQueryBottle();
        }
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
    public void onPause() {
        Log.d(TAG, "onPause: fragmentType     " + fragmentType);
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: fragmentType     " + fragmentType);
        inactivityTimer.shutdown();
    }

    @Override
    public void onGetInnerFetchBottleInfoSuccess(VerifyBottleBean verifyBottleBean) {
        if (isContainBottle(list, verifyBottleBean.getData().getBottleCode())) {
            PopUtil.toastInBottom("钢瓶已添加");
        } else if (fragmentType == 2) {
            if (getInnerType() == 1 && verifyBottleBean.getData().getIsHeavy() == 0) {
                list.add(0,verifyBottleBean);
                adapter.notifyDataSetChanged();
            } else if (getInnerType() == 1 && verifyBottleBean.getData().getIsHeavy() == 1) {
                PopUtil.toastInBottom("不能领空瓶");
            } else if (getInnerType() == 2) {
                list.add(0,verifyBottleBean);
                adapter.notifyDataSetChanged();
            }
        }
        if (handler == null) {
            handler = new MyScanHandler(this, decodeFormats, characterSet, viewfinderView);
        }
        Message reDecode = Message.obtain(handler, R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }

    private boolean isContainBottle(List<VerifyBottleBean> list, String bottleCode) {
        for (VerifyBottleBean bean : list) {
            if (bean.getData().getBottleCode().equals(bottleCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onGetEmployerInfoSuccess() {
        isEmployerQuerySuccess = true;
        tvEmployer.setText(employerId);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: fragmentType     " + fragmentType);
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged: fragmentType     " + fragmentType);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed: fragmentType     " + fragmentType);
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

    public interface InnnerFetchActivityListener {

        void onClickNextBtnAndSendEmployerId(String employerId);

        void onCaptureFragmenBackBtnClick(int fragmentType);

        String getEmpolyerId();

        String getUrl();

        int getInnerType();

        String getVerifyType();
    }

    @Override
    public String getBottleCode() {
        return bottleCode;
    }

    @Override
    public String getVerifyType() {
        return innnerFetchActivityListener.getVerifyType();
    }

    @Override
    public String getUrl() {
        return innnerFetchActivityListener.getUrl();
    }

    @Override
    public int getInnerType() {
        return innnerFetchActivityListener.getInnerType();
    }

    @Override
    public String getSiteId() {
        return SharePreferenceUtil.getLoginSpStringValue("siteId");
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        Message reDecode = Message.obtain(handler, R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }
}
