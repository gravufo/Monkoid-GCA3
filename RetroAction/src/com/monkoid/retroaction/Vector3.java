package com.monkoid.retroaction;

public class Vector3 {
	int x;
	int y;
	public Vector3(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Vector3 Add(Vector3 vectorToAdd){
		return new Vector3( this.x += vectorToAdd.x, this.y += vectorToAdd.y );
	}
	
	public boolean HasVisited( Vector3 vectorToCheck )
	{
		
		return ( (vectorToCheck.x == -1 * this.x) &&  (vectorToCheck.y == -1 * this.y) );
	}
}
