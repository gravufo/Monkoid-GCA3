package com.monkoid.retroaction;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.monkoid.retroaction.Bloc;
import com.monkoid.retroaction.Bloc.BlockType;
import com.monkoid.retroaction.Drawable;

public class Terrain implements Drawable {

	int longueur;
	int largeur;
	int tailleAirDeJeu = 400;
	public LinkedList<Bloc> list_blocs_libres;
	public LinkedList<Bloc> list_blocs_attaches;

	public Random generateur;
	public Bloc[][] GameGrid;
	
	int blockCountX = 0;
	int blockCountY = 0;
	
	public Terrain(float screen_width, float screen_height, int block_width, int block_height){
		
		blockCountX = (int)(screen_width  / block_width);
		blockCountY = (int)(screen_height / block_height);
		
		if( blockCountX % 2 == 0 ) blockCountX++;
		if( blockCountY % 2 == 0 ) blockCountY++;
		
		GameGrid = new Bloc[blockCountX][blockCountY];
		for( int i = 0 ; i < blockCountX; i++)
			for( int j = 0; j < blockCountY; j++ )
				GameGrid[i][j] = new Bloc(i, j, BlockType.INVISIBLE);
		
		Vector3 center = GetGridCenter();
		GameGrid[center.x][center.y].setType(BlockType.RACINE);
		GameGrid[1][4].setType(BlockType.GREF);
	}
	

	
	public Terrain(int tailleX, int tailleY, int tailleAirDeJeu,
			LinkedList<Bloc> list_blocs_libres,LinkedList<Bloc> list_blocs_attaches) {
		super();
		this.largeur = tailleX;
		this.longueur = tailleY;
		this.tailleAirDeJeu = tailleAirDeJeu;
		this.list_blocs_libres = list_blocs_libres;
		this.list_blocs_attaches = list_blocs_attaches;
		generateur = new Random();
	}

	public Terrain() {
		super();
		this.largeur = 600;
		this.longueur = 1000;
		this.tailleAirDeJeu = 300;
		this.list_blocs_libres = new LinkedList<Bloc>();
		this.list_blocs_attaches = new LinkedList<Bloc>();
		generateur = new Random();
	}

	
	Vector3 choisirPosition(int direction){

		Vector3 position = new Vector3(0,0);
		
		switch(direction){
		case 0 : 
			position.x = 0;
			position.y = longueur/2 + Math.abs(generateur.nextInt()%longueur/2);
			break;
		case 1 :
			position.x = Math.abs(generateur.nextInt()%largeur/2);
			position.y = 0;
			break;
		case 2 :
			position.x = largeur;
			position.y = Math.abs(generateur.nextInt()%longueur/2);
			break;
		case 3 :
			position.x = largeur/2 + Math.abs(generateur.nextInt()%largeur/2);
			position.y = longueur;
			break;
		}

		
		return position;
	}
	
	private void verifierLaser(){
		
	}
	
	private void actualiserPositionBlocs(){	
	}

	public Bloc getBloc(int i, int j){
		return GameGrid[i][j];	
	}
	
	public void onDraw(Canvas canvas) {
		for( int i = 0 ; i < blockCountX; i++)
			for( int j = 0; j < blockCountY; j++ )
				GameGrid[i][j].onDraw(canvas);
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		for( int i = 0 ; i < blockCountX; i++)
			for( int j = 0; j < blockCountY; j++ )
				if(GameGrid[i][j].type == BlockType.GREF){
					GameGrid[i][j].setType(BlockType.INVISIBLE);
					GameGrid[i+1][j].setType(BlockType.GREF);
					return;
				}
					
	}
	
	void genererCube(){
		Bloc temp = new Bloc();
		
		temp.couleur =  Math.abs(generateur.nextInt()%5);
		switch(temp.couleur){
		case 0 : 
			temp.image = BitmapLibrary.getGreen().BlocblueBmp_;
			break;
		case 1 :
			temp.image = BitmapLibrary.getGreen().BlocgreenBmp_;
			break;
		case 2 :
			temp.image = BitmapLibrary.getGreen().BlocpurpleBmp_;
			break;
		case 3 :
			temp.image = BitmapLibrary.getGreen().BlocyellowBmp_;
			break;
		case 4 :
			temp.image = BitmapLibrary.getGreen().BlocredBmp_;
			break;
		}
		temp.direction = generateur.nextInt()%4;
		temp.position = choisirPosition(temp.direction);
		list_blocs_libres.add(temp);
		
	}
	
	public Vector3 GetGridCenter(){
		return new Vector3((GameGrid.length) / 2, (GameGrid[0].length) / 2);
	}



	
}