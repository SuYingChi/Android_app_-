package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.PostTransferBean;

public interface IPostTransferView extends IBaseView {
    String getFiveCount();

    String getFifteenCount();

    String getFiftyCount();

    String carNum();

    String getTransferType();



    String getSiteId();

    String getBottleIds();

    void onPostTransfersuccess(PostTransferBean bean);

    String getUrl();

    String getTransferId();

    String getStationId();
}
