package com.monkoid.retroaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import com.monkoid.retroaction.Drawable;

public class Bloc implements Drawable {
	int couleur;
	int direction;
	Vector position;
	Bitmap image;
	
	public Bloc(int couleur, int direction, Vector position) {
		this.couleur = couleur;
		this.direction = direction;
		this.position = position;
		
	}
	public Bloc() {
	}
	public void actualiserPosition(){
		switch(this.direction){
		case 0 : 
			position.x++;
			break;
		case 1 :
			position.y++;
			break;
		case 2 :
			position.x--;
			break;
		case 3 :
			position.y--;
			break;
		}
	}
	
	public void onDraw(Canvas canvas){
		if(canvas != null)
		{
			canvas.drawBitmap(this.image,position.x, position.y, null);
		}
	}
	public void initBmp(Bitmap bmp) {
		// TODO Auto-generated method stub
		
	}
	
	
}
