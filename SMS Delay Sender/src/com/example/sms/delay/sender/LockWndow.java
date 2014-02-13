package com.example.sms.delay.sender;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LockWndow extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_window);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lock_wndow, menu);
        return true;
       
    }
}
