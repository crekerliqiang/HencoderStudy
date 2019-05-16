package demo.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

public class XView extends View {
    public XView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    Paint paint;
    PathEffect pathEffect;
    Path shapePath;
    Path path;
    {
        shapePath = new Path();
        shapePath.addCircle(20,20,20, Path.Direction.CCW);
        shapePath.close();

        paint = new Paint();

        pathEffect = new PathDashPathEffect(shapePath, 100, 0,
                PathDashPathEffect.Style.TRANSLATE);


        path = new Path();
        path.moveTo(0,0);
        path.rLineTo(800,800);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        paint.setPathEffect(pathEffect);
        canvas.drawPath(path,paint);
//        canvas.drawLine(0,0,800,800,paint);
        canvas.restore();
    }
}
