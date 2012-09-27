package game.androidPie;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
	
	private static final String TAG = GameThread.class.getSimpleName();
	
	private boolean running;
	private SurfaceHolder surfaceHolder;
	private Level level_;

	public GameThread(SurfaceHolder surfaceHolder, Level level){
		super();
		
		this.surfaceHolder = surfaceHolder;
		level_ = level;		
	}
	
	public void setRunning(boolean running){
		this.running = running;
	}
	
	@Override
	public void run(){
		long tickCount = 0L;
		Log.d(TAG, "Starting game loop");
		Canvas canvas = new Canvas();
		while(running){
			canvas = null;
			try{
				canvas = surfaceHolder.lockCanvas(null); 
				synchronized (surfaceHolder) {
					level_.onDraw(canvas);
				}
			}finally{
				if(canvas != null) surfaceHolder.unlockCanvasAndPost(canvas);
			}
			tickCount++;
		}
		Log.d(TAG, "Game loop executed " + tickCount + "times");
	}
}
