package com.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class RevealAnimUtil {
    public enum AnimPositions {
        VERTICAL_FROM_BOTTOM_LEFT,VERTICAL_FROM_TOP_RIGHT,HORIZONTAL_CENTER,
        HORIZONTAL_TO_LEFT_BOTTOM,HORIZONTAL_TO_LEFT_TOP,HORIZONTAL_TO_RIGHT_BOTTOM,HORIZONTAL_TO_RIGHT_TOP
    }
    public static void enterReveal1(View revealView, AnimPositions animType,int duration) {
        getXYValues(animType,revealView);
        revealView.setVisibility(View.INVISIBLE);
        int radius = Math.max(revealView.getWidth(), revealView.getHeight());
        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0, radius);
        revealView.setVisibility(View.VISIBLE);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(duration);
        anim.start();
    }
    public static void exitReveal1(View revealView,AnimPositions animType,int duration) {
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
