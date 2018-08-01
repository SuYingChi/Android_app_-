package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.TransferStorageListBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.UpdateTransferBean;

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
}
