package com.labd2m.ufv.vidamemoria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView zZXingScannerView;
    private final int CAMERA_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scan(View view){
        requestCameraPermission();
    }

    public void scanQR(){
        zZXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zZXingScannerView);
        zZXingScannerView.setResultHandler(this);
        zZXingScannerView.startCamera();
    }

    public void requestCameraPermission(){
        // verifica a necessidade de pedir a permissão
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            // verifica se precisa explicar para o usuário a necessidade da permissão
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                //explica para o usuário a necessidade da permissão caso ele já tenha negado pelo menos uma vez
                Toast.makeText(this,"Permita o uso da câmera para ler QR Code!",Toast.LENGTH_LONG).show();

                //pede permissão de camera
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION);

                Log.i("Permission","Devo dar explicação");

            } else {

                // Pede a permissão direto a primeira vez que o usuário tentar usar o recurso.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION);

                Log.i("Permission","Pede a permissão");

                // CAMERA_PERMISSION é uma constante declarada para ser usada no callback da resposta da permissão
            }
        }else{
            //faz escaneamento pois já tem permissão
            scanQR();
        }
    }

    //trata resposta do usuário para permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION: {
                // Se a requisição é cancelada, um array vazio é retornado
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permissão foi concedida. Esse ponto deve conter a ação a ser feita neste momento
                    scanQR();

                    Log.i("Permission","Deu a permissão");

                } else {

                    // permissão não foi concedida pelo usuário. Desabilitar recursos que dependem dela
                    Log.i("Permission","Não permitiu");
                }
                return;
            }

            // tratar outros "case" referentes a eventuais novas requisições de permissão de recursos.
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(zZXingScannerView != null)
            zZXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
        intent.putExtra("URL", result.getText());
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_sobre was selected
            case R.id.action_sobre:
                Intent it = new Intent(this, AboutActivity.class);
                startActivity(it);
                break;

            default:
                break;
        }

        return true;
    }

}
