package net.canway.modularizationmvp.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author 张文建
 * @class Android应用开发实践
 * @desc ${TODD}
 */
public class AppAplication extends Application {
    private boolean mDebug;

    @Override
    public void onCreate() {
        super.onCreate();
        //进行ARouter的初始化
        mDebug = isApkDebugable(getApplicationContext());
        if (mDebug) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    /**
     * 判断当前应用是否为debug模式
     * @param context 上下文路径
     * @return  boolean true:debug模式  false:release模式
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

