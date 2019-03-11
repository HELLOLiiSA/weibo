package com.example.hellolisa.my_weibo.util;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

/**
 * Created by HELLOLISA on 2018/12/21.
 */

/*
* 用于重写DrawerLayout类的Onmeasure函数的类
* */
public class MyDrawerLayout extends DrawerLayout {

    public MyDrawerLayout(Context context) {

        super(context);

    }



    public MyDrawerLayout(Context context, AttributeSet attrs) {

        super(context, attrs);

    }



    public MyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

    }



    /*
    *
    * 重写的onMeasuer函数
    * */
    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(

                MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY);

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(

                MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }



}