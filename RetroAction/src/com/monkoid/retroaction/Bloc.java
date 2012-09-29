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
	public BlockType type;

	public Bloc(int couleur, int direction, Vector3 position) {
		this.couleur = couleur;
		this.direction = direction;
		this.position = position;
		this.type = BlockType.GREF;
	}
	
	public Bloc(int i, int j, BlockType type) {
		this.size = size;
		this.setType(type);
		this.position = new Vector3(i,j);
		this.direction = 0;
		////////TEST///////////
		//Random generateur = new Random();
		//this.couleur =  Math.abs(generateur.nextInt()%4);
		
	}
	
	public Bloc() {
		// TODO Auto-generated constructor stub
	}
	
	public void onDraw(Canvas canvas){
		if(canvas != null)
			canvas.drawBitmap(this.image, position.x*this.image.getWidth(), position.y*this.image.getHeight(), null);
	}
	public void initBmp(Bitmap bmp) {
		// TODO Auto-generated method stub
		
	}
	
	public void setType(BlockType type){
		this.type = type;
		switch(this.type){
		case INVISIBLE : 
			this.image = BitmapLibrary.getGreen().BlocblueBmp_;
			break;
		case GREF :
			this.image = BitmapLibrary.getGreen().BlocgreenBmp_;
			break;
		case RACINE:
			this.image = BitmapLibrary.getGreen().BlocredBmp_;
			break;
		case PLATEFORME:
			this.image = BitmapLibrary.getGreen().BlocyellowBmp_;
			break;
		}
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		/*if(this.type == BlockType.GREF)
			switch(this.direction){
				case 0 : 
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
		*/		
	}
}
