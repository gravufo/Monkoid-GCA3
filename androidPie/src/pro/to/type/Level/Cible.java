package pro.to.type.Level;


import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;



public class Cible{

	private PointF m_Pos;
	private Bitmap m_bmpCible;
	private int m_Direction;
	private float m_Speed;
	private boolean m_outOfBounds;
	private Rect m_bdnRect;
	private int m_ScreenHeight;
	private boolean isKill_;
	
	public Cible(Resources _res, Bitmap _bmp, int _screenWidth, int _screenHeight, boolean _underFreeze)
	{
		this.m_ScreenHeight = _screenHeight;
		this.m_outOfBounds = false;
		if (_underFreeze)
			this.m_Speed = 0.5f;
		else
			this.m_Speed = 1.5f;
		this.m_bmpCible = _bmp;
		Random generator = new Random();
		int tmpPosY = 0;
		this.m_Direction = -1;
		tmpPosY = _screenHeight;
		this.m_Pos = new PointF(_screenWidth - generator.nextInt(_screenWidth/4) - this.m_bmpCible.getWidth(), tmpPosY);
		this.m_bdnRect = new Rect();
		this.MakeBoundingBox();
		isKill_ = false;
	}
	public void OnDraw(Canvas c) {
		
			c.drawBitmap(this.m_bmpCible, this.m_Pos.x, this.m_Pos.y, null);
	}
	
	public void UpdatePosition(){
		this.m_Pos.y += this.m_Direction*this.m_Speed;
		this.MakeBoundingBox();
	}
	
	public void setFrozen(boolean froze)
	{
		if(froze)this.m_Speed = 0.5f;
		else this.m_Speed = 1.5f;
	}

	public void MakeCibleDie()
	{
		this.m_Pos.y += 800*this.m_Direction;
		
	}
	
	public void Kill(){
		isKill_ = true;
	}
	
	public boolean getIsKill()
	{
		return this.isKill_;
	}
	
	public Rect getRect()
	{
		return this.m_bdnRect;
	}
	
	public boolean isOut()
	{
		if(m_Pos.y < (0 - m_bmpCible.getHeight()))
			return true;
		
		return false;
	}
	
	public void MakeBoundingBox()
	{
		this.m_bdnRect.bottom = (int)(this.m_Pos.y + this.m_bmpCible.getHeight());
		this.m_bdnRect.top = (int)(this.m_Pos.y);
		this.m_bdnRect.right = (int)(this.m_Pos.x + this.m_bmpCible.getWidth());
		this.m_bdnRect.left = (int)(this.m_Pos.x);
	}
	
}
