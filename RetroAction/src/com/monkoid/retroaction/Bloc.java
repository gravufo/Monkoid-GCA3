package com.monkoid.retroaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class Bloc {
	int couleur;
	int direction;
	Vector position;
	
	public Bloc(int couleur, int direction, Vector position) {
		this.couleur = couleur;
		this.direction = direction;
		this.position = position;
	}
	public Bloc() {
		// TODO Auto-generated constructor stub
	}
	public void actualiserPosition(){
		switch(this.direction){
		case 0 : 
			position.y--;
			break;
		case 1 :
			position.x--;
			break;
		case 2 :
			position.y++;
			break;
		case 3 :
			position.x++;
			break;
		}
	}
	
	public void onDraw(Canvas canvas){
		if(canvas != null)
		{
			//Bitmap bloc = BitmapFactory.decodeResource(getRessource(),R.drawable.kangoo);
			//canvas.drawBitmap(bloc, 10, 10, null);
		}
	}
	
	
	
}
