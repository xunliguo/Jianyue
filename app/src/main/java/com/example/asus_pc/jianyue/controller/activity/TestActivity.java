package com.example.asus_pc.jianyue.controller.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asus_pc.jianyue.R;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class TestActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener, View.OnClickListener {
    private CaptureManager captureManager;
    private boolean isLightOn = false;
    private Button btn_switch;
    private Button btn_hint1;
    private Button btn_hint2;
    private DecoratedBarcodeView mDBV

            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTorchOn() {
        Toast.makeText(this, "torch on", Toast.LENGTH_LONG).show();
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        Toast.makeText(this, "torch off", Toast.LENGTH_LONG).show();
        isLightOn = false;
    }

    private void initView() {
        btn_switch = (Button) findViewById(R.id.btn_switch);
        btn_hint1 = (Button) findViewById(R.id.btn_hint1);
        btn_hint2 = (Button) findViewById(R.id.btn_hint2);
        mDBV = (DecoratedBarcodeView) findViewById(R.id.dbv_custom);

        btn_switch.setOnClickListener(this);
        btn_hint1.setOnClickListener(this);
        btn_hint2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_switch:

                break;
            case R.id.btn_hint1:

                break;
            case R.id.btn_hint2:

                break;
        }
    }
}
