package com.whatapplikemenuanim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class A5_New_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a5_new);
        Fade fade = new Fade();
        getWindow().setEnterTransition(fade);

        // we are also setting fade
        // animation for exit transition.
        getWindow().setExitTransition(fade);

        // initializing our imageview.

        final TextView tv = findViewById(R.id.tv);

        // setting on click listener for our imageview.
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on image click we are opening new activity
                // and adding animation between this two activities.
                Intent intent = new Intent(A5_New_Activity.this, A6_New_Activity.class);
                // below method is used to make scene transition
                // and adding fade animation in it.
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        A5_New_Activity.this, tv, ViewCompat.getTransitionName(tv));
                // starting our activity with below method.
                startActivity(intent, options.toBundle());
            }
        });
    }
}