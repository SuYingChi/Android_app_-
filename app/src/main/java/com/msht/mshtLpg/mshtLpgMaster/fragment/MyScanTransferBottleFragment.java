package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.Result;
import com.msht.mshtLpg.mshtLpgMaster.Bean.PostTransferBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IPostTransferPresenter;
import com.msht.mshtLpg.mshtLpgMaster.Present.IScanbottleCodePresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ScanBottleQRCodeRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.handler.MyScanHandler;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.BottleCaculteUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IPostTransferView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IScanCodeDeliverSteelBottleView;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyScanTransferBottleFragment extends BaseScanFragmengt implements IScanCodeDeliverSteelBottleView, IPostTransferView {
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
    private SurfaceHolder surfaceHolder;
    private IScanbottleCodePresenter iScanbottleCodePresenter;
    private String fiveCount = 0 + "";
    private String fifteenCount = 0 + "";
    private String fiftyCount = 0 + "";
    private String carNum = "";
    private String siteId = "";
    private IPostTransferPresenter iPostTransferPresenter;
    private List<VerifyBottleBean> list = new ArrayList<VerifyBottleBean>();
    private String orderId;
    private MyScanHandler handler;
    private ScanBottleQRCodeRclAdapter adapter;
    private String transferType;
    private String url;
    private String verifyType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundles = getArguments();
        if (bundles != null) {
            orderId = bundles.getString(Constants.ORDER_ID);
            fiveCount = bundles.getString(Constants.ORDER_FIVE_NUM);
            fifteenCount = bundles.getString(Constants.ORDER_FIFTEEN_NUM);
            fiftyCount = bundles.getString(Constants.ORDER_FIFTY_NUM);
            transferType = bundles.getString("TransferType");
            iScanbottleCodePresenter = new IScanbottleCodePresenter(this);
            iPostTransferPresenter = new IPostTransferPresenter(this);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scan_delive_steel_bottle_layout, null);
        ButterKnife.bind(this, view);
        surfaceHolder = surfaceView.getHolder();
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        switch (transferType) {
            //出库
            case "0":
                url = Constants.POST_OUT_REPERTORY;
                topBarView.setTitle("调拨出库");
                title.setText("请扫描要调拨出库钢瓶");
                verifyType = "5";
                break;
            //入库
            case "1":
                url = Constants.POST_INPUT_REPERTORY;
                topBarView.setTitle("调拨入库");
                title.setText("请扫描要调拨入库钢瓶");
                verifyType = "4";
                break;
            default:
                break;
        }
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
                if (BottleCaculteUtil.checkBottleListbyOrderNum(list, Integer.valueOf(fiveCount), Integer.valueOf(fifteenCount), Integer.valueOf(fiftyCount))) {
                    iPostTransferPresenter.postTransferOrders();
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
                    AppUtil.hideInput(MyScanTransferBottleFragment.this.getContext(), etInput);
                }
            }
        });
        adapter = new ScanBottleQRCodeRclAdapter(list, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
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
    }

    @Override
    protected void initCamera(SurfaceHolder surfaceHolder) {
        super.initCamera(surfaceHolder);
        if (handler == null) {
            handler = new MyScanHandler(this, decodeFormats, characterSet, viewfinderView);
        }
    }

    @Override
    public void handleDecode(Result result, Bitmap barcode) {
        super.handleDecode(result, barcode);
        iScanbottleCodePresenter.queryBottleByQRCode();
    }

    @Override
    public void onGetBottleInfoSuccess(VerifyBottleBean verifyBottleBean) {

        if (BottleCaculteUtil.isContainBottle(list, verifyBottleBean.getData().getBottleCode())) {
            PopUtil.toastInBottom("钢瓶已添加");
        } else {
            if (verifyBottleBean.getData().getBottleWeight() == 5 && BottleCaculteUtil.getBottleNum(list, 5) >= Integer.valueOf(fiveCount)) {
                PopUtil.toastInBottom("5kg钢瓶已达到订单数");
            } else if (verifyBottleBean.getData().getBottleWeight() == 15 && BottleCaculteUtil.getBottleNum(list, 15) >= Integer.valueOf(fifteenCount)) {
                PopUtil.toastInBottom("15kg钢瓶已达到订单数");
            } else if (verifyBottleBean.getData().getBottleWeight() == 50 && BottleCaculteUtil.getBottleNum(list, 50) >= Integer.valueOf(fiftyCount)) {
                PopUtil.toastInBottom("50kg钢瓶已达到订单数");
                //空瓶调拨出库单
            } else if (TextUtils.equals(transferType, "0") && verifyBottleBean.getData().getIsHeavy() == 1) {
                list.add(verifyBottleBean);
                adapter.notifyDataSetChanged();
                fiveBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 5)));
                fifteenBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 15)));
                fiftyBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 50)));

            } else if (TextUtils.equals(transferType, "0") && verifyBottleBean.getData().getIsHeavy() == 0) {
                PopUtil.toastInBottom("只能调拨空瓶出库");
            } else if (TextUtils.equals(transferType, "1") && verifyBottleBean.getData().getIsHeavy() == 1) {
                PopUtil.toastInBottom("只能调拨重瓶入库");
            }  //重瓶调拨入库单
            else if (TextUtils.equals(transferType, "1") && verifyBottleBean.getData().getIsHeavy() == 0) {
                list.add(verifyBottleBean);
                adapter.notifyDataSetChanged();
                fiveBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 5)));
                fifteenBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 15)));
                fiftyBottleNumber.setText(String.format("%d", BottleCaculteUtil.getBottleNum(list, 50)));
            } else {
                PopUtil.toastInBottom("不能扫描钢瓶");
            }
        }
        if (handler == null) {
            handler = new MyScanHandler(this, decodeFormats, characterSet, viewfinderView);
        }
        Message reDecode = Message.obtain(handler, R.id.redecode_after_decodeSuccess);
        handler.sendMessageDelayed(reDecode, 1000);
    }

    @Override
    public String getBottleCode() {
        return bottleCode;
    }

    @Override
    public int getFragmentType() {
        return 0;
    }

    @Override
    public String getVerifyType() {

        return verifyType;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getTransferId() {
        return orderId;
    }

    @Override
    public String getStationId() {
        return "1";
    }

    @Override
    public String getFiveCount() {

        return fiveCount;
    }

    @Override
    public String getFifteenCount() {

        return fifteenCount;
    }

    @Override
    public String getFiftyCount() {

        return fiftyCount;
    }

    @Override
    public String carNum() {
        return carNum;
    }

    @Override
    public String getTransferType() {
        return 1 + "";
    }


    @Override
    public String getSiteId() {
        siteId = SharePreferenceUtil.getLoginSpStringValue("siteId");
        return siteId;
    }

    @Override
    public String getBottleIds() {
        return BottleCaculteUtil.getBottleIds(list);
    }

    @Override
    public void onPostTransfersuccess(PostTransferBean bean) {
        if (TextUtils.equals(transferType, "0")) {
            PopUtil.toastInBottom("调拨单出库单已完成");
        } else if (TextUtils.equals(transferType, "1")) {
            PopUtil.toastInBottom("调拨单入库单已完成");
        }
        getActivity().finish();
    }

    @Override
    public void onPause() {
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
        inactivityTimer.shutdown();
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }
}
