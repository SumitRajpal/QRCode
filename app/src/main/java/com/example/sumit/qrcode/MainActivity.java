package com.example.sumit.qrcode;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
Button button;
    private IntentIntegrator qrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button);

        qrCode= new IntentIntegrator(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    qrCode.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult( requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(intentResult != null){

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(intentResult.getContents()+""));

            startActivity(browserIntent);
        }
        else{
            Toast.makeText(getApplicationContext()," Empty Result ",Toast.LENGTH_SHORT).show();
        }
    }
}
