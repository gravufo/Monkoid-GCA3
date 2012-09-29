package com.monkoid.retroaction;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class View extends SurfaceView implements SurfaceHolder.Callback{
	private MainThread mainThread_;
	private float screenWidth_ = 0;		
	private float screenHeight_ = 0;		
	private int screenDensity_ = 0;
	private Platform platform;
	private TouchHandler touchHandler;
	private Paint textPaint;
	public TreeRender treeRender;
	public Terrain t = null;
	public View(Context context,Activity _act, Resources _res) {
		super(context);
		// TODO Auto-generated constructor stub
		// adding the callback (this) to the surface holder to intercept events
	      getHolder().addCallback(this);
		 // make the GamePanel focusable so it can handle events
	      setFocusable(true);
	      mainThread_ = new MainThread(this.getHolder(), this);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(canvas != null){
			canvas.drawColor(Color.BLACK);
			treeRender.draw(canvas);
		}
	}
	
	protected void update(){
		
	}

	public boolean onTouchEvent(MotionEvent event) {
		touchHandler.handleEvent(event);
		return true;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		BitmapLibrary.getGreen().init(this.getContext());
		this.treeRender = new TreeRender();
		this.mainThread_.start();
		this.screenWidth_ = this.getWidth();
		this.screenHeight_ = this.getHeight();
		this.screenDensity_ = this.getContext().getResources().getDisplayMetrics().densityDpi;
		this.initTreeRender();
	}
	
	private void initTreeRender(){
		switch(this.screenDensity_){
	     case DisplayMetrics.DENSITY_LOW:
	    	 t = new Terrain(this.screenWidth_, this.screenHeight_, 64, 64);
	     break;
	     case DisplayMetrics.DENSITY_MEDIUM:
	    	 t = new Terrain(this.screenWidth_, this.screenHeight_, 64, 64);
	      break;
	     case DisplayMetrics.DENSITY_HIGH:
	    	 t = new Terrain(this.screenWidth_, this.screenHeight_, 64, 64);
	      break;
	}
//		t.genererCube();
//		t.genererCube();
		
		t.parcourirGrille(t.GetGridCenter(), true, new Vector3(0,0) );
		this.treeRender.add(t);

	//	this.treeRender.add(platform = new Platform(100,100));
	  touchHandler = new TouchHandler(platform);
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}
	
}
