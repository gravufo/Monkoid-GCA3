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
		bmp_ = BitmapLibrary.getGreen().PlatformBmp_;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void move(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void moveX(int x)
	{
		this.x += x;
	}
	
	public void moveY(int y)
	{
		this.y += y;
	}

	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//canvas.drawBitmap(bmp_, this.x + 150, this.y + 250, null);
	}

	public void initBmp(Bitmap bmp) {
		// TODO Auto-generated method stub
		
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}
}
