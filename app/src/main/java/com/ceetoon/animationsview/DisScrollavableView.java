package com.ceetoon.animationsview;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by ceetoon on 2016/5/6.
 */
public class DisScrollavableView extends FrameLayout implements DisScrollavable {
	
	private boolean disscrollview_alpha;
	private boolean disscrollview_scaleX;
	private boolean disscrollview_scaleY;
	private ArgbEvaluator argbEvaluator=new ArgbEvaluator();
	public boolean isDisscrollview_alpha() {
		return disscrollview_alpha;
	}

	public void setDisscrollview_alpha(boolean disscrollview_alpha) {
		this.disscrollview_alpha = disscrollview_alpha;
	}

	public boolean isDisscrollview_scaleX() {
		return disscrollview_scaleX;
	}

	public void setDisscrollview_scaleX(boolean disscrollview_scaleX) {
		this.disscrollview_scaleX = disscrollview_scaleX;
	}

	public boolean isDisscrollview_scaleY() {
		return disscrollview_scaleY;
	}

	public void setDisscrollview_scaleY(boolean disscrollview_scaleY) {
		this.disscrollview_scaleY = disscrollview_scaleY;
	}

	public int getDisscrollview_fromBg() {
		return disscrollview_fromBg;
	}

	public void setDisscrollview_fromBg(int disscrollview_fromBg) {
		this.disscrollview_fromBg = disscrollview_fromBg;
	}

	public int getDisscrollview_toBg() {
		return disscrollview_toBg;
	}

	public void setDisscrollview_toBg(int disscrollview_toBg) {
		this.disscrollview_toBg = disscrollview_toBg;
	}

	public int getDisscrollview_translation() {
		return disscrollview_translation;
	}

	public void setDisscrollview_translation(int disscrollview_translation) {
		this.disscrollview_translation = disscrollview_translation;
	}

	private int disscrollview_fromBg;
	private int disscrollview_toBg;
	private int disscrollview_translation;
	public DisScrollavableView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public DisScrollavableView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DisScrollavableView(Context context) {
		super(context);
	}

	/*
	 * <attr name="disscrollview_translation">
        <flag name="fromLeft" value="0x01"></flag>
        <flag name="fromTop" value="0x02"></flag>
        <flag name="fromBottom" value="0x04"></flag>
        <flag name="fromRight" value="0x08"></flag>
    </attr>
	 */
	private static final int TRASNLATION_FROMLEFT=0x01;
	private static final int TRASNLATION_FROMTOP=0x02;
	private static final int TRASNLATION_FROMRBOTTOM=0x04;
	private static final int TRASNLATION_FROMRIGHT=0x08;
	@Override
	public void onDisScroll(float ration) {
		
		if(disscrollview_alpha){
			setAlpha(ration);
		}
		if(disscrollview_scaleX){
			setScaleX(ration);
		}
		if(disscrollview_scaleY){
			setScaleY(ration);
		}
		if(disscrollview_fromBg!=-1 && disscrollview_toBg!=-1){
			setBackgroundColor((int) argbEvaluator.evaluate(ration, disscrollview_fromBg,disscrollview_toBg));
		}
		if(isTranslation(TRASNLATION_FROMLEFT)){
			setTranslationX(-getWidth()*(1-ration));
		}
		if(isTranslation(TRASNLATION_FROMTOP)){
			setTranslationY(-getHeight()*(1-ration));
		}
		if(isTranslation(TRASNLATION_FROMRIGHT)){
			setTranslationX(getWidth()*(1-ration));
		}
		if(isTranslation(TRASNLATION_FROMRBOTTOM)){
			setTranslationY(getHeight()*(1-ration));
		}
		
	}
	private boolean isTranslation(int mask){
		if(disscrollview_translation==-1){
			return false;
		}
		return ((disscrollview_translation & mask)==mask);
	}

	@Override
	public void onResetDisScroll() {

		if(disscrollview_alpha){
			setAlpha(0);
		}
		if(disscrollview_scaleX){
			setScaleX(0);
		}
		if(disscrollview_scaleY){
			setScaleY(0);
		}
		if(disscrollview_fromBg!=-1 && disscrollview_toBg!=-1){
			setBackgroundColor(disscrollview_fromBg);
		}
		if((disscrollview_translation|TRASNLATION_FROMLEFT)==TRASNLATION_FROMLEFT){
			setTranslationX(0);
		}
		if((disscrollview_translation|TRASNLATION_FROMTOP)==TRASNLATION_FROMTOP){
			setTranslationY(0);
		}
		if((disscrollview_translation|TRASNLATION_FROMRIGHT)==TRASNLATION_FROMRIGHT){
			setTranslationY(0);
		}
		if((disscrollview_translation|TRASNLATION_FROMRBOTTOM)==TRASNLATION_FROMRBOTTOM){
			setTranslationY(0);
		}
		
	
	}
	
}
