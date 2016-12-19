/**
 * 
 */
package com.example.overlayview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Rama Palaniappan
 * 
 */
public class InnerView2 extends View {

	private Paint paint = null;
	private Paint paintCircle = null;
	private static final String TAG = "InnerView2";
	private GestureDetector gesture = null;

	/**
	 * @param context
	 */
	public InnerView2(Context context) {
		this(context, null, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public InnerView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public InnerView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaints();
		gesture = new GestureDetector(getContext(),
				new InnerViewGestureListener());
	}

	private View outerView = null;
	public void setOuterView(View outerView) {
		this.outerView = outerView;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gesture.onTouchEvent(event);
		if (outerView != null) {
			outerView.onTouchEvent(event);
		}
		return true;
	}

	public GestureDetector getGestureDetector() {
		return gesture;
	}

	public void initPaints() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(5);
		paint.setColor(Color.GRAY);

		paintCircle = new Paint();
		paintCircle.setAntiAlias(true);
		paintCircle.setStrokeJoin(Paint.Join.ROUND);
		paintCircle.setStrokeCap(Paint.Cap.ROUND);
		paintCircle.setStyle(Style.STROKE);
		paintCircle.setStrokeWidth(3);
		paintCircle.setColor(Color.RED);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int left = getPaddingLeft();
		int top = getPaddingTop();
		int right = getWidth() - getPaddingRight();
		int bottom = getHeight() - getPaddingBottom();
		canvas.drawRect(left, top, right, bottom, paint);
		paint.setStrokeWidth(1);
		paint.setTextSize(25);
		canvas.drawText("Inner View2", left, top, paint);

		int widthWithoutPadding = getWidth() - getPaddingLeft()
				- getPaddingRight();
		int heightWithoutPadding = getHeight() - getPaddingTop()
				- getPaddingBottom();

		// Draw circle in the center
		int circleX = widthWithoutPadding / 2 + left;
		int circleY = heightWithoutPadding / 2 + top;
		int radius = 25;
		canvas.drawCircle(circleX, circleY, radius, paintCircle);
		paintCircle.setStrokeWidth(1);
		paintCircle.setTextSize(25);
		canvas.drawText("Tap here", circleX + radius, circleY, paintCircle);
	}

	private class InnerViewGestureListener extends
			GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			Log.i(TAG, "InnerView2.onDown()");
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.i(TAG, "InnerView2.onFling()");
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			Log.i(TAG, "InnerView2.onSingleTapConfirmed()");
			return super.onSingleTapConfirmed(e);
		}

	}

}
