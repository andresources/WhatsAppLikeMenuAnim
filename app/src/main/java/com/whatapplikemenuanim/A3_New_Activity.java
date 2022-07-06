package com.whatapplikemenuanim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.utils.RevealAnimUtil;

public class A3_New_Activity extends AppCompatActivity {
    private Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3_new);
        btnCreate = (Button) findViewById(R.id.btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevealAnimUtil.starttActivityWithSceneTransitionAnimation(A3_New_Activity.this,v,A4_New_Activity.class);
            }
        });
    }


}