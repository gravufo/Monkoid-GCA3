package com.monkoid.retroaction;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import com.monkoid.retroaction.Drawable;

public class Bloc implements Drawable {

	enum DIRECTIONS { HAUT, BAS, GAUCHE, DROITE, AUCUNE }
	enum COLORS {RED, YELLOW, BLUE, GREEN, PURPLE, AUCUNE}

	public boolean checkValue;

	COLORS couleur;
	DIRECTIONS direction;
	Vector3 position;
	boolean updated;
	Bitmap image;
	
	int size = 0;

	public boolean toggleCheckValue = false;

	enum BlockType { INVISIBLE, PLATEFORME, GREF, RACINE, LASER_H, LASER_V}
	public BlockType type;

	public Bloc(COLORS couleur, DIRECTIONS direction, Vector3 position) {
		this.couleur = couleur;
		this.direction = direction;
		this.position = position;
		this.updated = false;
		this.type = BlockType.GREF;
	}

	public Bloc(int i, int j, BlockType type, int size) {
		this.size = size;
		this.setType(type);
		this.position = new Vector3(i,j);
		this.direction = DIRECTIONS.BAS;
		this.toggleCheckValue = false;
		this.couleur = COLORS.GREEN;
		//ChooseRandomColorForCurrentBlock();

		this.updated = false;
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
			this.couleur = COLORS.BLUE;
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
					this.image = BitmapLibrary.getGreen().Blocblue_;
				break;
			case GREEN :
					this.image = BitmapLibrary.getGreen().Blocgreen_;
				break;
			case PURPLE:
					this.image = BitmapLibrary.getGreen().Blocpurple_;
				break;
			case YELLOW:
					this.image = BitmapLibrary.getGreen().Blocyellow_;
				break;
			case RED:
					this.image = BitmapLibrary.getGreen().Blocred_;
				break;
			case AUCUNE: if( type == BlockType.RACINE )	
							this.image = BitmapLibrary.getGreen().PlatformBmp_;
			break;
			}
			if(canvas != null )
				canvas.drawBitmap(this.image, position.x*size, position.y*size, null);

	}

	public void initBmp(Bitmap bmp) {
		// TODO Auto-generated method stub
	}

	public void setType(BlockType type){
		this.type = type;
		switch(this.type){
		case INVISIBLE : 
			
				this.image = BitmapLibrary.getGreen().BlocInvisible_;
			break;
		case RACINE:
			if(size == 16)
				this.image = BitmapLibrary.getGreen().PlatformBmp_;
			else if(size == 32)
				this.image = BitmapLibrary.getGreen().PlatformBmp_;
			else
				this.image = BitmapLibrary.getGreen().PlatformBmp_;
			break;
		case PLATEFORME:
				this.image = BitmapLibrary.getGreen().Blocblue_;
			break;
		case LASER_H:
				this.image = BitmapLibrary.getGreen().Bloclaserhorizontale_;
			break;
		case LASER_V:
				this.image = BitmapLibrary.getGreen().Bloclaservertical_;
			break;
		}
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
	}

	
	public void Destroy(){
		
		this.type = BlockType.INVISIBLE;
		this.couleur = COLORS.AUCUNE;
	}
	
	public void AcquirePropertiesFrom(Bloc oldBlock) {
		
		
		
		this.couleur = oldBlock.couleur;
		this.direction = oldBlock.direction;
		this.type = oldBlock.type;
		this.toggleCheckValue = oldBlock.toggleCheckValue;
		
	}
}
