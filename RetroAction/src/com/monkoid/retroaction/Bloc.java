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

	public Bloc(int i, int j, BlockType type, int size) {
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
				if(size == 16)
					this.image = BitmapLibrary.getGreen().Blocblue16_;
				else if(size == 32)
					this.image = BitmapLibrary.getGreen().Blocblue32_;
				else
					this.image = BitmapLibrary.getGreen().Blocblue64_;
				break;
			case GREEN :
				if(size == 16)
					this.image = BitmapLibrary.getGreen().Blocgreen16_;
				else if(size == 32)
					this.image = BitmapLibrary.getGreen().Blocgreen32_;
				else
					this.image = BitmapLibrary.getGreen().Blocgreen64_;
				break;
			case PURPLE:
				if(size == 16)
					this.image = BitmapLibrary.getGreen().Blocpurple16_;
				else if(size == 32)
					this.image = BitmapLibrary.getGreen().Blocpurple32_;
				else
					this.image = BitmapLibrary.getGreen().Blocpurple64_;
				break;
			case YELLOW:
				if(size == 16)
					this.image = BitmapLibrary.getGreen().Blocyellow16_;
				else if(size == 32)
					this.image = BitmapLibrary.getGreen().Blocyellow32_;
				else
					this.image = BitmapLibrary.getGreen().Blocyellow64_;
				break;
			case RED:
				if(size == 16)
					this.image = BitmapLibrary.getGreen().Blocred16_;
				else if(size == 32)
					this.image = BitmapLibrary.getGreen().Blocred32_;
				else
					this.image = BitmapLibrary.getGreen().Blocred64_;
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
			if(size == 16)
				this.image = BitmapLibrary.getGreen().BlocInvisible16_;
			else if(size == 32)
				this.image = BitmapLibrary.getGreen().BlocInvisible32_;
			else
				this.image = BitmapLibrary.getGreen().BlocInvisible64_;
			break;
		case RACINE:
			if(size == 16)
				this.image = BitmapLibrary.getGreen().Blocblue16_;
			else if(size == 32)
				this.image = BitmapLibrary.getGreen().Blocblue32_;
			else
				this.image = BitmapLibrary.getGreen().Blocblue64_;
			break;
		case PLATEFORME:
			if(size == 16)
				this.image = BitmapLibrary.getGreen().Blocblue16_;
			else if(size == 32)
				this.image = BitmapLibrary.getGreen().Blocblue32_;
			else
				this.image = BitmapLibrary.getGreen().Blocblue64_;
			break;
		}
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
	}
}
