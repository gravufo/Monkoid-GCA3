package com.monkoid.retroaction;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import com.monkoid.retroaction.Drawable;

public class Bloc implements Drawable {

	enum DIRECTIONS { HAUT, BAS, GAUCHE, DROITE }
	enum COLORS {RED, YELLOW, BLUE, GREEN, PURPLE}

	public boolean checkValue;

	COLORS couleur;
	DIRECTIONS direction;
	Vector3 position;
	Bitmap image;
	int size = 0;

	public boolean toggleCheckValue = false;

	enum BlockType { INVISIBLE, PLATEFORME, GREF, RACINE}
	public BlockType type;

	public Bloc(COLORS couleur, DIRECTIONS direction, Vector3 position) {
		this.couleur = couleur;
		this.direction = direction;
		this.position = position;
		this.type = BlockType.GREF;
	}

	public Bloc(int i, int j, BlockType type) {
		this.size = size;
		this.setType(type);
		this.position = new Vector3(i,j);
		this.direction = DIRECTIONS.BAS;
		this.toggleCheckValue = false;
		ChooseRandomColorForCurrentBlock();
		////////TEST///////////
		//Random generateur = new Random();
		//this.couleur =  Math.abs(generateur.nextInt()%4);

	}

	public Bloc() {
		// TODO Auto-generated constructor stub
	}

	public void ChooseRandomColorForCurrentBlock(){
		Random generateur = new Random();
		int tempCouleur =  Math.abs(generateur.nextInt()%5);
		
		switch(tempCouleur){
		case 0 : 
			this.couleur = COLORS.GREEN;
			break;
		case 1 :
			this.couleur = COLORS.GREEN;
			break;
		case 2 :
			this.couleur = COLORS.PURPLE;;
			break;
		case 3 :
			this.couleur = COLORS.YELLOW;;
			break;
		case 4 :
			this.couleur = COLORS.RED;
			break;
		
		}		
		
	}
	
	public void onDraw(Canvas canvas){

		
			switch(couleur){
			case BLUE : 
				this.image = BitmapLibrary.getGreen().BlocblueBmp_;
				break;
			case GREEN :
				this.image = BitmapLibrary.getGreen().BlocgreenBmp_;
				break;
			case PURPLE:
				this.image = BitmapLibrary.getGreen().BlocpurpleBmp_;
				break;
			case YELLOW:
				this.image = BitmapLibrary.getGreen().BlocyellowBmp_;
				break;
			case RED:
				this.image = BitmapLibrary.getGreen().BlocredBmp_;
				break;
			}
		

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
			this.image = BitmapLibrary.getGreen().BlocInvisibleBmp_;
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
	}
}
