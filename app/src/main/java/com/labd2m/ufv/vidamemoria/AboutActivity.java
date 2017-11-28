package com.labd2m.ufv.vidamemoria;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView appVersion = (TextView) findViewById(R.id.versaoSobre);
        PackageInfo pInfo;

        //adiciona o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // botão voltar

        //OBTEM A VERSÃO DO APLICATIVO
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            appVersion.setText("Versão " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sobreVidaMemoria(View view){
        //Representa o endereço que desejamos abrir
        Uri uri = Uri.parse("http://www.vidaememoria.ufv.br");

        //Cria a Intent com o endereço
        Intent it = new Intent(Intent.ACTION_VIEW, uri);

        //Envia a mensagem para o sistema operacional
        startActivity(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //trata evento da actionBar
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
