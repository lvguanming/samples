/**
 * 
 */
package com.example.overlayview;

import android.content.Context;
import android.gesture.Gesture;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author Rama Palaniappan
 *
 */
public class Container extends RelativeLayout {

	private OuterView1 outerView1 = null;
	private InnerView2 innerView2 = null;
	private GestureDetector outerViewGestureDetector = null;
	private GestureDetector innerViewGestureDetector = null;
	
	/**
	 * @param context
	 */
	public Container(Context context) {
		this(context, null, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Container(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public Container(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View resultView = inflater.inflate(R.layout.layout_container, this, true);

		outerView1 = (OuterView1) resultView.findViewById(R.id.outerView1);
		innerView2 = (InnerView2) resultView.findViewById(R.id.innerView2);
		innerView2.setOuterView(outerView1);
//		outerViewGestureDetector = outerView1.getGestureDetector();
//		innerViewGestureDetector = innerView2.getGestureDetector();
	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
////		outerView1.onTouchEvent(ev);
////		innerView2.onTouchEvent(ev);
//		return false;
//	}
}
