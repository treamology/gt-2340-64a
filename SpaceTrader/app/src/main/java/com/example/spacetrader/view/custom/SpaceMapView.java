package com.example.spacetrader.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.spacetrader.model.Universe;
import com.example.spacetrader.viewmodel.ISpaceMapViewModel;
import com.example.spacetrader.viewmodel.modeldisplay.DisplayedSolarSystem;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class SpaceMapView extends View {

    private ISpaceMapViewModel viewModel;

    enum ZoomType {
        SYSTEM (3.0f),
        UNIVERSE (1.0f);

        private float zoomFactor;

        ZoomType(float zoomFactor) {
            this.zoomFactor = zoomFactor;
        }
    }
    // The current subsection the Universe map we are viewing.
    private int viewportWidth;
    private int viewportHeight;
    private int viewportCenterX;
    private int viewportCenterY;

    // How largely points on the map should be rendered, depending on the zoom level.
    private int systemDotSize;
    private int systemTextSize;

    private Paint boundaryPaint;
    private Paint gridlinePaint;
    private Paint dotPaint;
    private Paint currentSystemDotPaint;
    private Paint textPaint;
    private Paint travelBoundPaint;

    public SpaceMapView(Context context) {
        super(context);
        init(null, 0);
    }

    public SpaceMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SpaceMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setZoomType(ZoomType type) {
        viewportWidth = (int)(Universe.SIZE_X / type.zoomFactor);
        viewportHeight = (int)(Universe.SIZE_Y / type.zoomFactor);

        switch (type) {
            case SYSTEM:
                break;
            case UNIVERSE:
                viewportCenterX = Universe.SIZE_X / 2;
                viewportCenterY = Universe.SIZE_Y / 2;
                systemDotSize = 3;
                systemTextSize = 12;
        }
        invalidate();
    }

    private void init(AttributeSet attrs, int defStyle) {
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

        textPaint = new TextPaint();
        textPaint.setTextSize(48);

        travelBoundPaint = new Paint();
        travelBoundPaint.setStrokeWidth(2.0f);
        travelBoundPaint.setStyle(Paint.Style.STROKE);
        travelBoundPaint.setColor(Color.BLACK);
        travelBoundPaint.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));

        setZoomType(ZoomType.UNIVERSE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        float scaleFactor = contentHeight > contentWidth
                ? (float)contentWidth / viewportWidth
                : (float)contentHeight / viewportHeight;
        float marginX = (contentWidth - (viewportWidth * scaleFactor)) / 2.0f;
        float marginY = (contentHeight - (viewportHeight * scaleFactor)) / 2.0f;

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
            canvas.drawText(system.name, marginX + system.x * scaleFactor, marginY + system.y * scaleFactor + 48, textPaint);
            if (system.currentlyVisiting) {
                canvas.drawCircle(marginX + system.x * scaleFactor, marginY + system.y * scaleFactor, 8, currentSystemDotPaint);
                currentSystem = system;
            } else {
                canvas.drawCircle(marginX + system.x * scaleFactor, marginY + system.y * scaleFactor, 8, dotPaint);
            }
        }

        // Draw circle indicating where the player can travel
        canvas.drawCircle(marginX + currentSystem.x * scaleFactor, marginY + currentSystem.y * scaleFactor, viewModel.getCurrentShipFuel() * scaleFactor, travelBoundPaint);
    }

    public void setViewModel(ISpaceMapViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
