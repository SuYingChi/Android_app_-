package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.TransferStorageListBean;
import com.msht.mshtlpgmaster.Bean.UpdateTransferBean;

public interface ITransferToStorageView extends IBaseView{

     String getSiteId();

     String getState();

     String getPageNum();

     String getPageSize();

    void onGetListSuccess(TransferStorageListBean transferStorageListBean);

    String getId();

    String getFiveCount();

    String getFifteenCount();

    String getFifthCount();

    void onUpdateTransferSuccess(UpdateTransferBean updateTransferBean);

    String getFiveFullCount();

    String getFifteenFullCount();

    String getFifthFullCount();
}
