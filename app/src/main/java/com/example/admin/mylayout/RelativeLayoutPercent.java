package com.example.admin.mylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by admin on 2018/5/15.
 */

public class RelativeLayoutPercent extends RelativeLayout {
    public RelativeLayoutPercent(Context context) {
        this(context,null);
    }

    public RelativeLayoutPercent(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RelativeLayoutPercent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
//        测量出子空间的宽高进行改变
        int childCount = this.getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View child = this.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
//            解析自定义的，进行替换，替换为直接的布局宽高
            float widthPercent=0;
            float heightPercent=0;
            if(layoutParams instanceof RelativeLayoutPercent.LayoutParams) {
                widthPercent=((LayoutParams) layoutParams).getWidthPercent();
                heightPercent = ((LayoutParams) layoutParams).getHeightPercent();
            }
//            容错
            if(widthPercent!=0) {
                if(widthPercent>1) {
                    widthPercent=1;
                }
                layoutParams.width=(int)(width*widthPercent);
            }
            if(heightPercent!=0) {
                if(heightPercent>1) {
                    heightPercent=1;
                }
                layoutParams.height= (int) (height*heightPercent);
            }

        }

        //因为继承Relativelayout已经测量过了，不需要再次测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    public static class  LayoutParams extends RelativeLayout.LayoutParams{

        private float widthPercent;
        private float heightPercent;



        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
//            获取自定义属性
            TypedArray array= c.obtainStyledAttributes(attrs,R.styleable.RelativeLayoutPercent);
            widthPercent = array.getFloat(R.styleable.RelativeLayoutPercent_layout_widthPercent, widthPercent);
            heightPercent = array.getFloat(R.styleable.RelativeLayoutPercent_layout_heightPercent, heightPercent);
//          释放资源
            array.recycle();

        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }


        public float getWidthPercent() {
            return widthPercent;
        }

        public void setWidthPercent(float widthPercent) {
            this.widthPercent = widthPercent;
        }

        public float getHeightPercent() {
            return heightPercent;
        }

        public void setHeightPercent(float heightPercent) {
            this.heightPercent = heightPercent;
        }
    }
}
