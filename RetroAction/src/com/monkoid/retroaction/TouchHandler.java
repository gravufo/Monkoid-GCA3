package com.monkoid.retroaction;

import java.util.List;

import android.util.Log;
import android.view.MotionEvent;

public class TouchHandler 
{
	/**
	 * This class will be handling the event sent into handleEvent method
	 */
	public static final String TAG = "TouchHandler";
	private float initialX = 0,
			initialY = 0,
			finalX = 0,
			finalY = 0,
			deltaX = 0,
			deltaY = 0,
			deltaBlockX = 0,
			deltaBlockY = 0;
	private boolean axisY = false,
			axisX = true;
	private Terrain t;
	//private List<Vector3> initialPlatformBlockPos;
	private int pixelSize;

	public TouchHandler(Terrain t, int pixelSize)
	{
		this.t = t;
		this.pixelSize = pixelSize;
	}

	public void handleEvent(MotionEvent event)
	{
		Log.d(TAG, "Event");

		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;

		switch(actionCode)
		{
		case MotionEvent.ACTION_DOWN:
			initialX =  event.getX();
			initialY =  event.getY();

			break;

		case MotionEvent.ACTION_UP:

			break;

		case MotionEvent.ACTION_MOVE:
			Log.d("TouchHandler", "ACTION_MOVE");

			finalX = event.getX();
			finalY = event.getY();

			deltaX = finalX - initialX;
			deltaY = finalY - initialY;

			int deplacemenntIndexX = (int) (deltaX / pixelSize);
			int deplacemenntIndexY = (int) (deltaY / pixelSize);

			
			
			if( deplacemenntIndexX != 0 || deplacemenntIndexY != 0 ){
				t.MovePlatform(deplacemenntIndexX, deplacemenntIndexY);
				initialX =  event.getX();
				initialY =  event.getY();
			}
			
		}


	}
}
