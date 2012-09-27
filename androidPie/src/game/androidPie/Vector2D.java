package game.androidPie;

public class Vector2D
{
	protected float x;
	protected float y;


	public Vector2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Get angle (rad)
	 */
	public float angle()
	{
		return (float)Math.atan2(y, x);
	}

	/**
	 * Retourne la norme du vecteur
	 */
	public float norme()
	{
		return (float)Math.sqrt((x * x) + (y * y));
	}

	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}

}
