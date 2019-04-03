package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice04BitmapShaderView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);


    public Practice04BitmapShaderView(Context context) {
        super(context);
    }

    public Practice04BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice04BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint1.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap2,600,0,paint2);

        canvas.drawCircle(200, 200, 200, paint1);
    }
}
