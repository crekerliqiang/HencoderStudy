package com.hencoder.hencoderpracticedraw6.practice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hencoder.hencoderpracticedraw6.R;

import static com.hencoder.hencoderpracticedraw6.Utils.dpToPixel;

public class Practice05MultiProperties extends ConstraintLayout {
    Button animateBt;
    ImageView imageView;
    boolean visiable = true;
    public Practice05MultiProperties(Context context) {
        super(context);
    }

    public Practice05MultiProperties(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05MultiProperties(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setScaleX(0);
        imageView.setScaleY(0);
        imageView.setAlpha(0f);
        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 在这里处理点击事件，同时对多个属性做动画
                //旋转 + 平移 + 缩放
                if(visiable){
                    imageView.animate().
                            scaleX(1f).
                            scaleY(1f).
                            alpha(1f).
                            rotation(360).
                            translationX(dpToPixel(200)).
                            setDuration(3000);
                }else{
                    imageView.animate().
                            scaleX(0f).
                            scaleY(0f).
                            alpha(0f).
                            rotation(0f).
                            translationX(dpToPixel(0)).
                            setDuration(3000);
                }
                visiable = !visiable;
            }
        });
    }
}