package com.cerkerli.a10xfermode.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.com.library.Util;

public class TransferModeView extends View {

    Paint paintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float RADIUS = Util.dpToPixel(200);

    Bitmap bitmap;

    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);


    public TransferModeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Util.getBitmap(RADIUS);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);//开启离屏缓冲

        paintRect.setColor(Color.GREEN);
        canvas.drawCircle(getWidth()>>1,getHeight()>>1,bitmap.getWidth()>>1 , paintRect);

        paintBitmap.setXfermode(xfermode); // 设置 Xfermode

        paintBitmap.setColor(Color.RED);
        canvas.drawBitmap(bitmap,(getWidth() - bitmap.getWidth()) >> 1,
                (getHeight() - bitmap.getHeight()) >> 1,paintBitmap);



        paintBitmap.setXfermode(null); // 用完及时清除 Xfermode
        canvas.restore();//关闭离屏缓冲
    }
}
