package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice14MaskFilterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    BlurMaskFilter maskFilter1;
    BlurMaskFilter maskFilter2;
    BlurMaskFilter maskFilter3;
    BlurMaskFilter maskFilter4;

    public Practice14MaskFilterView(Context context) {
        super(context);
    }

    public Practice14MaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14MaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.what_the_fuck);

        maskFilter1 = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
        maskFilter2 = new BlurMaskFilter(100, BlurMaskFilter.Blur.INNER);
        maskFilter3 = new BlurMaskFilter(100, BlurMaskFilter.Blur.OUTER);
        maskFilter4 = new BlurMaskFilter(100, BlurMaskFilter.Blur.SOLID);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 用 Paint.setMaskFilter 来设置不同的 BlurMaskFilter

        // 第一个：NORMAL
        paint.setMaskFilter(maskFilter1);
        canvas.drawBitmap(bitmap, 100, 50, paint);

        // 第二个：INNER
        paint.setMaskFilter(maskFilter2);
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 200, 50, paint);

        // 第三个：OUTER
        paint.setMaskFilter(maskFilter3);
        canvas.drawBitmap(bitmap, 100, bitmap.getHeight() + 100, paint);

        // 第四个：SOLID
        paint.setMaskFilter(maskFilter4);
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 200, bitmap.getHeight() + 100, paint);
    }
}
