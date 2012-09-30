package com.monkoid.retroaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import com.monkoid.retroaction.Bloc;
import com.monkoid.retroaction.Bloc.BlockType;
import com.monkoid.retroaction.Bloc.COLORS;
import com.monkoid.retroaction.Bloc.DIRECTIONS;
import com.monkoid.retroaction.Drawable;

public class Terrain implements Drawable {

	enum SensAxe {VERTICAL, HORIZONTAL, AUCUN}
	
	int longueur;
	int largeur;
	int tailleAirDeJeu = 400;
	public LinkedList<Bloc> list_blocs_libres;
	public LinkedList<Bloc> list_blocs_attaches;
	public List<Bloc> platformInTheFutur = null;
	public Random generateur;
	public Bloc[][] GameGrid;
	public Integer JekiffCaRace = 0;
	private Vector3 racineVector;

	int blockCountX = 0;
	int blockCountY = 0;
	private List<Vector3> platformBlocksIndexList;

	private MainThread mainThread_ = null;
	
	
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

		//LASER_H
		for(int i = 0; i< blockCountX;i++)
		{
			GameGrid[i][center.y].setType(BlockType.LASER_H);
			GameGrid[i][center.y].couleur = COLORS.AUCUNE;
		}
		//	LASER_V
		for(int j = 0; j< blockCountY;j++)
		{
			GameGrid[center.x][j].setType(BlockType.LASER_V);
			GameGrid[center.x][j].couleur = COLORS.AUCUNE;
		}
		racineVector = new Vector3(center.x, center.y);
		GameGrid[center.x][center.y].setType(BlockType.RACINE);
		GameGrid[center.x][center.y].couleur = COLORS.AUCUNE;
		platformBlocksIndexList.add(center);
		platformInTheFutur = new LinkedList<Bloc>(); 
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
			parcourirGrille(nextPos, newToggleCheckValue, delta);
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

	public Bloc getBloc(int i, int j){
		return GameGrid[i][j];	
	}

	public void onDraw(Canvas canvas) {
		for( int i = 0 ; i < blockCountX; i++)
			for( int j = 0; j < blockCountY; j++ )
				GameGrid[i][j].onDraw(canvas);
	}

	public void onUpdate() {
		for( int i = 0 ; i < blockCountX; i++)
			for( int j = 0; j < blockCountY; j++ ){
				GameGrid[i][j].updated = false;
			}

		for( int i = 0 ; i < blockCountX; i++)
			for( int j = 0; j < blockCountY; j++ )
				if(GameGrid[i][j].type == BlockType.GREF){
					if(GameGrid[i][j].direction == DIRECTIONS.DROITE  && !GameGrid[i][j].updated){
						changerBlocPour(GameGrid[i][j], GameGrid[i+1][j], BlockType.INVISIBLE, DIRECTIONS.DROITE);
					}
					else if(GameGrid[i][j].direction == DIRECTIONS.GAUCHE  && !GameGrid[i][j].updated){
						changerBlocPour(GameGrid[i][j],GameGrid[i-1][j], BlockType.INVISIBLE, DIRECTIONS.GAUCHE);
					}
					else if(GameGrid[i][j].direction == DIRECTIONS.HAUT  && !GameGrid[i][j].updated){
						changerBlocPour(GameGrid[i][j],GameGrid[i][j-1], BlockType.INVISIBLE, DIRECTIONS.HAUT);
					}
					if(GameGrid[i][j].direction == DIRECTIONS.BAS  && !GameGrid[i][j].updated){
						changerBlocPour(GameGrid[i][j],GameGrid[i][j+1], BlockType.INVISIBLE, DIRECTIONS.BAS);
					}

					GameGrid[i][j].updated = true;		
				}		

	}

