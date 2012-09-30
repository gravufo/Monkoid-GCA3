package com.monkoid.retroaction;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

	private SurfaceHolder surfaceHolder_;
	private View view_;
	private long lastTimeDraw_ = 0;
	private long lastTimeCreatBloc_ = 0;
	private long lastTimeUpdateBlocs_ = 0;
	public boolean Curly = true;
	public boolean freeze = false;
	
	public MainThread(SurfaceHolder surfaceHolder, View view)
	{
		this.surfaceHolder_ = surfaceHolder;
		this.view_ = view;
	}
	
	@Override
    public void run() {
        Canvas c;
        c = null;
        ////Test
        while(Curly){
	        try 
	        {
	        	c = this.surfaceHolder_.lockCanvas(null);
	        	long new_time = System.currentTimeMillis();

	        	 synchronized(this.surfaceHolder_) 
	              { 
	        		 if((new_time - lastTimeCreatBloc_) > 800 && !freeze){
        				freeze = true;
	        			this.view_.t.genererCube();
	 					lastTimeCreatBloc_ =  new_time;
	 					freeze = false;
	        		 }else if((new_time - lastTimeUpdateBlocs_) > 400 && !freeze){
	        			 if(this.view_.t != null){
	        				 freeze = true;
	        				 this.view_.t.onUpdate();
	        			 }
	        			 lastTimeUpdateBlocs_ = new_time;
	        			 freeze = false;
	        		 }else{//(((new_time - lastTimeDraw_) > 15) && !freeze){
	        			 freeze = true;
	        			 this.view_.onDraw(c);
	        			 lastTimeDraw_ = new_time;
	        			 freeze = false;
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
