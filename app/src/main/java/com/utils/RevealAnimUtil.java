package com.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.whatapplikemenuanim.A4_New_Activity;

public class RevealAnimUtil {
    public enum AnimPositions {
        VERTICAL_FROM_BOTTOM_LEFT,VERTICAL_FROM_TOP_RIGHT,HORIZONTAL_CENTER,
        HORIZONTAL_TO_LEFT_BOTTOM,HORIZONTAL_TO_LEFT_TOP,HORIZONTAL_TO_RIGHT_BOTTOM,HORIZONTAL_TO_RIGHT_TOP
    }
    public static void starttActivityWithSceneTransitionAnimation(Activity ac,View view,Class<?> clss) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ac, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);
        Intent intent = new Intent(ac, clss);
        intent.putExtra(A4_New_Activity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(A4_New_Activity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.startActivity(ac, intent, options.toBundle());
    }
    public static void enterRevealView(View revealView, AnimPositions animType, int duration) {
        getXYValues(animType,revealView);
        revealView.setVisibility(View.INVISIBLE);
        int radius = Math.max(revealView.getWidth(), revealView.getHeight());
        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0, radius);
        revealView.setVisibility(View.VISIBLE);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(duration);
        anim.start();
    }
    public static void exitRevealView(View revealView, AnimPositions animType, int duration) {
        getXYValues(animType,revealView);
        int radius = Math.max(revealView.getWidth(), revealView.getHeight());
        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(revealView, cx, cy, radius, 0);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(duration);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealView.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }
    public static void enterRevealActivity(View rootLayout,int x,int y,int duration){
        float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
        circularReveal.setDuration(duration);
        //AccelerateInterpolator
        //LinearInterpolator
        circularReveal.setInterpolator(new AccelerateInterpolator());
        // make the view visible and start the animation
        rootLayout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }
    public static void getXYValues(AnimPositions animType,View revealView){
        switch (animType){
            case VERTICAL_FROM_BOTTOM_LEFT:verticalFromBottomLeft(revealView);break;
            case VERTICAL_FROM_TOP_RIGHT:verticalFromTopRight(revealView);break;
            case HORIZONTAL_CENTER:horizontalCenter(revealView);break;
            case HORIZONTAL_TO_LEFT_BOTTOM:horizontalToLeftBottom(revealView);break;
            case HORIZONTAL_TO_LEFT_TOP:horizontalToLeftTop(revealView);break;
            case HORIZONTAL_TO_RIGHT_TOP:horizontalToRightTop(revealView);break;
            case HORIZONTAL_TO_RIGHT_BOTTOM:horizontalToRightBottom(revealView);break;
        }
    }
    public static void loadAnim(View rootLayout, int revealX, int revealY, Activity ac,int duration) {
        rootLayout.setVisibility(View.INVISIBLE);
        ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        enterRevealActivity(rootLayout,revealX,revealY,duration);
                    } else {
                        ac.finish();
                    }
                    rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }
    public static void unRevealActivity(View rootLayout,int revealX,int revealY,Activity ac,int duation) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ac.finish();
        } else {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, revealX, revealY, finalRadius, 0);
            circularReveal.setDuration(duation);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    rootLayout.setVisibility(View.INVISIBLE);
                    ac.finish();
                }
            });
            circularReveal.start();
        }
    }
    static int cx, cy =0;
    private static void horizontalCenter(View revealView){
        cx = (revealView.getLeft() + revealView.getRight())/2;
        cy = (revealView.getTop() + revealView.getBottom())/2;
    }
    private static void horizontalToLeftBottom(View revealView){
        cx = revealView.getLeft();
        cy = revealView.getBottom();
    }
    private static void horizontalToLeftTop(View revealView){
        cx = revealView.getLeft();
        cy = revealView.getTop();
    }
    private static void horizontalToRightTop(View revealView){
        cx = revealView.getRight();
        cy = revealView.getTop();
    }
    private static void horizontalToRightBottom(View revealView){
        cx = revealView.getRight();
        cy = revealView.getBottom();
    }
    private static void verticalFromTopRight(View revealView){
        cx = revealView.getBottom()/2;
        cy = revealView.getTop()/2;
    }
    private static void verticalFromBottomLeft(View revealView){
        cx = revealView.getTop();
        cy = revealView.getBottom();
    }
}
