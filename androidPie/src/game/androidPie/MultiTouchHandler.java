package game.androidPie;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

public class MultiTouchHandler {
	
	/**
	 * This class will be handling event sent into the handleEvent method
	 */
	public static final String TAG = "MultiTouchHandler";
	private MotionEvent event;
	private Bridge bridge;
	
	/**
	 * Main constructon
	 * Takes in the user's Bridge to be able to perform action on it
	 * @param bridge
	 */
	public MultiTouchHandler(Bridge bridge){
		this.bridge = bridge;
	}
	
	/**
	 * This method is where every touch event are being managed
	 * @param event
	 */
	public void handleEvent(MotionEvent event){
		Log.d(TAG, "Event");
	
		/*if(event.getPointerCount() == 2)
		{
			Point p1 = new Point((int)event.getX(1), (int)event.getY(1));
			Point p2 = new Point((int)event.getX(2), (int)event.getY(2));
			Log.d(TAG, "Multitouch 2 points");
			return new Bridge(p1, p2);
		}*/
		
		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;
		
		switch(actionCode){
		//If the event is a first touch pressed
				case MotionEvent.ACTION_DOWN:
					bridge.setFirstTouch((int)event.getX(), (int)event.getY());
					break;
				
				//If the event is a second touch pressed
				case MotionEvent.ACTION_POINTER_DOWN:
					bridge.setSecondTouch((int)event.getX(1), (int)event.getY(1)); //method are taking the touch id in parameter
					break;
					
					//If the event is a second touch released
				case MotionEvent.ACTION_POINTER_UP:
					bridge.resetP2(); //Reset the bridge's second point
					break;
				
				//If the event is a first touch released (no touch on screen)
				case MotionEvent.ACTION_UP:
					bridge.reset();
					break;
				
				//If the event is a move, handle every touch moved with id
				case MotionEvent.ACTION_MOVE:
					if(bridge.getP1() != null)bridge.setFirstTouch((int)event.getX(0), (int)event.getY(0));
					if(bridge.getP2() != null)bridge.setSecondTouch((int)event.getX(1), (int)event.getY(1));
					break;
		}
	}
}
