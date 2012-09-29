package com.monkoid.retroaction;

import java.util.Random;

import com.monkoid.retroaction.Bloc.BlockType;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

	private SurfaceHolder surfaceHolder_;
	private View view_;
	private long lastTimeDraw_ = 0;
	private long lastTimeCreatBloc_ = 0;
	private long lastTimeUpdateBlocs_ = 0;
	private boolean Curly = true;
	private Random randomizer = null;
	
	public MainThread(SurfaceHolder surfaceHolder, View view)
	{
		this.surfaceHolder_ = surfaceHolder;
		this.view_ = view;
		this.randomizer = new Random();
	}
	
	@Override
    public void run() {
        Canvas c;
        c = null;
        ////Test
        boolean update = false;
        while(Curly){
	        try 
	        {
	        	c = this.surfaceHolder_.lockCanvas(null);
	        	long new_time = System.currentTimeMillis();

	        	 synchronized(this.surfaceHolder_) 
	              {
	        		 if((new_time - lastTimeDraw_) > 10){


	        			 this.view_.onDraw(c);
	        			 lastTimeDraw_ = new_time;
	        		 }

	        		 if((new_time - lastTimeUpdateBlocs_) >500){
	        			 if(this.view_.t != null)
	        				 this.view_.t.onUpdate();
	        			 lastTimeUpdateBlocs_ = new_time;
	        			 Log.d("MainThread", "UpdateBlocs");
	        		 }

	        		 
	        		 if((new_time - lastTimeCreatBloc_) > 500){
	        			this.view_.t.genererCube();
	 					Log.d("MainThread", "BlocGenerator");
	 					lastTimeCreatBloc_ =  new_time;

	        		 }
	        		 
	              }
	        }
	        finally 
	        {
	            // do this in a finally so that if an exception is thrown
	            // during the above, we don't leave the Surface in an
	            // inconsistent state
	            if (c != null) 
	            {
	            	this.surfaceHolder_.unlockCanvasAndPost(c);
	            }
	        }
        }
    }
	

}
