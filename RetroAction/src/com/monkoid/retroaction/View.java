package com.monkoid.retroaction;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
<<<<<<< HEAD
import android.os.Bundle;
import android.util.DisplayMetrics;
=======
import android.os.Bundle;
>>>>>>> a35e5c968769d83680408f98d6d85b68da159581
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class View extends SurfaceView implements SurfaceHolder.Callback{


	private MainThread mainThread_;
	
<<<<<<< HEAD
	private float screenWidth_ = 0;		
	private float screenHeight_ = 0;		
	private int screenDensity_ = 0;
=======
	private float m_ScaleWidth = 480;			// Facteur de grossissement selon la taille de l'écran
	private float m_ScaleHeight = 640;		// Facteur de grossissement selon la taille de l'écran
	private Platform platform;
	private TouchHandler touchHandler;
>>>>>>> a35e5c968769d83680408f98d6d85b68da159581
	private Paint textPaint;
	private TreeRender treeRender;
	
	public View(Context context,Activity _act, Resources _res) {
		super(context);
		// TODO Auto-generated constructor stub
		// adding the callback (this) to the surface holder to intercept events
	      getHolder().addCallback(this);
		 // make the GamePanel focusable so it can handle events
	      setFocusable(true);
	      Log.d("view", "Constructor");
	      mainThread_ = new MainThread(this.getHolder(), this);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.d("View", "onDraw");
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
		this.initTreeRender();
		this.mainThread_.start();
		this.screenWidth_ = this.getWidth();
		this.screenHeight_ = this.getHeight();
		this.screenDensity_ = this.getContext().getResources().getDisplayMetrics().densityDpi;
	}
	
	private void initTreeRender(){
		Terrain t = null;
		this.treeRender.add(new Kangoo());
		switch(this.screenDensity_){
	     case DisplayMetrics.DENSITY_LOW:
	    	 t = new Terrain(this.screenWidth_, this.screenHeight_, 40, 40);
	                break;
	     case DisplayMetrics.DENSITY_MEDIUM:
	    	 t = new Terrain(this.screenWidth_, this.screenHeight_, 20, 20);
	                 break;
	     case DisplayMetrics.DENSITY_HIGH:
	    	 t = new Terrain(this.screenWidth_, this.screenHeight_, 10, 10);
	                 break;
	}
		t.genererCube();
		t.genererCube();
	
		this.treeRender.add(t);

	//	this.treeRender.add(platform = new Platform(100,100));
	//  touchHandler = new TouchHandler(platform);
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}
	
}
