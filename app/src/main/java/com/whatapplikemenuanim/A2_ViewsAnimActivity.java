package com.whatapplikemenuanim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class A2_ViewsAnimActivity extends AppCompatActivity {
    FloatingActionButton fab;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2_views_anim);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        button = (Button) findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),A1_MenuAnimActivity.class));
                /*if (button.getVisibility() == View.INVISIBLE) {
                    enterReveal(button, RevealAnimUtil.AnimPositions.HORIZONTAL_CENTER,350);
                } else {
                    exitReveal(button, RevealAnimUtil.AnimPositions.HORIZONTAL_CENTER,350);
                }*/
            }
        });
    }




}