package game.androidPie;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;


public class Ball{ //extends Shape {

	 private int radius_;
	 private Point pos_;
	 private int deltaX_, posX_ = 150; //la position de la balle est fixe en X car on déplace le background pour l'effet de déplacement en X
	 private Bitmap image_;
	 private Physique physique_;
	 private Animation m_BallAnim;
	 private Point centre_;
	 private int m_ScreenHeight;
	 private boolean m_Alive;
	 private Rect m_bdnRect;

	 private static final String TAG = Ball.class.getSimpleName();
	 
	 public Ball(Resources r, Bitmap _bmp, int _sWidth, int _sHeight){
		 this.m_ScreenHeight = _sHeight;
		 this.m_Alive = true;
		 radius_ = 32;
		 Random generator = new Random();
		 pos_ = new Point(generator.nextInt(_sWidth/3 - 30) + 60, -70);
		 physique_ = new Physique(pos_, new Vector2D(0, 0.1f), new Vector2D(0, 0.0003f));	 
		 //image_ = BitmapFactory.decodeResource(r, imageId);
		 image_ = _bmp;
		 deltaX_ = radius_*2;
		 centre_ = new Point(posX_ + image_.getHeight()/2, pos_.y + image_.getHeight()/2);
		 this.m_BallAnim = new Animation(_bmp, (int)(80*this.physique_.getVitesse().x), 10);
		 this.m_bdnRect = new Rect();
		 this.MakeBoundingBox();
	 }
	 
	// @Override
	 public void draw(Canvas canvas){
		 //canvas.drawBitmap(image_, pos_.x, pos_.y, null); 
		 this.m_BallAnim.draw(canvas, new PointF(pos_.x, pos_.y), null);
	 }
	 
	 public boolean checkCollision(Bridge bridge){
		 if(bridge.getP2() != null
			&& bridge.getP1() != bridge.getP2()
			&& (Math.sqrt((bridge.closestPoint(centre_).x-centre_.x)*(bridge.closestPoint(centre_).x-centre_.x) + 
				 (bridge.closestPoint(centre_).y-centre_.y)*(bridge.closestPoint(centre_).y-centre_.y))) < radius_)
			 return true;
		 return false;
	 }
	 
	 public void resolveCollision(Bridge bridge){
		Log.d(TAG, "*****************************************************************");
		
		Point oldPos = pos_;
		Point newPos = new Point((int)(oldPos.x+physique_.getVitesse().x),(int)(oldPos.y+physique_.getVitesse().y));
		
		Point closest = bridge.closestPoint(pos_);
		
		Vector2D normal = new Vector2D(pos_.x - closest.x, pos_.y - closest.y);
		Vector2D oldSpeed = physique_.getVitesse();
		
		double length = Math.sqrt(oldSpeed.x*oldSpeed.x + oldSpeed.y*oldSpeed.y);
		
		Vector2D newSpeed = new Vector2D(oldSpeed.x + normal.x, oldSpeed.y + normal.y);
		
		float newSpeedX = (float)(newSpeed.x * length / Math.sqrt(newSpeed.x*newSpeed.x + newSpeed.y*newSpeed.y));
		float newSpeedY = (float)(newSpeed.y * length / Math.sqrt(newSpeed.x*newSpeed.x + newSpeed.y*newSpeed.y));
		
		physique_.setV(new Vector2D(newSpeedX*1.2f, newSpeedY*1.2f));
		
		newPos = new Point((int)(oldPos.x+physique_.getVitesse().x), (int)(oldPos.y+physique_.getVitesse().y)); 
		
		pos_ = newPos;
	 }
	 
	 public Point getNextPos(){
		 return new Point((int)(pos_.x+physique_.getVitesse().x),(int)(pos_.y+physique_.getVitesse().y));
	 }
	 
	 public void update(){
		 //deltaX_ = pos_.x;
		 physique_.calcNewPosition();
		 pos_ = physique_.getPosition();
		 //deltaX_ = pos_.x - deltaX_;
		 centre_.x = pos_.x + image_.getHeight()/2;
		 centre_.y = pos_.y + image_.getHeight()/2;
		 this.m_BallAnim.setFramePeriod((int)(80*this.physique_.getVitesse().x));
		 this.m_BallAnim.update(System.currentTimeMillis());
		 this.MakeBoundingBox();
		 if (this.pos_.y > this.m_ScreenHeight)
			 this.m_Alive = false;
	 }

	 public void MakeBoundingBox()
	 {
			this.m_bdnRect.bottom = (int)(this.pos_.y + this.m_BallAnim.getHeight());
			this.m_bdnRect.top = (int)(this.pos_.y);
			this.m_bdnRect.right = (int)(this.pos_.x + this.m_BallAnim.getWidth());
			this.m_bdnRect.left = (int)(this.pos_.x);
	 }
	 
	 public boolean CheckIntersection(Rect _rectCible)
	 {
		 if (this.m_bdnRect.intersect(_rectCible) == true)
			 return true;
		 else
			 return false;
	 }
	 
	 public Point getPos(){
		 return pos_;
	 }

	 public int getDeltaX(){
		 return deltaX_;
	 }
	 
	 public boolean getAlive()
	 {
		 return this.m_Alive;
	 }
	 
	 public Physique getPhysique(){
		 return this.physique_;
	 }
}


