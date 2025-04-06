package com.example.fancontrol;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class FanControlView extends View {

    private float[] angles = {-135f, -90f, -45f, 0f};
    private String[] labels = {"정지", "미풍", "약풍", "강풍"};

    private int selectedIndex = 0;
    private int buttonOutlineColor = Color.BLACK;
    private int selectedButtonColor = Color.RED;
    private int labelTextColor = Color.BLACK;
    private float labelTextSize = 40f;

    private Paint paint;
    private Paint labelPaint;
    private float[] buttonCentersX = new float[4];
    private float[] buttonCentersY = new float[4];

    public FanControlView(Context context) {
        super(context);
        init(null, 0);
    }

    public FanControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FanControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FanControlView);

            buttonOutlineColor = a.getColor(R.styleable.FanControlView_buttonOutlineColor, buttonOutlineColor);
            selectedButtonColor = a.getColor(R.styleable.FanControlView_selectedButtonColor, selectedButtonColor);
            labelTextColor = a.getColor(R.styleable.FanControlView_labelTextColor, labelTextColor);
            labelTextSize = a.getDimension(R.styleable.FanControlView_labelTextSize, labelTextSize);
            selectedIndex = a.getInt(R.styleable.FanControlView_defaultSelectedIndex, selectedIndex);

            String labelStr = a.getString(R.styleable.FanControlView_labels);
            if (labelStr != null) {
                labels = labelStr.split(",");
            }

            String angleStr = a.getString(R.styleable.FanControlView_buttonAngles);
            if (angleStr != null) {
                String[] parts = angleStr.split(",");
                angles = new float[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    angles[i] = Float.parseFloat(parts[i].trim());
                }
            }

            a.recycle();
        }

        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setColor(labelTextColor);
        labelPaint.setTextSize(labelTextSize);
        labelPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int l = getPaddingLeft(), r = getPaddingRight();
        int t = getPaddingTop(), b = getPaddingBottom();
        int w = getWidth(), h = getHeight();
        int contentWidth = (w - l - r);
        int contentHeight = (h - t - b);

        int cx = l + contentWidth / 2;
        int cy = t + contentHeight / 2;
        int r_outer = Math.min(contentWidth, contentHeight) / 2;

        // 중심 원
        canvas.drawCircle(cx, cy, r_outer, paint);

        float buttonRadius = r_outer * 0.1f;
        float distance = r_outer * 0.7f;

        Paint outlinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(10);
        outlinePaint.setColor(buttonOutlineColor);

        Paint fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(selectedButtonColor);

        for (int i = 0; i < angles.length; i++) {
            double angleRad = Math.toRadians(angles[i]);
            buttonCentersX[i] = (float) (cx + Math.cos(angleRad) * distance);
            buttonCentersY[i] = (float) (cy + Math.sin(angleRad) * distance);

            if (i == selectedIndex) {
                canvas.drawCircle(buttonCentersX[i], buttonCentersY[i], buttonRadius, fillPaint);
                canvas.drawCircle(buttonCentersX[i], buttonCentersY[i], buttonRadius, outlinePaint);
            } else {
                canvas.drawCircle(buttonCentersX[i], buttonCentersY[i], buttonRadius, outlinePaint);
            }

            if (i < labels.length) {
                float textY = buttonCentersY[i] + buttonRadius + labelTextSize;
                canvas.drawText(labels[i], buttonCentersX[i], textY, labelPaint);
            }
        }
    }

    public void setSelectedIndex(int index) {
        if (index >= 0 && index < angles.length) {
            selectedIndex = index;
            invalidate(); // redraw
        }
    }
}
