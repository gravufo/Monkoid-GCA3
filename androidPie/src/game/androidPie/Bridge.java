package game.androidPie;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class Bridge {
	/**
	 * This class represent the bridge on the user's interface. 
	 */
	public static final String TAG = "Bridge";
	private Point p1, p2;
	private Paint paint;
	private double m, b; // Equation of line : y = mx+b
	private int XMAX_ = 600;
	private boolean drawing = false;
	
	/**
	 * Main constructor
	 * Initializing the bridge's point to null and setting the bridge's paint
	 */
	public Bridge(){
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(4);
		this.p1 = null;
		this.p2 = null;
	}
	
	public Bridge(Point p1, Point p2){
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(4);
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Sets the first point of the bridge
	 * @param x
	 * @param y
	 */
	public void setFirstTouch(int x, int y){
		if(x < XMAX_)
			if(p1 == null)
				this.p1 = new Point(x,y);
			else setP1(x,y);
	}
	
	/**
	 * Sets the second point of the bridge depending if first point as been set
	 * @param x
	 * @param y
	 */
	public void setSecondTouch(int x, int y){
		if(this.p1 != null && x < XMAX_){
			if(p2 == null)
				this.p2 = new Point(x,y);
			else setP2(x, y);
		}
	}
	
	
	/**
	 * Drawing the bridge on the canvas sent in parameter
	 * If only first point has been set, will only draw a circle around first point
	 * If both point has been set, will draw circle around both and a line joining them
	 * If no point has been set, will draw nothing
	 * @param canvas
	 */
	@SuppressWarnings("finally")
	public void draw(Canvas canvas){
		Log.d(TAG, "BridgeDraw");
		drawing = true;
		if(this.p1 == null){
		}else if(this.p2 == null){
			canvas.drawCircle((int)p1.x, (int)p1.y, 10, this.paint);
			//Log.d(TAG, "Drawing Circle 1");
		}else{
			//Log.d(TAG, "Drawing completed bridge");
			try{
			canvas.drawCircle((int)p1.x, (int)p1.y, 10, this.paint);
			canvas.drawCircle((int)p2.x, (int)p2.y, 10, this.paint);
			canvas.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y, this.paint);
			}
			finally{
				return;
			}
		}
		Log.d(TAG, "Bridge Finished Drawing");
		drawing = false;
	}
	
	
	public Point closestPoint(Point p3){
		final double xDelta = p2.x - p1.x;
		final double yDelta = p2.y - p1.y;

		if ((xDelta == 0) && (yDelta == 0)) {
		//throw new IllegalArgumentException("p1 and p2 cannot be the same point");
		}

		final double u = ((p3.x - p1.x) * xDelta + (p3.y - p1.y) * yDelta) / (xDelta * xDelta + yDelta * yDelta);

		final Point closestPoint;
		if (u < 0) {
		closestPoint = p1;
		} else if (u > 1) {
		closestPoint = p2;
		} else {
		closestPoint = new Point((int)(p1.x + u * xDelta), (int)(p1.y + u * yDelta));
		}
		
		return closestPoint;
		}
	
	/**
	 * Resetting both point to null
	 */
	public void reset(){
		if(!drawing){
			this.p1 = null;
			this.p2 = null;
			Log.d(TAG, "Both Point reset");
		}
	}
	
	// LOOK IF POINTS ARE SET
	
	public void resetP1(){
		this.p1 = null;
		Log.d(TAG, "Point 1 reset");
	}
	
	public void resetP2(){
		this.p2 = null;
		Log.d(TAG, "Point 2 reset");
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}
	
	public void setP1(int x, int y){
		p1.x = x;
		p1.y = y;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}
	
	public void setP2(int x, int y){
		p2.x = x;
		p2.y = y;
	}
}
