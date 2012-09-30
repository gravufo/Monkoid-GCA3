package com.monkoid.retroaction;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Kangoo implements Drawable{

	Bitmap bmp_;
	public Kangoo(){
		bmp_ = BitmapLibrary.getGreen().KangooBmp_;
	}
	
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(canvas != null){
			canvas.drawBitmap(bmp_, 10, 10, null);
		}
	}

	public void initBmp(Bitmap bmp) {
		// TODO Auto-generated method stub
		bmp_ = bmp;
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}	
}
