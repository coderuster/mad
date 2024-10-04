package com.example.graphicalprimitive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class InteractiveGraphicsView extends View {

    private Paint paint;
    private List<float[]> circles;

    public InteractiveGraphicsView(Context context) {
        super(context);

        // Set up paint object for drawing
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        // List to store circle positions
        circles = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Set background color
        canvas.drawColor(Color.WHITE);

        // Draw each circle stored in the list
        for (float[] circle : circles) {
            canvas.drawCircle(circle[0], circle[1], circle[2], paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Check if the event is a touch down event (i.e., a click)
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Add a new circle at the touch position
            float x = event.getX();
            float y = event.getY();
            float radius = 100;  // You can modify the circle size here

            // Store the circle's position and size in the list
            circles.add(new float[]{x, y, radius});

            // Request to redraw the view
            invalidate();

            return true;
        }
        return super.onTouchEvent(event);
    }
}

