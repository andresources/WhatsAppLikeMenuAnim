package com.whatapplikemenuanim;

import static com.utils.RevealAnimUtil.enterRevealView;
import static com.utils.RevealAnimUtil.exitRevealView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.utils.RevealAnimUtil;

public class A1_MenuAnimActivity extends AppCompatActivity {
    //https://guides.codepath.com/android/circular-reveal-animation
    LinearLayout mRevealView;
    private android.transition.Transition.TransitionListener mEnterTransitionListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRevealView = findViewById(R.id.reveal_items);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reveal, menu);
        return true;
    }
    int cx,cy;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clip:
                //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    if (mRevealView.getVisibility() == View.INVISIBLE) {
                        enterRevealView(mRevealView, RevealAnimUtil.AnimPositions.HORIZONTAL_CENTER,350);
                    } else {
                        exitRevealView(mRevealView, RevealAnimUtil.AnimPositions.HORIZONTAL_CENTER,350);
                    }
                return true;

            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void clickk(View view) {
        Toast.makeText(getApplicationContext(), "ewrwe", Toast.LENGTH_SHORT).show();
    }

    public void ccc(View view) {
        Toast.makeText(getApplicationContext(), "ccc" +
                "", Toast.LENGTH_SHORT).show();
    }
}