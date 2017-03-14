package net.canway.newsmodule;

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
@Route(path = "/news/activity")
public class NewsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    public void goVedio(View view){
        ARouter.getInstance().build("/vedio/activity").navigation();
    }
}
