package com.monkoid.retroaction;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

public class View extends SurfaceView implements SurfaceHolder.Callback{


	private MainThread m_Thread;
	
	private float m_ScaleWidth = 480;			// Facteur de grossissement selon la taille de l'écran
	private float m_ScaleHeight = 640;		// Facteur de grossissement selon la taille de l'écran
	
	public View(Context context,Activity _act, Resources _res) {
		super(context);
		// TODO Auto-generated constructor stub
		// adding the callback (this) to the surface holder to intercept events
	      getHolder().addCallback(this);
		 // make the GamePanel focusable so it can handle events
	      setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		m_Thread = new MainThread(holder, this);
		m_Thread.start();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		this.m_Thread.setRunning(false);
		while (retry) {
			try {
				this.m_Thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
			
	}
	
	protected void update(){
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

	
	public MainThread getMenuThread()
	{
		return this.m_Thread;
	}
	
}
