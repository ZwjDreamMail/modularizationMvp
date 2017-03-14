package net.canway.vediomodule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author 张文建
 * @class Android应用开发实践
 * @desc ${TODD}
 */
@Route(path = "/vedio/activity")
public class VedioActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);
    }
    public void goNews(View view){
        ARouter.getInstance().build("/news/activity").navigation();
    }

}
