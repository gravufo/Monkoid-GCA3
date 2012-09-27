package pro.to.type.Level;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public abstract class Item {

	public int m_posX, m_posY, m_Width, m_Height;
	public Bitmap m_Image;
	public Resources m_Res;
	
	public void setPosX(int newx)
	{
		this.m_posX = newx;
	}
	
	public void setPosY(int newy)
	{
		this.m_posY = newy;
	}
	
	public boolean Showable(int _screenPosX, int _screenPosY, int _screenWidth, int _screenHeight)
	{
		if ((this.m_posX > _screenPosX) && (this.m_posX < _screenPosX + _screenWidth) && (this.m_posY > _screenPosY) && (this.m_posY < _screenPosY + _screenHeight))
			return true;
		else
			return false;
	}
	
	public abstract void OnDraw(Canvas c,int _screenPosX, int _screenPosY, int _screenWidth, int _screenHeight);

	public Point getImageDimension()
	{
		return new Point(this.m_Image.getWidth(), this.m_Image.getHeight());
	}
}
