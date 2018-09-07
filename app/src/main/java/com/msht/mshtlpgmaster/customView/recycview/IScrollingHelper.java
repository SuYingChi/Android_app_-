package com.msht.mshtlpgmaster.customView.recycview;

/**
 * Created by wupengjian on 17/12/6.
 */

public interface IScrollingHelper {

    int calculateAndScrollY(int dy);

    int getNestedScrollingTop();
}
