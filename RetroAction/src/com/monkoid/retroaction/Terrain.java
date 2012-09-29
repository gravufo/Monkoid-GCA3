package com.monkoid.retroaction;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.monkoid.retroaction.Bloc;
import com.monkoid.retroaction.Drawable;

public class Terrain implements Drawable {

	int longueur;
	int largeur;
	int tailleAirDeJeu = 400;
	LinkedList<Bloc> list_blocs_libres;
	LinkedList<Bloc> list_blocs_attaches;

	Random generateur;
	
	public Terrain(float screen_width, float screen_height, int width_block, int height_block){
		
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

	
	Vector choisirPosition(int direction){

		Vector position = new Vector(0,0);
		
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
	
	void verifierLaser(){
		
	}
	
	void actualiserPositionBlocs(){
		
		Bloc b = new Bloc();
		Iterator<Bloc> iter = list_blocs_libres.iterator();
		
		for(int i = 0; i < list_blocs_libres.size();i++){
			if(iter.hasNext()){
				b = iter.next();
			}
			b.actualiserPosition();
		}
		}

	public void onDraw(Canvas canvas) {
		Iterator<Bloc> iter = list_blocs_libres.iterator();
		Bloc b ;
		actualiserPositionBlocs();
		for(int i = 0; i < list_blocs_libres.size();i++){
			if(iter.hasNext()){
				b = iter.next();
				b.onDraw(canvas);
			}
			
		}
		
	}

	public void initBmp(Bitmap bmp) {
		
	}
	}