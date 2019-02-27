package com.example.spacetrader.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.spacetrader.model.Universe;
import com.example.spacetrader.viewmodel.ISpaceMapViewModel;
import com.example.spacetrader.viewmodel.representation.SolarSystemRepresentation;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class SpaceMap extends View {

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
    private Paint textPaint;

    public SpaceMap(Context context) {
        super(context);
        init(null, 0);
    }

    public SpaceMap(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SpaceMap(Context context, AttributeSet attrs, int defStyle) {
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

        textPaint = new TextPaint();
        textPaint.setTextSize(48);

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

        List<SolarSystemRepresentation> systems = viewModel.getSystems();

        // Draw the planets.
        for (int i = 0; i < systems.size(); i++) {
            SolarSystemRepresentation system = systems.get(i);
            canvas.drawCircle(marginX + system.x * scaleFactor, marginY + system.y * scaleFactor, 8, dotPaint);
            canvas.drawText(system.name, marginX + system.x * scaleFactor, marginY + system.y * scaleFactor + 48, textPaint);
        }
    }

    public void setViewModel(ISpaceMapViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
