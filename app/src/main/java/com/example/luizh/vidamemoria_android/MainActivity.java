package com.example.luizh.vidamemoria_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView zZXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scan(View view){
        zZXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zZXingScannerView);
        zZXingScannerView.setResultHandler(this);
        zZXingScannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        zZXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
        intent.putExtra("URL", result.getText());
        startActivity(intent);
    }

}
