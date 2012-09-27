package game.androidPie;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class Lifes {

	private int m_screenWidth, m_screenHeight;
	private int m_DimX, m_DimY;
	private int m_Lifes;
	private Paint m_fullPaint;
	
	public Lifes(int _sWidth, int _sHeight, int _nbrVies)
	{
		this.m_screenWidth = _sWidth;
		this.m_screenHeight = _sHeight;
		this.m_Lifes = _nbrVies;
		//this.m_Rect.bottom = 0;
		this.m_DimY = (int)(this.m_screenHeight/10);
		this.m_DimX = (int)(this.m_screenWidth/20);
		//this.m_Rect.left = 0;
		this.m_fullPaint = new Paint();
		this.m_fullPaint.setColor(Color.RED);
		this.m_fullPaint.setAlpha(80);
		this.m_fullPaint.setTextSize(80);
		this.m_fullPaint.setTextAlign(Align.RIGHT);
	}
	
	public void TakeoffLife()
	{
		this.m_Lifes--;
	}
	
	public void AddLife()
	{
		this.m_Lifes++;
	}
	
	public void draw(Canvas canvas)
	{
		canvas.drawText(this.m_Lifes + "", this.m_screenWidth-20, this.m_screenHeight/2, this.m_fullPaint);
	}
}
