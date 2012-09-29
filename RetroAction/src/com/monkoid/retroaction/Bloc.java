package com.monkoid.retroaction;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import com.monkoid.retroaction.Drawable;

public class Bloc implements Drawable {
	int couleur;
	int direction;
	Vector3 position;
	Bitmap image;
	int size = 0;
	
	enum BlockType { INVISIBLE, PLATEFORME, GREF, RACINE}
	BlockType type;

	public Bloc(int couleur, int direction, Vector3 position) {
		this.couleur = couleur;
		this.direction = direction;
		this.position = position;
		this.type = BlockType.GREF;
	}
	
	public Bloc(int i, int j, int size) {
		this.size = size;
		this.type = BlockType.INVISIBLE;
		this.position = new Vector3(i,j);
		////////TEST///////////
		Random generateur = new Random();
		this.couleur =  Math.abs(generateur.nextInt()%5);
		switch(this.couleur){
		case 0 : 
			this.image = BitmapLibrary.getGreen().BlocblueBmp_;
			break;
		case 1 :
			this.image = BitmapLibrary.getGreen().BlocgreenBmp_;
			break;
		case 2 :
			this.image = BitmapLibrary.getGreen().BlocpurpleBmp_;
			break;
		case 3 :
			this.image = BitmapLibrary.getGreen().BlocyellowBmp_;
			break;
		case 4 :
			this.image = BitmapLibrary.getGreen().BlocredBmp_;
			break;
		}
	}
	
	public Bloc() {
		// TODO Auto-generated constructor stub
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
			canvas.drawBitmap(this.image, position.x*size, position.y*size, null);
		}
	}
	public void initBmp(Bitmap bmp) {
		// TODO Auto-generated method stub
		
	}
	
	
}
