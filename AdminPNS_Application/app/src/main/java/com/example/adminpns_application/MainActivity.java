package com.example.adminpns_application;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtUser= findViewById(R.id.edtAccout);
        EditText edtPass= findViewById(R.id.edtPhone);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user= edtUser.getText().toString();
                String pass= edtPass.getText().toString();

                if(user.equals("admin") && pass.equals("123456789")){
                    startActivity(new Intent(MainActivity.this,Menu_Admin_Activity.class));

                }else {
                    Toast.makeText(MainActivity.this, "Sai rồi ngu  !!!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}