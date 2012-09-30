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

//			initialPlatformBlockPos.clear(); ////////////
//			for( Vector3 v:  t.platformBlocksIndexList){
//				Vector3 newVector = new Vector3(v.x, v.y);
//				initialPlatformBlockPos.add(newVector);
//			}

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

		//			deltaBlockX = deltaX;
		//			deltaBlockY = deltaY;




		//initialX = finalX;
		//initialY = finalY;

		//			if(Math.abs(platform.getX() + platform.getY()) <= 10)
		//			{
		//				axisY = true;
		//				axisX = true;
		//			}
		//			
		//			if(axisX && axisY)
		//			{
		//				// p-e finalX et finalY a la place de delta...
		//				if(Math.abs(deltaX) > Math.abs(deltaY))
		//				{
		//					axisY = false;
		//					
		//					if(deltaX > 0)
		//					{
		//						// platform.moveRight();
		//						// platform.moveX(deltaX);
		//						
		//						if(deltaBlockX > 30)
		//						{
		//							platform.moveX(60);
		//							initialX += 30;
		//						}
		//					}
		//					else
		//					{
		//						// platform.moveLeft();
		//						// platform.moveX(deltaX);
		//						
		//						if(deltaBlockX < -30)
		//						{
		//							platform.moveX(-60);
		//							initialX -= 30;
		//						}
		//					}
		//				}
		//				else
		//				{
		//					axisX = false;
		//					
		//					if(deltaY > 0)
		//					{
		//						// platform.moveDown();
		//						// platform.moveY(deltaY);
		//						
		//						if(deltaBlockY > 30)
		//						{
		//							platform.moveY(60);
		//							initialY += 30;
		//						}
		//					}
		//					else
		//					{
		//						// platform.moveUp();
		//						// platform.moveY(deltaY);
		//						
		//						if(deltaBlockY < -30)
		//						{
		//							platform.moveY(-60);
		//							initialY -= 30;
		//						}
		//					}
		//				}
		//			}
		//			else
		//			{
		//				if(deltaX < 0 && axisX)
		//				{
		//					// send a LEFT movement
		//					// platform.moveLeft();
		//
		//					// platform.moveX(deltaX);
		//					
		//					if(deltaBlockX < -30)
		//					{
		//						platform.moveX(-60);
		//						 initialX -= 30;
		//					}
		//				}
		//				else if(deltaX > 0 && axisX)
		//				{
		//					// send a RIGHT movement
		//					// platform.moveRight();
		//
		//					// platform.moveX(deltaX);
		//					if(deltaBlockX < 30)
		//					{
		//						platform.moveX(60);
		//						initialX += 30;
		//					}
		//				}
		//				
		//				if(deltaY < 0 && axisY)
		//				{
		//					// send an UP movement
		//					// platform.moveUp();
		//					
		//					// platform.moveY(deltaY);
		//					if(deltaBlockY < -30)
		//					{
		//						platform.moveY(-60);
		//						initialY -= 30;
		//					}
		//				}
		//				else if(deltaY > 0 && axisY)
		//				{
		//					// send a DOWN movement
		//					// platform.moveDown();
		//
		//					// platform.moveY(deltaY);
		//					if(deltaBlockY < 30)
		//					{
		//						platform.moveY(60);
		//						initialY += 30;
		//					}
		//				}
		//			}
		//			break;
		//		}
	}
}
