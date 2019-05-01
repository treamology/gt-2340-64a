package com.example.spacetrader.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.spacetrader.model.Universe;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.viewmodel.ISpaceMapViewModel;
import com.example.spacetrader.viewmodel.DisplayedSolarSystem;
import com.example.spacetrader.viewmodel.SolarSystemInfo;

import java.util.List;
import java.util.Objects;

/**
 * TODO: document your custom view class.
 */
public class SpaceMapView extends View {

    private ISpaceMapViewModel viewModel;

    enum ZoomType {
        SYSTEM (3.0f),
        UNIVERSE (1.0f);

        private final float zoomFactor;

        ZoomType(float zoomFactor) {
            this.zoomFactor = zoomFactor;
        }
    }
    // The current subsection the Universe map we are viewing.
    private int viewportWidth;
    private int viewportHeight;

    private Paint boundaryPaint;
    private Paint gridlinePaint;
    private Paint dotPaint;
    private Paint currentSystemDotPaint;
    private Paint visitedSystemDotPaint;
    private Paint textPaint;
    private Paint travelBoundPaint;

    private float scaleFactor;
    private float marginX;
    private float marginY;

    private Rect[] systemRects;
    private SolarSystemInfo lastTouchedSystem;
    private View.OnClickListener clickListener;


    public SpaceMapView(Context context) {
        super(context);
        init();
    }

    public SpaceMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpaceMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void setZoomType() {
        viewportWidth = (int)(Universe.SIZE_X / ZoomType.UNIVERSE.zoomFactor);
        viewportHeight = (int)(Universe.SIZE_Y / ZoomType.UNIVERSE.zoomFactor);

        /*
        switch (ZoomType.UNIVERSE) {
            case SYSTEM:
                break;
            case UNIVERSE:
                //int viewportCenterX = Universe.SIZE_X / 2;
                //int viewportCenterY = Universe.SIZE_Y / 2;
                // How largely points on the map should be rendered, depending on the zoom level.
                //int systemDotSize = 3;
                //int systemTextSize = 12;
        }
        */
        invalidate();
    }

    private void init() {
        setWillNotDraw(false);

        boundaryPaint = new Paint();
        boundaryPaint.setStyle(Paint.Style.STROKE);
        boundaryPaint.setStrokeWidth(4.0f);
        boundaryPaint.setColor(Color.BLACK);

        gridlinePaint = new Paint();
        gridlinePaint.setStrokeWidth(2.0f);
        gridlinePaint.setColor(Color.LTGRAY);

        dotPaint = new Paint();
        dotPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        dotPaint.setColor(Color.BLUE);

        currentSystemDotPaint = new Paint();
        currentSystemDotPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        currentSystemDotPaint.setColor(Color.RED);

        visitedSystemDotPaint = new Paint();
        visitedSystemDotPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        visitedSystemDotPaint.setColor(Color.GREEN);

        textPaint = new TextPaint();
        textPaint.setTextSize(48);

        travelBoundPaint = new Paint();
        travelBoundPaint.setStrokeWidth(4.0f);
        travelBoundPaint.setStyle(Paint.Style.STROKE);
        travelBoundPaint.setColor(Color.DKGRAY);
        travelBoundPaint.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));

        systemRects = new Rect[SolarSystem.MAX_SYSTEMS];
        for (int i = 0; i < systemRects.length; i++) {
            systemRects[i] = new Rect(0, 0, 0, 0);
        }

        setZoomType();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw map boundary
        canvas.drawRect(marginX, marginY, marginX + viewportWidth * scaleFactor, marginY + viewportHeight * scaleFactor, boundaryPaint);

        // Draw vertical gridlines.
        for (int i = 5; i < viewportWidth; i += 5) {
            canvas.drawLine(marginX + i * scaleFactor, marginY, marginX + i * scaleFactor, marginY + viewportHeight * scaleFactor, gridlinePaint);
        }
        // Draw horizontal gridlines.
        for (int i = 5; i < viewportHeight; i += 5) {
            canvas.drawLine(marginX, marginY + i * scaleFactor, marginX + viewportWidth * scaleFactor, marginY + i * scaleFactor, gridlinePaint);
        }

        List<DisplayedSolarSystem> systems = viewModel.getSystems();

        DisplayedSolarSystem currentSystem = null;

        // Draw the planets.
        for (int i = 0; i < systems.size(); i++) {
            DisplayedSolarSystem system = systems.get(i);
            float realX = marginX + system.x * scaleFactor;
            float realY = marginY + system.y * scaleFactor;
            canvas.drawText(system.name, realX, realY + 48, textPaint);
            if (system.currentlyVisiting) {
                canvas.drawCircle(realX, realY, 8, currentSystemDotPaint);
                currentSystem = system;
            } else if (system.visited) {
                canvas.drawCircle(realX, realY, 8, visitedSystemDotPaint);
            } else {
                canvas.drawCircle(realX, realY, 8, dotPaint);
            }
            systemRects[i].bottom = (int)realY + 32;
            systemRects[i].top = (int)realY - 32;
            systemRects[i].right = (int)realX + 32;
            systemRects[i].left = (int)realX - 32;
        }

        // Draw circle indicating where the player can travel
        canvas.drawCircle(marginX + Objects.requireNonNull(currentSystem).x * scaleFactor, marginY + currentSystem.y * scaleFactor, viewModel.getCurrentShipFuel() * scaleFactor, travelBoundPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        scaleFactor = contentHeight > contentWidth
                ? (float) contentWidth / viewportWidth
                : (float) contentHeight / viewportHeight;
        marginX = (contentWidth - (viewportWidth * scaleFactor)) / 2.0f;
        marginY = (contentHeight - (viewportHeight * scaleFactor)) / 2.0f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_UP) {
            int viewX = (int)event.getX();
            int viewY = (int)event.getY();

            for (int i = 0; i < systemRects.length; i++) {
                Rect touchRect = systemRects[i];
                if (touchRect.contains(viewX, viewY)) {
                    lastTouchedSystem = viewModel.getSystemInfo(i);
                    clickListener.onClick(this);
                }
            }
        }
        return true;
    }

    public void setViewModel(ISpaceMapViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public SolarSystemInfo getLastTouchedSystem() {
        return lastTouchedSystem;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
