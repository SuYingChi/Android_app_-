package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ScanBottleQRCodeBean;

public interface IScanCodeDeliverSteelBottleView extends IBaseView {
    void onGetBottleInfoSuccess(ScanBottleQRCodeBean scanBottleQRCodeBean);
    String getBottleId();

}
