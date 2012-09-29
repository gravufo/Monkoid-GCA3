package com.monkoid.retroaction;

import android.util.Log;
import android.view.MotionEvent;

public class TouchHandler 
{
	/**
	 * This class will be handling the event sent into handleEvent method
	 */
	public static final String TAG = "TouchHandler";
	private int initialX = 0,
			initialY = 0,
			finalX = 0,
			finalY = 0,
			deltaX = 0,
			deltaY = 0;
	private Platform platform;
	
	public TouchHandler(Platform platform)
	{
		this.platform = platform;
	}
	
	public void handleEvent(MotionEvent event)
	{
		Log.d(TAG, "Event");
		
		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;
		
		switch(actionCode)
		{
		case MotionEvent.ACTION_DOWN:
			initialX = (int) event.getX();
			initialY = (int) event.getY();
			break;
			
		case MotionEvent.ACTION_UP:
			finalX = (int) event.getX();
			finalY = (int) event.getY();
			
			deltaX = finalX - initialX;
			deltaY = finalY - initialY;
			
			if(deltaX < 0)
			{
				// send a LEFT movement
			}
			else
			{
				// send a RIGHT movement
			}
			
			if(deltaY < 0)
			{
				// send an UP movement 
			}
			else
			{
				// send an DOWN movement
			}
			break;

		case MotionEvent.ACTION_MOVE:
			Log.d("TouchHandler", "ACTION_MOVE");
			break;
		}
	}
}
