package com.ceetoon.animationsview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 * Created by ceetoon on 2016/5/6.
 */
@SuppressLint("Recycle")
public class DisScrollView extends ScrollView {
	private DisScrollViewContent disScrollViewContent;

	public DisScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		disScrollViewContent=(DisScrollViewContent) getChildAt(0);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		View child=disScrollViewContent.getChildAt(0);
		if(child!=null){
			child.getLayoutParams().height=getHeight();
		}
	}

	

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		for (int i = 0; i < disScrollViewContent.getChildCount(); i++) {
			View child = disScrollViewContent.getChildAt(i);
			if (!(child instanceof FrameLayout))
				continue;
			float scrollHeight = getHeight();
			float top = child.getTop();
			float gap = scrollHeight - (top - t);
			float ratio = gap / child.getHeight();
			System.out.println("ratio:"+ratio);
			DisScrollavableView disScrollavableView = (DisScrollavableView) child;
			if ((top - t) <= scrollHeight) {
				disScrollavableView.onDisScroll(Math.min(ratio, 1.0f));
			} else {
				disScrollavableView.onResetDisScroll();
			}
		}
	}

	

}
