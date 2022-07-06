package com.whatapplikemenuanim;

import androidx.appcompat.app.AppCompatActivity;

import android.transition.ChangeBounds;
import android.transition.Fade;
import android.os.Bundle;
import android.view.View;

public class A6_New_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a6_new);
        Fade fade = new Fade();

        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
       // android.transition.ChangeBounds
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
    }
}