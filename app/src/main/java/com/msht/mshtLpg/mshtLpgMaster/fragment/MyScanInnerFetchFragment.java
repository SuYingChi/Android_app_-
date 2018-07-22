package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.Result;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ScanInnerFetchBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IInnerFetchPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ScanBottleQRCodeRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IinnerFetchView;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyScanInnerFetchFragment extends MyCaptureFragment  implements IinnerFetchView {
    @BindView(R.id.scan_delive_topbar)
    TopBarView topBarView;
    @BindView(R.id.et_scan_qrcode_steel_bottle_number)
    EditText etInput;
    @BindView(R.id.btn_scan_qrcode_query_steel_bottle)
    TextView tvQuery;
    @BindView(R.id.et_employer)
    EditText etEmployer;
    @BindView(R.id.scan_delive_bottom)
    TextView tvNext;
    @BindView(R.id.scan_rcl_deliver_steel_bottle)
    RecyclerView recyclerView;
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
    private String verifyType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            fragmentType = bundle.getInt(Constants.SCANFRAGMENT_TYPE);
            //扫描员工二维码FRAGMENT
            if (fragmentType == 1) {
            }//扫描领瓶二维码FRAGMENT
            else if (fragmentType == 2) {
            }
        } else {
            return;
        }
        CameraManager.init(LPGApplication.getLPGApplicationContext());
        iScanInnerPresenter = new IInnerFetchPresenter(this);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this.getActivity());
    }


    @Override
    public void onInnerFetchComfirmSuccess(ScanInnerFetchBottleBean bean) {
        PopUtil.toastInBottom("内部领瓶成功");
        Objects.requireNonNull(getActivity()).finish();
    }



    @Override
    public String getEmployeeCode() {
        return employerId;
    }


    @Override
    public String getBottleIds() {
        StringBuilder stringBuilder = new StringBuilder();
       for (int i=0;i<list.size();i++){
          String id =  list.get(i).getData().getId()+"";
          if(i==0){
              stringBuilder.append(id);
          }else {
              stringBuilder.append(",").append(id);
          }
       }
        return stringBuilder.toString();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
       if(fragmentType == 1){
            view = inflater.inflate(R.layout.inner_fetch_no_rcl_layout, null);
       } else if(fragmentType == 2){
            view = inflater.inflate(R.layout.inner_fetch_layout, null);
       }
        if (view != null) {
            ButterKnife.bind(this, view);
        }else {
           return null;
        }
        surfaceHolder = surfaceView.getHolder();
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innnerFetchActivityListener.onCaptureFragmenBackBtnClick(fragmentType);
            }
        });
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleCode = etInput.getText().toString();
                iScanInnerPresenter.innerFetchQueryBottle();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentType == 2) {
                   iScanInnerPresenter.innerFetchComfirm();
                } else if (fragmentType == 1&&isEmployerQuerySuccess) {
                    innnerFetchActivityListener.onClickNextBtnAndSendEmployerId(employerId);
                }

            }
        });
        if (fragmentType == 1) {


        } else if (fragmentType == 2) {
            adapter = new ScanBottleQRCodeRclAdapter(list, getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.innnerFetchActivityListener = (InnnerFetchActivityListener) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        String s = fragmentType == 1 ? "扫描员工二维码" : "扫描领瓶二维码";
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
        if(fragmentType == 1){
            employerId = "";
            iScanInnerPresenter.innerFetchQueryEmpolyer();
        } else if(fragmentType == 2) {
            int index = bottleUrl.indexOf("id=");
            bottleCode = bottleUrl.substring(index + 3).trim();
            PopUtil.toastInBottom(result.getText());
            iScanInnerPresenter.innerFetchQueryBottle();
        }
    }

    @Override
    public void onGetInnerFetchBottleInfoSuccess(VerifyBottleBean verifyBottleBean) {
        if (isContainBottle(list,verifyBottleBean.getData().getBottleCode())) {
            PopUtil.toastInBottom("钢瓶已添加");
        }else if(fragmentType == 2) {
             if(verifyBottleBean.getData().getIsHeavy()== 1){
                list.add(verifyBottleBean);
                adapter.notifyDataSetChanged();
            }else {
                PopUtil.toastInBottom("不能回收重瓶");
            }
        }
        Message reDecode = Message.obtain(handler, com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }

    @Override
    public void onGetEmployerInfoSuccess(String employerId) {
        isEmployerQuerySuccess  = true;
        this.employerId = employerId;
    }

    public interface InnnerFetchActivityListener {

        void onClickNextBtnAndSendEmployerId(String employerId);

        void onCaptureFragmenBackBtnClick(int fragmentType);
    }

   @Override
   public  String getBottleCode(){
        return bottleCode;
    }

    @Override
    public String getVerifyType() {

        return verifyType;
    }
}
