package com.hencoder.hencoderpracticedraw7.practice;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw7.R;

public class Practice04PropertyValuesHolderLayout extends RelativeLayout {
    View view;
    Button animateBt;

    public Practice04PropertyValuesHolderLayout(Context context) {
        super(context);
    }

    public Practice04PropertyValuesHolderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice04PropertyValuesHolderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = findViewById(R.id.objectAnimatorView);
        animateBt = (Button) findViewById(R.id.animateBt);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用 PropertyValuesHolder.ofFloat() 来创建不同属性的动画值方案
                // 第一个： scaleX 从 0 到 1
                // 第二个： scaleY 从 0 到 1
                // 第三个： alpha 从 0 到 1
                PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0,1);
                PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0,1);
                PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0,1);

                // 然后，用 ObjectAnimator.ofPropertyValuesHolder() 把三个属性合并，创建 Animator 然后执行
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2, holder3);
                animator.start();

                //todo 用下面这种方式为什么第二次会只有 alpha有效
//                view.animate()
//                        .scaleX(0.01f)
//                        .scaleY(0.01f)
//                        .alpha(0.01f)
//                .setListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                    }
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        view.animate()
//                                .scaleX(1f)
//                                .scaleY(1f)
//                                .alpha(1f);
//                    }
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//                    }
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//                    }
//                });

            }
        });
    }
}
