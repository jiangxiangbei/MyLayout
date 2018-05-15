package com.example.admin.mylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by admin on 2018/5/15.
 */

public class LinearLayoutPercent  extends LinearLayout{

    public LinearLayoutPercent(Context context) {
        super(context);
    }

    public LinearLayoutPercent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutPercent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View childAt = getChildAt(i);
            float percentHeight=0;
            float percentWidth=0;
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if(layoutParams instanceof LinearLayoutPercent.LayoutParams) {
                percentHeight = ((LayoutParams) layoutParams).getPercentHeight();
                percentWidth = ((LayoutParams) layoutParams).getPercentWidth();
            }
            if(percentWidth!=0) {
                if(percentWidth>1) {
                    percentWidth=1;
                }
                layoutParams.width= (int) (percentWidth*sizeWidth);


            }
            if(percentHeight!=0) {
                if(percentHeight>1) {
                    percentHeight=1;
                }
                layoutParams.height= (int) (percentHeight*sizeHeight);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams{

        private float percentWidth;
        private float percentHeight;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array=c.obtainStyledAttributes(attrs,R.styleable.RelativeLayoutPercent);
            percentHeight = array.getFloat(R.styleable.RelativeLayoutPercent_layout_heightPercent, percentHeight);
            percentWidth = array.getFloat(R.styleable.RelativeLayoutPercent_layout_widthPercent, percentWidth);
            array.recycle();

        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height, weight);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public float getPercentWidth() {
            return percentWidth;
        }

        public void setPercentWidth(float percentWidth) {
            this.percentWidth = percentWidth;
        }

        public float getPercentHeight() {
            return percentHeight;
        }

        public void setPercentHeight(float percentHeight) {
            this.percentHeight = percentHeight;
        }
    }

}
