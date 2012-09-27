package game.androidPie;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

public class Animation {
	
	private Bitmap m_SourceBitmap1;
	private int m_frameCount;     // Nombre de frames
	private int m_currentFrame;   // La frame displayé
    private long m_frameTicker;   // Le temps de la dernière update
    private int m_framePeriod;    // milliseconds entre chaque frame (1000/fps)
    private Matrix m_mtxEffect;
    private Bitmap m_bmpToDraw;

	public Animation(Bitmap bmp1, int fps, int frameCount)
	{
		this.m_SourceBitmap1 = bmp1;
		this.m_mtxEffect = new Matrix();
		this.m_frameCount = frameCount;
		this.m_currentFrame = 0;
		this.m_frameTicker = 0;
		if (fps == 0)
			fps = 1;
		this.m_framePeriod = 1000 / fps;
	}
	
	public void update(long gameTime) 
	{
		if (gameTime > this.m_frameTicker + this.m_framePeriod) {
			this.m_frameTicker = gameTime;
			 // increment the frame
			this.m_currentFrame++;
			 if (this.m_currentFrame >= this.m_frameCount) 
			 {
				 this.m_currentFrame = 0;
			 }
			 this.m_bmpToDraw = Bitmap.createBitmap(this.m_SourceBitmap1, this.m_currentFrame*(this.m_SourceBitmap1.getWidth()/this.m_frameCount), 0, (this.m_SourceBitmap1.getWidth()/this.m_frameCount), this.m_SourceBitmap1.getHeight(), this.m_mtxEffect, true);
		}
		
	}
	
	public void draw(Canvas canvas, PointF _Position, Paint _paint) 
	{
		if ((canvas != null) && (this.m_bmpToDraw != null))
		{
			canvas.drawBitmap(this.m_bmpToDraw, _Position.x, _Position.y, _paint);
		}
	}

	public int getWidth() {
		return this.m_SourceBitmap1.getWidth()/this.m_frameCount;
	}

	public int getHeight() {
		return this.m_SourceBitmap1.getHeight();
	}	
	
	public void setRotation(int _Rotation)
	{
		this.m_mtxEffect.setRotate(_Rotation, this.m_SourceBitmap1.getWidth()/2, this.m_SourceBitmap1.getHeight()/2);
	}
	
	public void setFramePeriod(int fps)
	{
		if (fps == 0)
			fps = 1;
		this.m_framePeriod = 1000 / fps;
	}
}
