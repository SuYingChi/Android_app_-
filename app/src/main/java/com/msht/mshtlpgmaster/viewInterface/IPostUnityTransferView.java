package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.PostTransferBean;

public interface IPostUnityTransferView extends IBaseView{
    String getUrl();

    String getFiveCount();

    String getFifteenCount();

    String getFiftyCount();

    String fiveFullCount();

    String fifteenFullCount();

    String fiftyFullCount();

    String carNum();

    String heavryBottleIds();

    String lightBottleIds();

    String getTransferId();

    void onPostTransUnityfersuccess(PostTransferBean bean);
}
