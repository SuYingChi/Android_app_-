package com.msht.mshtLpg.mshtLpgMaster.customView.recycview;

/**
 * Created by wupengjian on 17/12/6.
 */

public interface IScrollingHelper {

    int calculateAndScrollY(int dy);

    int getNestedScrollingTop();
}
