package com.monkoid.retroaction;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

	private SurfaceHolder m_surfaceHolder;

	
	public MainThread(SurfaceHolder surfaceHolder)
	{
		this.m_surfaceHolder = surfaceHolder;
	}
	
	@Override
    public void run() {
        Canvas c;
            c = null;
            try 
            {
            	
            }
            finally 
            {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) 
                {
                	//this.m_surfaceHolder.unlockCanvasAndPost(c);
                }
            }
    }
}
