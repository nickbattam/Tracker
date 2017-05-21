package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;


/**
 * Extend the axis renderer to handle multi-line data, separated with '\n'
 *
 * Based on https://github.com/fabriziogueli/MPAndroidChart/tree/axis_multiline_labels
 */
public class MultiLineXAxisRenderer extends XAxisRenderer {

    private static String NEW_LINE = "\n";

    public MultiLineXAxisRenderer(XAxisRenderer xAxisRenderer) {
        super(xAxisRenderer.mViewPortHandler, xAxisRenderer.mXAxis, xAxisRenderer.mTrans);
    }

    @Override
    protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
        drawXMultiLineText(c, formattedLabel, x, y, mAxisLabelPaint, anchor, angleDegrees);
    }


    private static Rect mDrawTextRectBuffer = new Rect();
    private static Paint.FontMetrics mFontMetricsBuffer = new Paint.FontMetrics();

    private static void drawXMultiLineText(Canvas c, String text, float x, float y,
                                          Paint paint,
                                          MPPointF anchor, float angleDegrees) {


        String firstTextLine = text.split(NEW_LINE)[0];

        float drawOffsetX = 0.f;
        float drawOffsetY = 0.f;

        final float lineHeight = paint.getFontMetrics(mFontMetricsBuffer);
        paint.getTextBounds(firstTextLine, 0, firstTextLine.length(), mDrawTextRectBuffer);

        // Android sometimes has pre-padding
        drawOffsetX -= mDrawTextRectBuffer.left;

        // Android does not snap the bounds to line boundaries,
        //  and draws from bottom to top.
        // And we want to normalize it.
        drawOffsetY += -mFontMetricsBuffer.ascent;

        // To have a consistent point of reference, we always draw left-aligned
        Paint.Align originalTextAlign = paint.getTextAlign();
        paint.setTextAlign(Paint.Align.LEFT);

        if (angleDegrees != 0.f) {

            // Move the text drawing rect in a way that it always rotates around its center
            drawOffsetX -= mDrawTextRectBuffer.width() * 0.5f;
            drawOffsetY -= lineHeight * 0.5f;

            float translateX = x;
            float translateY = y;

            // Move the "outer" rect relative to the anchor, assuming its centered
            if (anchor.x != 0.5f || anchor.y != 0.5f) {
                final FSize rotatedSize = Utils.getSizeOfRotatedRectangleByDegrees(
                        mDrawTextRectBuffer.width(),
                        lineHeight,
                        angleDegrees);

                translateX -= rotatedSize.width * (anchor.x - 0.5f);
                translateY -= rotatedSize.height * (anchor.y - 0.5f);
                FSize.recycleInstance(rotatedSize);
            }

            c.save();
            c.translate(translateX, translateY);
            c.rotate(angleDegrees);

            for (String line : text.split("\n")) {
                c.drawText(line, drawOffsetX, drawOffsetY, paint);
                drawOffsetY += paint.descent() - paint.ascent();

            }

            c.restore();
        } else {
            if (anchor.x != 0.f || anchor.y != 0.f) {
                drawOffsetX -= mDrawTextRectBuffer.width() * anchor.x;
                drawOffsetY -= lineHeight * anchor.y;
            }

            drawOffsetX += x;
            drawOffsetY += y;

            for (String line : text.split(NEW_LINE)) {
                c.drawText(line, drawOffsetX, drawOffsetY, paint);
                drawOffsetY += paint.descent() - paint.ascent();
            }

        }

        paint.setTextAlign(originalTextAlign);
    }
}
