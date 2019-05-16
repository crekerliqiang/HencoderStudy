package com.hencoder.hencoderpracticedraw3.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice13GetTextBoundsView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    String text1 = "A";
    String text2 = "a";
    String text3 = "J";
    String text4 = "j";
    String text5 = "Â";
    String text6 = "â";
    int top = 200;
    int bottom = 400;
    int[] Ys = {0, 0, 0, 0, 0, 0};

    public Practice13GetTextBoundsView(Context context) {
        super(context);
    }

    public Practice13GetTextBoundsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13GetTextBoundsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(20);
        paint1.setColor(Color.parseColor("#E91E63"));
        paint2.setTextSize(160);

        Rect bounds = new Rect();
        paint2.getTextBounds(text1, 0, text1.length(), bounds);
        Ys[0] = Ys[0] - (bounds.bottom + bounds.top) / 2;

        paint2.getTextBounds(text2, 0, text2.length(), bounds);
        Ys[1] = Ys[1] - (bounds.bottom + bounds.top) / 2;

        paint2.getTextBounds(text3, 0, text3.length(), bounds);
        Ys[2] = Ys[2] - (bounds.bottom + bounds.top) / 2;

        paint2.getTextBounds(text4, 0, text4.length(), bounds);
        Ys[3] = Ys[3] - (bounds.bottom + bounds.top) / 2;

        paint2.getTextBounds(text5, 0, text5.length(), bounds);
        Ys[4] = Ys[4] - (bounds.bottom + bounds.top) / 2;

        paint2.getTextBounds(text6, 0, text6.length(), bounds);
        Ys[5] = Ys[5] - (bounds.bottom + bounds.top) / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(50, top, getWidth() - 50, bottom, paint1);

        int middle = (top + bottom) / 2;
        canvas.drawText(text1, 100, middle + Ys[0], paint2);
        canvas.drawText(text2, 200, middle + Ys[1], paint2);
        canvas.drawText(text3, 300, middle + Ys[2], paint2);
        canvas.drawText(text4, 400, middle + Ys[3], paint2);
        canvas.drawText(text5, 500, middle + Ys[4], paint2);
        canvas.drawText(text6, 600, middle + Ys[5], paint2);


    }
}