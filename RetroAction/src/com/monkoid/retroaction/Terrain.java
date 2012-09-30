package com.monkoid.retroaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.monkoid.retroaction.Bloc;
import com.monkoid.retroaction.Bloc.BlockType;
import com.monkoid.retroaction.Bloc.COLORS;
import com.monkoid.retroaction.Bloc.DIRECTIONS;
import com.monkoid.retroaction.Drawable;

public class Terrain implements Drawable {

	int longueur;
	int largeur;
	int tailleAirDeJeu = 400;
	public LinkedList<Bloc> list_blocs_libres;
	public LinkedList<Bloc> list_blocs_attaches;

	public Random generateur;
	public Bloc[][] GameGrid;
	
	private Vector3 racineVector;
	
	int blockCountX = 0;
	int blockCountY = 0;
	private List<Vector3> platformBlocksIndexList;

	public Terrain(float screen_width, float screen_height, int block_width, int block_height){

		blockCountX = (int)(screen_width  / block_width);
		blockCountY = (int)(screen_height / block_height);

		if( blockCountX % 2 == 0 ) blockCountX++;
		if( blockCountY % 2 == 0 ) blockCountY++;

		GameGrid = new Bloc[blockCountX][blockCountY];
		for( int i = 0 ; i < blockCountX; i++)
		{	
			for( int j = 0; j < blockCountY; j++ )
				GameGrid[i][j] = new Bloc(i, j, BlockType.INVISIBLE, block_width);
		}

		platformBlocksIndexList = new ArrayList<Vector3>();
		Vector3 center = GetGridCenter();
		racineVector = new Vector3(center.x, center.y);
		GameGrid[center.x][center.y].setType(BlockType.RACINE);
		GameGrid[center.x][center.y].couleur = COLORS.AUCUNE;
		platformBlocksIndexList.add(center);
		
		//LASER_H
		/*for(int i = 0; i< blockCountX;i++)
		{
			GameGrid[i][center.y].setType(BlockType.LASER_H);
			GameGrid[i][center.y].couleur = COLORS.AUCUNE;
		}
		//	LASER_V
		for(int j = 0; j< blockCountY;j++)
		{
			GameGrid[center.x][j].setType(BlockType.LASER_V);
			GameGrid[center.x][j].couleur = COLORS.AUCUNE;
		}*/
		generateur = new Random();
		genererCube();
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


	Vector3 choisirPosition(DIRECTIONS currentBlockDirection){

		Vector3 position = new Vector3(0,0);

		switch(currentBlockDirection){
		case  DROITE : 
			position.x = 0;
			position.y = longueur/2 + Math.abs(generateur.nextInt()% (longueur/2));
			break;
		case BAS :
			position.x = Math.abs(generateur.nextInt()% (largeur/2));
			position.y = 0;
			break;
		case GAUCHE :
			position.x = largeur;
			position.y = Math.abs(generateur.nextInt()% (longueur/2));
			break;
		case HAUT :
			position.x = largeur/2 + Math.abs(generateur.nextInt()% (largeur/2));
			position.y = longueur;
			break;
		}


		return position;
	}


	public void parcourirGrille( Vector3 origin, boolean newToggleCheckValue, Vector3 whereFrom  ){

		
		if(origin.x > (GameGrid.length-1) || origin.y > (GameGrid[0].length-1) || origin.x < 0 || origin.y < 0){
			Log.d("Parcourir", "STOP x:"+origin.x+" "+"y:"+origin.y);
			return;
		}
		Log.d("Parcourir", "x:"+origin.x+" "+"y:"+origin.y);
		Bloc currentBlock = GameGrid[origin.x][origin.y];

		// Stopping condition
		if( currentBlock.type == BlockType.INVISIBLE )
			return;

		// This means the block has already been verified
		if(currentBlock.toggleCheckValue ==  newToggleCheckValue)
			return;

		currentBlock.toggleCheckValue = newToggleCheckValue;

		int state = 0;

		while (state <= 3){
			Vector3 delta = null;
			switch( state ){
			// Right
			case 0: 
				delta = new Vector3(1, 0);
			break;
			// Top
			case 1:  
				delta = new Vector3(0, 1);
			break;
			// Left
			case 2:  
				delta = new Vector3(-1, 0);
			break;
			// Down
			case 3:  
				delta = new Vector3(0, -1);
			break;
		}
			Vector3 nextPos = new Vector3(origin.x + delta.x, origin.y + delta.y);

			//if( !delta.HasVisited(whereFrom) ){
			parcourirGrille(nextPos, newToggleCheckValue, delta);
			//}
			currentBlock.couleur = COLORS.BLUE;
			state++;
		}
	}

	public DIRECTIONS choisirDirection(){

		int tempDirection = generateur.nextInt()%4;
		switch(tempDirection){

		case 0: return DIRECTIONS.DROITE;
		case 1: return DIRECTIONS.BAS;
		case 3: return DIRECTIONS.HAUT;
		case 4: return DIRECTIONS.GAUCHE;
		default: return DIRECTIONS.BAS;
		}

	}

	public boolean blocDepasseLaser(Bloc blocAVerifier){

		int facteur = 1;
		
		Vector3 milieu = this.GetGridCenter();
		if( blocAVerifier.direction == DIRECTIONS.DROITE && blocAVerifier.position.x >= milieu.x)
			return true;
		if( blocAVerifier.direction == DIRECTIONS.GAUCHE && blocAVerifier.position.x <= milieu.x+1)
			return true;
		if( blocAVerifier.direction == DIRECTIONS.BAS && blocAVerifier.position.y >= milieu.y)
			return true;
		if( blocAVerifier.direction == DIRECTIONS.HAUT && blocAVerifier.position.y <= milieu.y+1)
			return true;

		return false;
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
			for( int j = 0; j < blockCountY; j++ ){
				GameGrid[i][j].updated = false;
				}
		Vector3 milieu = GetGridCenter();
		
		for( int i = 0 ; i < blockCountX; i++)
			for( int j = 0; j < blockCountY; j++ )
				if(GameGrid[i][j].type == BlockType.GREF){
					if(blocDepasseLaser(GameGrid[i][j])){
						if(GameGrid[i][j].direction == DIRECTIONS.GAUCHE && i == milieu.x+1 )
						{
							GameGrid[i][j].setType(BlockType.INVISIBLE);
							GameGrid[i][j].couleur = COLORS.GREEN;
							GameGrid[i][j].direction= DIRECTIONS.AUCUNE;
						}
						else if(GameGrid[i][j].direction == DIRECTIONS.HAUT && j == milieu.y+1){
							GameGrid[i][j].setType(BlockType.INVISIBLE);
							GameGrid[i][j].couleur = COLORS.GREEN;
							GameGrid[i][j].direction= DIRECTIONS.AUCUNE;
						}
						else{
							GameGrid[i][j].couleur = COLORS.AUCUNE;
						}
						GameGrid[i][j].updated = true;
					}
					
					
					if(GameGrid[i][j].direction == DIRECTIONS.DROITE  && !GameGrid[i][j].updated){
						changerBlocPour(GameGrid[i][j],GameGrid[i+1][j], BlockType.INVISIBLE);
					}
					else if(GameGrid[i][j].direction == DIRECTIONS.GAUCHE  && !GameGrid[i][j].updated){
						changerBlocPour(GameGrid[i][j],GameGrid[i-1][j], BlockType.INVISIBLE);
					}
					else if(GameGrid[i][j].direction == DIRECTIONS.HAUT  && !GameGrid[i][j].updated){
						changerBlocPour(GameGrid[i][j],GameGrid[i][j-1], BlockType.INVISIBLE);
					}
					 if(GameGrid[i][j].direction == DIRECTIONS.BAS  && !GameGrid[i][j].updated){
						changerBlocPour(GameGrid[i][j],GameGrid[i][j+1], BlockType.INVISIBLE);
					}
					GameGrid[i][j].updated = true;
					
				}
					
	}
	
	public void changerBlocPour(Bloc source, Bloc destination, BlockType type){
		
		destination.setType(BlockType.GREF);
		destination.couleur = source.couleur;
		destination.direction = source.direction;
		source.setType(type);
		source.couleur = COLORS.GREEN;
		source.direction= DIRECTIONS.AUCUNE;
		destination.updated = true;	
	}

	void genererCube(){

		/*ATTENTION CETTE SECTION CONTIENT DU CODE DE TRUIE*/
		int direction = Math.abs(generateur.nextInt()%4);
		int positionArrivee =  Math.abs(generateur.nextInt());
		int indexY, indexX;
		switch(direction){
		case 0 : 
			indexY = (blockCountY/2)+1 + positionArrivee%(blockCountY/2); //<---- CODE DE TRUIE
			GameGrid[0][indexY].setType(BlockType.GREF);
			GameGrid[0][indexY].ChooseRandomColorForCurrentBlock();
			GameGrid[0][indexY].direction =  DIRECTIONS.DROITE;
			break;
		case 1 :
			indexX = positionArrivee%(blockCountX/2);
			GameGrid[indexX][0].setType(BlockType.GREF);
			GameGrid[indexX][0].ChooseRandomColorForCurrentBlock();
			GameGrid[indexX][0].direction =  DIRECTIONS.BAS;
			break;
		case 2 :
			indexY = positionArrivee%(blockCountY/2);
			GameGrid[blockCountX-1][indexY].setType(BlockType.GREF);
			GameGrid[blockCountX-1][indexY].ChooseRandomColorForCurrentBlock();
			GameGrid[blockCountX-1][indexY].direction =  DIRECTIONS.GAUCHE;
			break;
		case 3 :
			indexX = blockCountX/2+1 + positionArrivee%(blockCountX/2);//<---- CODE DE TRUIE
			GameGrid[indexX][blockCountY-1].setType(BlockType.GREF);
			GameGrid[indexX][blockCountY-1].ChooseRandomColorForCurrentBlock();
			GameGrid[indexX][blockCountY-1].direction =  DIRECTIONS.HAUT;
			break;
		}
		
		indexX = 0;
	}

	public Vector3 GetGridCenter(){
		return new Vector3((GameGrid.length) / 2, (GameGrid[0].length) / 2);
	}


	public void MovePlatform(int deplacemenntIndexX, int deplacemenntIndexY) {
		//List<Vector3> tempList = new LinkedList<Vector3>();
		
		for( Vector3 v : platformBlocksIndexList){
			Bloc oldBlock = GameGrid[v.x][v.y];
			
			int newXPos = v.x + deplacemenntIndexX;
			int newYPos = v.y + deplacemenntIndexY;
			
			if( newXPos >= this.blockCountX ||  newYPos >= blockCountY )
				return;
			
			Bloc newBlock = GameGrid[newXPos][newYPos];
			
			newBlock.AcquirePropertiesFrom(oldBlock);
			v.x = newXPos;
			v.y = newYPos;
			
			if(newBlock.type == BlockType.RACINE){
				racineVector.x = newBlock.position.x;
				racineVector.y = newBlock.position.y;

			}
			
			oldBlock.Destroy();
		}
		
	}

	

}