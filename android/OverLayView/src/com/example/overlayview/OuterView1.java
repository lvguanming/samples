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
public class OuterView1 extends View {

	private Paint paint = null;
	private static final String TAG = "OuterView1";
	private GestureDetector gesture = null;

	/**
	 * @param context
	 */
	public OuterView1(Context context) {
		this(context, null, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public OuterView1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public OuterView1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaints();
		gesture = new GestureDetector(getContext(),
				new OuterViewGestureListener());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gesture.onTouchEvent(event);
		return true;
	}
	
	public GestureDetector getGestureDetector() {
		return gesture;
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
		canvas.drawText("Outer View1", left, top, paint);
	}

	private void initPaints() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(5);
		paint.setColor(Color.BLUE);
	}
	
	private class OuterViewGestureListener extends
			GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			Log.i(TAG, "OuterView1.onDown()");
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.i(TAG, "OuterView1.onFling()");
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			Log.i(TAG, "OuterView1.onSingleTapConfirmed()");
			return super.onSingleTapConfirmed(e);
		}

	}

}
