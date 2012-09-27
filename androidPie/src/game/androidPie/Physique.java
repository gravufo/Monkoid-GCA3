package game.androidPie;

import android.graphics.Point;
import android.os.SystemClock;

public class Physique {
	/**
	 * Déclarations
	 */
	protected Point position;
	protected Vector2D a; 			//vector acceleration
	protected Vector2D v; 			//vecteur vitesse
	protected float lastModif;		//time of last movement
	private static final String TAG = Level.class.getSimpleName();
	
	/**
	 * Constructeurs
	 */
	public Physique(){
		
		//Initialize vectors to zero
		v = new Vector2D(0,0);
		a = new Vector2D(0,0);
		position = new Point(0,0);	
		
		lastModif = SystemClock.uptimeMillis();
	}
	
	public Physique(Point position, Vector2D v, Vector2D a){
		this();
		this.position = position;
		this.v = v;
		this.a = a;
	}
	
	/**
	 * methods used to move the object
	 */
	public void calcNewPosition(){
		
		float x = position.x;
		float y = position.y;
		
		float dx, dy;
		
		if (lastModif == 0) lastModif = SystemClock.uptimeMillis(); //prevent lag at start
		float time = SystemClock.uptimeMillis();
		float dt = time - lastModif; 
		
		dx = (float)v.x * dt + 1/2 * a.getX() * dt * dt;
		dy = (float)v.y * dt + 1/2 * a.getY() * dt * dt;
		
		x += dx;
		y += dy;
		
		position.x = (int) x;
		position.y = (int) y;
		
		calcNewSpeed(dt);
		
		lastModif = time;
	}
	
	private void calcNewSpeed(float dt){
		v.x += a.getX() * dt;
		v.y += a.getY() * dt;
		
	}
	
	/**
	 *  getters and setters
	 */

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Vector2D getA() {
		return a;
	}

	public void setA(Vector2D a) {
		this.a = a;
	}

	public Vector2D getVitesse() {
		return v;
	}

	public void setV(Vector2D v) {
		this.v = v;
	}

	
}
