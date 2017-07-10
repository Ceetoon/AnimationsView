package com.ceetoon.animationsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by ceetoon on 2016/5/6.
 */
public class DisScrollViewContent extends LinearLayout {

	public DisScrollViewContent(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setOrientation(LinearLayout.VERTICAL);
	}

	public DisScrollViewContent(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
	}

	public DisScrollViewContent(Context context) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MyLayoutParams(getContext(), attrs);
	}

	@Override
	public void addView(View child, int index,
			android.view.ViewGroup.LayoutParams params) {
		MyLayoutParams myLayoutParams = (MyLayoutParams) params;
		if (isNeedToWrapper(myLayoutParams)) {
			DisScrollavableView disScrollavableView = new DisScrollavableView(
					getContext());
			disScrollavableView
					.setDisscrollview_alpha(myLayoutParams.disscrollview_alpha);
			disScrollavableView
					.setDisscrollview_scaleX(myLayoutParams.disscrollview_scaleX);
			disScrollavableView
					.setDisscrollview_scaleY(myLayoutParams.disscrollview_scaleY);
			disScrollavableView
					.setDisscrollview_fromBg(myLayoutParams.disscrollview_fromBg);
			disScrollavableView
					.setDisscrollview_toBg(myLayoutParams.disscrollview_toBg);
			disScrollavableView
					.setDisscrollview_translation(myLayoutParams.disscrollview_translation);
			disScrollavableView.addView(child);
			super.addView(disScrollavableView, index, myLayoutParams);
		} else {
			super.addView(child, index, params);
		}
	}

	private boolean isNeedToWrapper(MyLayoutParams myLayoutParams) {
		if (myLayoutParams.disscrollview_alpha) {
			return true;
		}
		if (myLayoutParams.disscrollview_scaleX) {
			return true;
		}
		if (myLayoutParams.disscrollview_scaleY) {
			return true;
		}
		if (myLayoutParams.disscrollview_fromBg != -1) {
			return true;
		}
		if (myLayoutParams.disscrollview_toBg != -1) {
			return true;
		}
		if (myLayoutParams.disscrollview_translation != -1) {
			return true;
		}
		return false;
	}

	private class MyLayoutParams extends LayoutParams {
		private boolean disscrollview_alpha;
		private boolean disscrollview_scaleX;
		private boolean disscrollview_scaleY;
		private int disscrollview_fromBg;
		private int disscrollview_toBg;
		private int disscrollview_translation;

		public MyLayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
			TypedArray a = c.getResources().obtainAttributes(attrs,
					R.styleable.DisScrollViewContent_Layout);
			disscrollview_alpha = a
					.getBoolean(
							R.styleable.DisScrollViewContent_Layout_disscrollview_alpha,
							false);
			disscrollview_scaleX = a
					.getBoolean(
							R.styleable.DisScrollViewContent_Layout_disscrollview_scaleX,
							false);
			disscrollview_scaleY = a
					.getBoolean(
							R.styleable.DisScrollViewContent_Layout_disscrollview_scaleY,
							false);
			disscrollview_fromBg = a
					.getColor(
							R.styleable.DisScrollViewContent_Layout_disscrollview_fromBg,
							-1);
			disscrollview_toBg = a.getColor(
					R.styleable.DisScrollViewContent_Layout_disscrollview_toBg,
					-1);
			disscrollview_translation = a
					.getInt(R.styleable.DisScrollViewContent_Layout_disscrollview_translation,
							-1);
			a.recycle();
		}
	}

}
