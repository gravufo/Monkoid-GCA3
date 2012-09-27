package game.androidPie;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MenuSView extends SurfaceView implements SurfaceHolder.Callback{

	private Bitmap m_Background, m_buttonPlay, m_buttonQuit;
	private Point m_PBPlay, m_PBQuit;
	private Resources m_Res;
	private int m_ScreenWidth;
	private int m_ScreenHeight;
	private MainThread m_Thread;
	//private RectF m_PlayButton;
	private Paint m_ButtonPaint;
	private Activity m_Activity;
	
	private float m_ScaleWidth;			// Facteur de grossissement selon la taille de l'écran
	private float m_ScaleHeight;		// Facteur de grossissement selon la taille de l'écran
	private Matrix m_Matrix;
	
	public MenuSView(Context context, Activity _activity, Resources _res) {
		super(context);
		this.m_Res = _res;
		this.m_Activity = _activity;
		getHolder().addCallback(this);
	    setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		this.m_ScreenWidth = this.getWidth();
	    this.m_ScreenHeight = this.getHeight();		
	    
	    
        this.m_Thread = new MainThread(getHolder(), this);
		this.m_Thread.setRunning(true);
		this.m_Thread.start();
	}
	
	
	public void InitializeEverything()
	{
		if ((this.m_ScreenWidth != 0) && (this.m_ScreenHeight != 0))
		{
			this.m_Background = BitmapFactory.decodeResource(this.m_Res,R.drawable.background);
			this.m_buttonPlay = BitmapFactory.decodeResource(this.m_Res,R.drawable.buttonplay);
			this.m_buttonQuit = BitmapFactory.decodeResource(this.m_Res,R.drawable.buttonquit);
			this.m_PBPlay = new Point(this.m_ScreenWidth/2 - this.m_buttonPlay.getWidth()/2, this.m_ScreenHeight/2 - this.m_buttonPlay.getHeight() - 15);
			this.m_PBQuit = new Point(this.m_ScreenWidth/2 - this.m_buttonQuit.getWidth()/2, this.m_ScreenHeight/2 + 15);
			//this.m_PlayButton = new RectF(20,20, 100, 100);
			
			this.m_ScaleWidth = ((float) this.m_ScreenWidth) / (float)this.m_Background.getWidth();
	        this.m_ScaleHeight = ((float) this.m_ScreenHeight) / (float)this.m_Background.getHeight();
	        this.m_Matrix = new Matrix();
	        this.m_Matrix.postScale(this.m_ScaleWidth, this.m_ScaleHeight);
	        this.m_Background = Bitmap.createBitmap(this.m_Background, 0, 0, this.m_Background.getWidth(), this.m_Background.getHeight(), this.m_Matrix, true);
			
	        
	        
			this.m_ButtonPaint = new Paint();
			this.m_ButtonPaint.setColor(Color.RED);
			this.m_Thread.setInitialized(true);
		}
	}

	@Override
	// On dessine le background et le ballon
	public void onDraw(Canvas canvas) {
		if (canvas != null)
		{
			canvas.drawBitmap(this.m_Background, 0, 0, null);
			canvas.drawBitmap(this.m_buttonPlay, this.m_PBPlay.x, this.m_PBPlay.y, null);
			canvas.drawBitmap(this.m_buttonQuit, this.m_PBQuit.x, this.m_PBQuit.y, null);
			//canvas.drawRect(this.m_PlayButton, this.m_ButtonPaint);
		}
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			if (this.ButtonContainClick(this.m_PBPlay, this.m_buttonPlay, new Point((int)event.getX(), (int)event.getY())) == true)
			{
				this.m_Thread.setInitialized(false);
				Intent intent = new Intent(this.getContext(), GameActivity.class);
				this.m_Activity.startActivityForResult(intent, 1);
			}
			else if (this.ButtonContainClick(this.m_PBQuit, this.m_buttonQuit, new Point((int)event.getX(), (int)event.getY())) == true)
			{
				this.m_Activity.finish();
			}
		}
		return super.onTouchEvent(event);
	}


	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		this.m_Thread.setRunning(false);
		while (retry) {
			try {
				this.m_Thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}
	}

	public boolean ButtonContainClick(Point _posImg, Bitmap _Img, Point _posClick)
	{
		if ((_posClick.x >= _posImg.x) && (_posClick.x <= _posImg.x + _Img.getWidth()) && (_posClick.y >= _posImg.y) && (_posClick.y <= _posImg.y + _Img.getHeight()))
			return true;
		else
			return false;
	}
}
