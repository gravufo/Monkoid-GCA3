package com.monkoid.retroaction;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

	private SurfaceHolder surfaceHolder_;
	private View view_;
	private long lastTime_ = 0;
	
	public MainThread(SurfaceHolder surfaceHolder, View view)
	{
		this.surfaceHolder_ = surfaceHolder;
		this.view_ = view;
	}
	
	@Override
    public void run() {
        Canvas c;
        c = null;
        while(true){
	        try 
	        {
	        	c = this.surfaceHolder_.lockCanvas();
	        	long new_time = System.currentTimeMillis();
	        	 synchronized (this.surfaceHolder_) 
	              {
	        		 if((new_time - lastTime_) > 33)
	        			 this.view_.onDraw(c);
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
