package com.monkoid.retroaction;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Platform implements Drawable
{
	private int x,
		y;
	
	public Bitmap bmp_;
	
	public Platform(int x, int y)
	{
		this.x = x;
		this.y = y;
		bmp_ = BitmapLibrary.getGreen().KangooBmp_;
	}
	
	public void move(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void moveDown()
	{
		
	}
	
	public void moveUp()
	{
		
	}

	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(bmp_, this.x, this.y, null);
	}

	public void initBmp(Bitmap bmp) {
		// TODO Auto-generated method stub
		
	}
}