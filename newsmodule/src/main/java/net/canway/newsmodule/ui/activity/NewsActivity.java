package net.canway.newsmodule.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import net.canway.newsmodule.R;

/**
 * @author 张文建 king
 *
 * @email 529169501@qq.com
 *
 * @desc ${TODD}
 *
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
