package com.monkoid.retroaction;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import com.monkoid.retroaction.Bloc;

public class terrain {

	int tailleX;
	int tailleY;
	int tailleAirDeJeu;
	LinkedList<Bloc> list_blocs_libres;
	LinkedList<Bloc> list_blocs_attaches;


	Random generateur;
	
	public terrain(int tailleX, int tailleY, int tailleAirDeJeu,
			LinkedList<Bloc> list_blocs_libres,LinkedList<Bloc> list_blocs_attaches) {
		super();
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.tailleAirDeJeu = tailleAirDeJeu;
		this.list_blocs_libres = list_blocs_libres;
		this.list_blocs_attaches = list_blocs_attaches;
		generateur = new Random();
	}

	void genererCubes(){
		int couleur =  choisirCouleur();
		int direction = choisirDirection();
		Vector position = choisirPosition(direction);
		Bloc temp = new Bloc(couleur, direction ,position);
		list_blocs_libres.add(temp);
		
	}
	
	int choisirDirection(){
		
		return generateur.nextInt()%4;
	}
	int choisirCouleur(){
		
		return generateur.nextInt()%6;
	}
	
	Vector choisirPosition(int direction){

		Vector position = new Vector(0,0);
		
		switch(direction){
		case 0 : 
			position.x = -generateur.nextInt()%tailleAirDeJeu;
			position.y = tailleAirDeJeu;
			break;
		case 1 :
			position.x = tailleAirDeJeu;
			position.y = generateur.nextInt()%tailleAirDeJeu;
			break;
		case 2 :
			position.x = generateur.nextInt()%tailleAirDeJeu;
			position.y = -tailleAirDeJeu;
			break;
		case 3 :
			position.x = generateur.nextInt()%tailleAirDeJeu; 
			position.y = -tailleAirDeJeu;
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
	}


