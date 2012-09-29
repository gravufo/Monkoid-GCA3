package com.monkoid.retroaction;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

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

	int blockCountX = 0;
	int blockCountY = 0;

	public Terrain(float screen_width, float screen_height, int block_width, int block_height){

		blockCountX = (int)(screen_width  / block_width);
		blockCountY = (int)(screen_height / block_height);

		if( blockCountX % 2 == 0 ) blockCountX++;
		if( blockCountY % 2 == 0 ) blockCountY++;

		GameGrid = new Bloc[blockCountX][blockCountY];
		for( int i = 0 ; i < blockCountX; i++)
		{	
			for( int j = 0; j < blockCountY; j++ )
				GameGrid[i][j] = new Bloc(i, j, BlockType.PLATEFORME);
		}

		Vector3 center = GetGridCenter();
		GameGrid[center.x][center.y].setType(BlockType.RACINE);
		//GameGrid[1][4].setType(BlockType.GREF);
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

		
		Bloc currentBlock = GameGrid[origin.x][origin.y];

		// Stopping condition
		if( currentBlock.type == BlockType.INVISIBLE )
			return;

		// This means the block has already been verified
		if(currentBlock.toggleCheckValue ==  newToggleCheckValue)
			return;

		currentBlock.toggleCheckValue = newToggleCheckValue;

		int state = 0;

		while (state <= 1){
			Vector3 delta = null;

			switch( state ){
			// Right
			case 0:  delta = new Vector3( 1, 0);
			break;
			// Top
			case 1:  delta = new Vector3( 0, 1);
			break;
			// Left
			case 2:  delta = new Vector3( -1, 0);
			break;
			// Down
			case 3:  delta = new Vector3( 0, -1);
			break;
			}

			Vector3 nextPos = origin.Add(delta);
			if( !delta.HasVisited(whereFrom) ){
				if(nextPos.x >= 0 &&  nextPos.x < blockCountX && nextPos.y >= 0 &&  nextPos.y < blockCountY)
					parcourirGrille(nextPos, newToggleCheckValue, delta);
			}
			
			state++;
		}

		currentBlock.couleur = COLORS.BLUE;

		//		// Check all directions, recursively, for the current block
		//		for( int i = 0; i < 2; i++ ){
		//			for( int j = 0; j < 2; j++){
		//				
		//				int signe =	(int)Math.pow(-1, i);
		//				
		//				int dx= signe * j;
		//				int dy= signe * ((j+1) %2);
		//				
		//				Vector3 delta = new Vector3(dx, dy);
		//				Vector3 nextPos = origin.Add(delta);
		//				System.out.print(delta.x + " " + delta.y);
		//				
		//				if( nextPos.x >= 0 &&  nextPos.x < blockCountX && nextPos.y >= 0 &&  nextPos.y < blockCountY)
		//					parcourirGrille( nextPos, newToggleCheckValue);
		//			}
		//		}

		// TRAITEMEN SPÉCIFIQUE
		//ex:

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

	public static boolean blocDepasseLaser(Bloc blocAVerifier){

		int facteur = 1;

		if( blocAVerifier.direction == DIRECTIONS.BAS || blocAVerifier.direction == DIRECTIONS.GAUCHE  )
			facteur = -1;

		if( facteur *  blocAVerifier.position.x > 0 || facteur * blocAVerifier.position.y > 0   )
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
		//		// TODO Auto-generated method stub
		//		for( int i = 0 ; i < blockCountX; i++)
		//			for( int j = 0; j < blockCountY; j++ )
		//				if(GameGrid[i][j].type == BlockType.GREF){
		//					GameGrid[i][j].setType(BlockType.INVISIBLE);
		//					GameGrid[i+1][j].setType(BlockType.GREF);
		//					return;
		//				}

	}

	void genererCube(){
		Bloc temp = new Bloc();

		int tempCouleur =  Math.abs(generateur.nextInt()%5);

		switch(tempCouleur){
		case 0 : 
			temp.couleur = COLORS.BLUE;
			break;
		case 1 :
			temp.couleur = COLORS.GREEN;
			break;
		case 2 :
			temp.couleur = COLORS.PURPLE;;
			break;
		case 3 :
			temp.couleur = COLORS.YELLOW;;
			break;
		case 4 :
			temp.couleur = COLORS.RED;
			break;
		}


		DIRECTIONS currentBlockDirection = choisirDirection();
		temp.position = choisirPosition(currentBlockDirection);
		list_blocs_libres.add(temp);

	}

	public Vector3 GetGridCenter(){
		return new Vector3((GameGrid.length) / 2, (GameGrid[0].length) / 2);
	}




}