	public void changerBlocPour(Bloc source, Bloc destination, BlockType type, DIRECTIONS direction){
		if(destination.type == BlockType.PLATEFORME || destination.type == BlockType.RACINE){
			source.setType(BlockType.PLATEFORME);
			source.direction= DIRECTIONS.AUCUNE;
			platformBlocksIndexList.add(new Vector3(source.position.x, source.position.y));
			platformInTheFutur.add(source);
		}else 

			if(destination.type == BlockType.LASER_H || destination.type == BlockType.LASER_V){
				source.setType(BlockType.INVISIBLE);
				source.couleur = COLORS.AUCUNE;
				source.direction= DIRECTIONS.AUCUNE;
			}else{
				destination.setType(BlockType.GREF);
				destination.couleur = source.couleur;
				destination.direction = source.direction;
				source.setType(type);
				source.couleur = COLORS.AUCUNE;
				source.direction= DIRECTIONS.AUCUNE;
				destination.updated = true;
			}

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


	public Vector3 FindRoot(){
		for( Vector3 v : platformBlocksIndexList){
			if(GameGrid[v.x][v.y].type == BlockType.RACINE )
				return v;
		}
		return null;
	}

	public Vector3 GetPlatformHeading(int deplacemenntIndexX, int deplacemenntIndexY){
		Vector3 v = racineVector;
		int deltaX = 0;
		int deltaY = 0;

		if( v.x != this.GetGridCenter().x ){
			if(deplacemenntIndexX == 0)
				return null;
			deltaX = deplacemenntIndexX;
		} else{
			if( v.y != this.GetGridCenter().y ){
				if(deplacemenntIndexY == 0)
					return null;
				deltaY = deplacemenntIndexY;
			}else{
				if( Math.abs(deplacemenntIndexX) > Math.abs(deplacemenntIndexY) )
				{
					deltaX = deplacemenntIndexX;
				} else if(Math.abs(deplacemenntIndexX) < Math.abs( deplacemenntIndexY) ){
					deltaY = deplacemenntIndexY;
				} else{
				}

			}
		}
		return new Vector3(deltaX, deltaY);
	}

	public Integer UpdateCaRace(){
		JekiffCaRace = 0;
		for(Bloc b: platformInTheFutur){
			if(b.type != BlockType.RACINE)
				if(b.couleur == COLORS.BLUE)
					JekiffCaRace += 983;
				else if(b.couleur == COLORS.GREEN)
					JekiffCaRace += 1213;
				else if(b.couleur == COLORS.RED)
					JekiffCaRace += 5352;
				else if(b.couleur == COLORS.PURPLE)
					JekiffCaRace += 444778;
				else if(b.couleur == COLORS.YELLOW)
					JekiffCaRace += 18;
		}
		return JekiffCaRace;
	}
	
	public void MovePlatform(int deplacemenntIndexX, int deplacemenntIndexY) {
		List<Vector3> tempList = new LinkedList<Vector3>();
		platformInTheFutur = new LinkedList<Bloc>(); 

		Vector3 platformHeading = GetPlatformHeading(deplacemenntIndexX, deplacemenntIndexY);

		if(platformHeading == null)
			return;

		for( Vector3 v : platformBlocksIndexList){
			Bloc oldBlock = GameGrid[v.x][v.y];

			int newXPos = v.x + platformHeading.x;
			int newYPos = v.y + platformHeading.y;

			if( newXPos >= this.blockCountX ||  newYPos >= blockCountY || newXPos < 0 || newYPos < 0)
				return;
			
			tempList.add(new Vector3(newXPos, newYPos));
			Bloc temp = new Bloc(oldBlock);
			temp.position = new Vector3(newXPos, newYPos);
			platformInTheFutur.add(temp);
			oldBlock.Destroy();
		}

		platformBlocksIndexList = new LinkedList<Vector3>(tempList);
		JekiffCaRace = 0;
		for(Bloc b: platformInTheFutur){
			GameGrid[b.position.x][b.position.y] = new Bloc(b);
			if(b.type == BlockType.RACINE)
				this.racineVector = new Vector3(b.position.x, b.position.y);

		}

		int centre_y = this.GetGridCenter().y;

		for(int i=0; i<GameGrid.length; i++)
			if(GameGrid[i][centre_y].type != BlockType.RACINE && GameGrid[i][centre_y].type != BlockType.PLATEFORME )
				GameGrid[i][centre_y].setType(BlockType.LASER_H);

		for(int i=0; i<GameGrid[0].length; i++)
			if(GameGrid[this.GetGridCenter().x][i].type != BlockType.RACINE && GameGrid[this.GetGridCenter().x][i].type != BlockType.PLATEFORME)
				GameGrid[this.GetGridCenter().x][i].setType(BlockType.LASER_V);

	}



}