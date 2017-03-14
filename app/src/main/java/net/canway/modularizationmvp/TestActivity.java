package net.canway.modularizationmvp;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * @author 张文建
 * @class Android应用开发实践
 * @desc ${TODD}
 */
public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.text_activity);
    }
}
