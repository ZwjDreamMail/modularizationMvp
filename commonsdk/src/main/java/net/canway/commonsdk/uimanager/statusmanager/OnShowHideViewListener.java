package net.canway.commonsdk.uimanager.statusmanager;

import android.view.View;

/**
 * Created by chenpengfei on 2016/12/15.
 */
public interface OnShowHideViewListener {

    void onShowView(View view, int id);
    void onHideView(View view, int id);
}
