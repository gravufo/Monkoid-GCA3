package game.androidPie;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Background {
	
	private int x_, y_;
	private Bitmap image_;
	private static final String TAG = Background.class.getSimpleName();
	
	public Background(Resources r, int imageId){
		image_ = BitmapFactory.decodeResource(r, imageId);
		x_ = y_ = 0;
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(image_, x_, y_, null);
	}

	
	public int getX(){
		return x_;
	}
	
	public int getY(){
		return y_;
	}
	
	public void update(int x){
		if(x_ < (-2000+420))
			x_ = 0;
		else
			x_ -= x; 
	}
	
	public void setX(int x){
		x_ = x;
	}
	
	public void setY(int y){
		y_ = y;
	}

	
}
