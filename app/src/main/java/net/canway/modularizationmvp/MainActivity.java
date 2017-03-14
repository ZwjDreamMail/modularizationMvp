package net.canway.modularizationmvp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    String[] titles ;
    private FragmentTabHost mHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //获取strings中配置的标题信息
        titles = getResources().getStringArray(R.array.tabhost_title);
        //将对应的Fragment封装成数组
        Class[] f_class = {NewsFragment.class, ThemeFragment.class,MediaFragment.class};
        //定义tabhost被选择时样式
        int[] selector = {R.drawable.news_selector,R.drawable.topic_selector,R.drawable.map_selector};
        //获取对应的tabhost组件
        mHost = (FragmentTabHost)findViewById(R.id.tabs);
        //放置Fragment的容器 id
        mHost.setup(MainActivity.this,getSupportFragmentManager(),R.id.tabContent);

        for(int i=0;i<titles.length;i++){

            View view = getTabView(i,titles,selector);
            // TabSpec相当于TabHost的一个分页
            TabHost.TabSpec host =  mHost.newTabSpec(i+"");
            // 自定义控件的UI添加到host中去
            host.setIndicator(view);
            // 添加标签到tabhost中(即绑定fragment和tabhost)
            mHost.addTab(host, f_class[i],null);
        }

        mHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public View getTabView(int index , String[] titles , int[] image){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.item_tab,null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        TextView title = (TextView) view.findViewById(R.id.title);
        icon.setBackground(getResources().getDrawable(image[index]));
        title.setText(titles[index]);
        return view;

    }
}
