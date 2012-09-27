package game.androidPie;

import java.util.ArrayList;

import pro.to.type.Level.Cible;
import pro.to.type.Level.Freezer;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Level extends SurfaceView implements SurfaceHolder.Callback{

	private static final String TAG = GameThread.class.getSimpleName();
	
	private MultiTouchHandler handler;
	private Bridge bridge_;
	private Ball ball_;
	
	private Bitmap background_;
	private Resources m_Res;
	private int m_ScreenWidth;
	private int m_ScreenHeight;
	private MainThread m_Thread;
	private int m_CurrentMapX;
	private ArrayList<Ball> m_lstBallz;
	private ArrayList<Cible> m_lstCibles;
	private Freezer m_freezer;
	private Bitmap m_bmpCible;
	private Bitmap m_bmpFreezer;
	private Bitmap m_bmpSoccerAnimation;
	private Paint m_ScorePaint;
	private double m_GameTime;
	private boolean m_Paused;
	private boolean m_UnderFreeze;
	private boolean m_gameOver;
	private Lifes m_Lifes;
	private Activity m_Activity;
	private MediaPlayer m_Player;
	private MediaPlayer m_PlayerFreezer;
	private MediaPlayer m_PlayerBigSong;
	
	private int m_Score;
	private int countDown_;
	private float m_ScaleWidth;			// Facteur de grossissement selon la taille de l'écran
	private float m_ScaleHeight;		// Facteur de grossissement selon la taille de l'écran
	private Matrix m_Matrix;
	
	public Level(Context context,Activity _act, Resources _res) {
		super(context);
		// TODO Auto-generated constructor stub
		// adding the callback (this) to the surface holder to intercept events
	      getHolder().addCallback(this);
		 // make the GamePanel focusable so it can handle events
	      setFocusable(true);
	      this.m_Activity = _act;
		  this.m_CurrentMapX = 0;
		  this.m_Score = 0;
		  this.m_Res = _res;
		  this.m_GameTime = 0;
		  this.m_Paused = false;
		  this.m_UnderFreeze = false;
		  countDown_ = 50;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {	
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		this.m_ScreenWidth = this.getWidth();
		this.m_ScreenHeight = this.getHeight();
		
		m_Thread = new MainThread(holder, this);
		m_Thread.setRunning(true);
		m_Thread.start();
	}
	
	public void InitializeEverything()
	{
		if ((this.m_ScreenWidth != 0) && (this.m_ScreenHeight != 0))
		{
			bridge_ = new Bridge();
			background_ = BitmapFactory.decodeResource(this.m_Res, R.drawable.background);
			handler = new MultiTouchHandler(bridge_);
			this.m_lstCibles = new ArrayList<Cible>();
			this.m_lstBallz = new ArrayList<Ball>();
			this.m_ScorePaint = new Paint();
			this.m_ScorePaint.setColor(Color.RED);
			this.m_ScorePaint.setTextSize(28);
			this.m_ScorePaint.setTextAlign(Align.LEFT);
			this.m_bmpCible = BitmapFactory.decodeResource(this.m_Res, R.drawable.cible);
			this.m_bmpFreezer = BitmapFactory.decodeResource(this.m_Res, R.drawable.sablier);
			this.m_bmpSoccerAnimation = BitmapFactory.decodeResource(this.m_Res, R.drawable.soccer_animation);
			this.m_Lifes = new Lifes(this.m_ScreenWidth, this.m_ScreenHeight, 100);
			
			
			this.m_ScaleWidth = ((float) this.m_ScreenWidth) / (float)this.background_.getWidth();
	        this.m_ScaleHeight = ((float) this.m_ScreenHeight) / (float)this.background_.getHeight();
	        this.m_Matrix = new Matrix();
	        this.m_Matrix.postScale(this.m_ScaleWidth, this.m_ScaleHeight);
	        
	        this.background_ = Bitmap.createBitmap(this.background_, 0, 0, this.background_.getWidth(), this.background_.getHeight(), this.m_Matrix, true);
			
			this.m_Player = MediaPlayer.create(this.m_Activity, R.raw.soundcible);
			this.m_PlayerFreezer = MediaPlayer.create(this.m_Activity, R.raw.soundsablier);
			this.m_PlayerBigSong = MediaPlayer.create(this.m_Activity, R.raw.song);
			this.m_PlayerBigSong.start();
			this.m_Thread.setInitialized(true);
		}
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
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(canvas != null)
		{
			
			canvas.drawColor(Color.BLACK);
			if(background_ != null)
				canvas.drawBitmap(this.background_, 0, 0, null);
			if(m_gameOver){
				canvas.drawText("Votre Résultat : " + m_Score, this.m_ScreenWidth/2, this.m_ScreenHeight/2, this.m_ScorePaint);
			}else{
				if(bridge_ != null) 
					bridge_.draw(canvas);
				
				for(Cible _cible : this.m_lstCibles)
				{
					_cible.OnDraw(canvas);
				}
				for(Ball ball : this.m_lstBallz)
				{
					ball.draw(canvas);
				}
				if(m_freezer != null) m_freezer.OnDraw(canvas);
				//canvas.drawText(this.m_Score+"", 100, 40, this.m_ScorePaint);
				canvas.drawText(this.countDown_+"", 100, 40, this.m_ScorePaint);
				canvas.drawText((int)m_GameTime/1000+"", 300, 40, this.m_ScorePaint);
				this.m_Lifes.draw(canvas);
			}
		}
			
	}
	
	protected void update(){
		if(this.m_freezer!=null)this.m_freezer.UpdatePosition();
		for(Cible _cible : this.m_lstCibles)
		{
			_cible.UpdatePosition();
			
			if(_cible.isOut())
			{
				--countDown_;
				_cible.Kill();
			}
		}
		
		ArrayList<Cible> tmpListCible = new ArrayList<Cible>();
		for(Cible cible : this.m_lstCibles)
		{
			if (cible.getIsKill() == true)
			{
				tmpListCible.add(cible);
			}	
		}
		for(Cible cible : tmpListCible)
		{
			this.m_lstCibles.remove(cible);
		}
		
		
		for(Ball ball : this.m_lstBallz)
		{
			//background_.update(ball.getDeltaX());
			ball.update();
			if(ball.checkCollision(bridge_))ball.resolveCollision(bridge_);
			if(ball.getPos().x+ball.getDeltaX() > getWidth())
				ball.getPhysique().setV(new Vector2D(-ball.getPhysique().getVitesse().getX(), ball.getPhysique().getVitesse().getY()));
		}
		ArrayList<Ball> tmpList = new ArrayList<Ball>();
		for(Ball ball : this.m_lstBallz)
		{
			if (ball.getAlive() == false)
				tmpList.add(ball);
		}
		for(Ball ball : tmpList)
		{
			this.m_lstBallz.remove(ball);
		}
		
		
		for(Ball ball : this.m_lstBallz)
		{
			for(Cible _cible : this.m_lstCibles)
			{
				if (ball.CheckIntersection(_cible.getRect()))
				{
					this.m_Player.start();
					_cible.Kill();
					this.m_Score += 10;
				}
			}
			if(m_freezer != null && ball.CheckIntersection(m_freezer.getRect())) 
			{
				this.m_PlayerFreezer.start();
				this.m_freezer = null;
				ActivateFreeze(true);
				this.m_Score += 20;
			}
			
		}
	}
	
	public void endGame(){
		this.m_gameOver = true;
		this.m_ScorePaint.setTextAlign(Align.CENTER);
		this.m_ScorePaint.setTextSize(40);
	}
	
	protected void onPause(){
		
	}
	
	public void ActivateFreeze(boolean active)
	{
		this.m_Thread.ActivateFreeze(active);
		for(Cible _cible : this.m_lstCibles)
		{
			_cible.setFrozen(active);
		}
		this.m_UnderFreeze = active;
	}
	
	public void setGameTime(long _GameTime){
		this.m_GameTime = _GameTime;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//Sending the event to the event handler
		if (this.m_gameOver == false)
			handler.handleEvent(event);
		else 
			this.m_Activity.finish();
		return true;
	}
	
	public boolean isCountDownNull(){
		if(countDown_ <= 0)
			return true;
		
		return false;
	}
	
	public void AddACible()
	{
		this.m_lstCibles.add(new Cible(this.m_Res, this.m_bmpCible, this.m_ScreenWidth, this.m_ScreenHeight, this.m_UnderFreeze));
	}
	
	public void AddBall()
	{
		this.m_lstBallz.add(new Ball(this.m_Res, this.m_bmpSoccerAnimation, this.m_ScreenWidth, this.m_ScreenHeight));
	}
	
	public void AddFreezer()
	{
		this.m_freezer = new Freezer(this.m_Res, this.m_bmpFreezer, this.m_ScreenWidth, this.m_ScreenHeight);
	}
	
	
	public MainThread getMenuThread()
	{
		return this.m_Thread;
	}
	
	public void setCurrentMapX(int x)
	{
		this.m_CurrentMapX = x;
	}

	public int getCurrentMapX()
	{
		return this.m_CurrentMapX;
	}
	
	public boolean getPaused()
	{
		return this.m_Paused;
	}
	
	public void setPaused(boolean _bool)
	{
		this.m_Paused = _bool;
	}
}
