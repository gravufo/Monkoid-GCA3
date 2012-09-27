package pro.to.type.Level;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;

public class Freezer{
	private PointF m_Pos;
	private Bitmap m_bmpFreezer;
	private int m_Direction;
	private float m_Speed;
	private boolean m_Activated;
	private boolean m_outOfBounds;
	private Rect m_bdnRect;
	private int m_ScreenHeight;
	
	public Freezer(Resources _res, Bitmap _bmp, int _screenWidth, int _screenHeight)
	{
		this.m_ScreenHeight = _screenHeight;
		this.m_Activated = false;
		this.m_Speed = 1.8f;
		this.m_bmpFreezer = _bmp;
		Random generator = new Random();
		int tmpPosY = 0;
		switch(generator.nextInt(2))
		{
			case 0 : {
				this.m_Direction = -1;
				tmpPosY = _screenHeight;
				break;
			}
			case 1: {
				this.m_Direction = 1;
				tmpPosY = -(this.m_bmpFreezer.getHeight());
				break;
			}
		}
		this.m_Pos = new PointF(_screenWidth - generator.nextInt(_screenWidth/4) - this.m_bmpFreezer.getWidth(), tmpPosY);
		this.m_bdnRect = new Rect();
		this.MakeBoundingBox();
	}
	public void OnDraw(Canvas c) {
		if (this.m_outOfBounds == false)
			c.drawBitmap(this.m_bmpFreezer, this.m_Pos.x, this.m_Pos.y, null);
	}
	
	public void UpdatePosition(){
		this.m_Pos.y += this.m_Direction*this.m_Speed;
		switch(this.m_Direction)
		{
			case -1 : {
				if (this.m_Pos.y + this.m_bmpFreezer.getHeight() <= 0)
					this.m_outOfBounds = true;
				break;
			}
			case 1: {
				if (this.m_Pos.y > this.m_ScreenHeight)
					this.m_outOfBounds = true;
				break;
			}
		}
		this.MakeBoundingBox();
	}

	public boolean getActivated()
	{
		return this.m_Activated;
	}
	
	public void MakeCibleOOB()
	{
		this.m_Pos.y += 800*this.m_Direction;
		
	}
	
	public boolean getOOB()
	{
		return this.m_outOfBounds;
	}
	
	public Rect getRect()
	{
		return this.m_bdnRect;
	}
	
	public void MakeBoundingBox()
	{
		this.m_bdnRect.bottom = (int)(this.m_Pos.y + this.m_bmpFreezer.getHeight());
		this.m_bdnRect.top = (int)(this.m_Pos.y);
		this.m_bdnRect.right = (int)(this.m_Pos.x + this.m_bmpFreezer.getWidth());
		this.m_bdnRect.left = (int)(this.m_Pos.x);
	}

}
