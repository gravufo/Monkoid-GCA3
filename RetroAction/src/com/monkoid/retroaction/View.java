package com.monkoid.retroaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class View extends SurfaceView implements SurfaceHolder.Callback{
	public MainThread mainThread_;
	private float screenWidth_ = 0;		
	private float screenHeight_ = 0;		
	private int screenDensity_ = 0;
	private TouchHandler touchHandler;
	public TreeRender treeRender;
	public Terrain t = null;
	public Activity activity;
	
	public View(Context context,Activity _act, Resources _res) {
		super(context);
		activity =_act;
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
		
		this.treeRender = new TreeRender();

		this.screenWidth_ = this.getWidth();
		this.screenHeight_ = this.getHeight();
		this.screenDensity_ = this.getContext().getResources().getDisplayMetrics().densityDpi;
		
		BitmapLibrary.getGreen().init(this.getContext(),getSizeofblocks(this.getContext().getResources().getDisplayMetrics().densityDpi) );
		this.initTreeRender();
		this.mainThread_.start();
	}
	
	private int getSizeofblocks(int density){
		switch(density){
	     case DisplayMetrics.DENSITY_LOW:
	    	 return 16;
	     case DisplayMetrics.DENSITY_MEDIUM:
	     case DisplayMetrics.DENSITY_HIGH:
	    	return 16;
	      default:
	    	 return 32;
	      
		}
	}
	
	private void initTreeRender(){
		int size = getSizeofblocks(this.screenDensity_);
	     t = new Terrain(this.screenWidth_, this.screenHeight_, size, size);
	     Intent i = new Intent(activity,MainMenu.class);
	     TimeCounter c = new TimeCounter(45000,i,activity,this);//45000 milliseconds
	     //c.activity = activity;
	     
		//t.parcourirGrille(t.GetGridCenter(), true, new Vector3(0,0) );
		this.treeRender.add(t);
		this.treeRender.add(c);
		//this.treeRender.add(platform = new Platform(0,0));
		touchHandler = new TouchHandler(t, 16);
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}
	
}
