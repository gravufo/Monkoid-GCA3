package game.androidPie;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

	private SurfaceHolder m_surfaceHolder;
	private Level m_LevelPanel;
	private MenuSView m_MenuPanel;
	private boolean m_run;
	private boolean m_initialized;
	private long m_ElaspedTimeCIBLE, m_NewTimeCIBLE, m_OldTimeCIBLE;
	private long m_ElaspedTimeBALL, m_NewTimeBALL, m_OldTimeBALL;
	private long m_ElaspedTimeFREEZER, m_NewTimeFREEZER, m_OldTimeFREEZER;
	private long m_ElaspedTimeFROZEN, m_NewTimeFROZEN, m_OldTimeFROZEN;
	private long m_ElaspedGameTime, m_NewGameTime, m_OldGameTime, m_MaxGameTime;
	private boolean m_gameOver = false;
	
	enum TypeOfView {Game, Menu};
	private TypeOfView m_TypeOfView;
	
	public MainThread(SurfaceHolder surfaceHolder, Level panel)
	{
		this.m_surfaceHolder = surfaceHolder;
		this.m_LevelPanel = panel;
		this.m_TypeOfView = TypeOfView.Game;
		this.m_run = false;
		
		this.m_ElaspedGameTime = 0;
		this.m_NewGameTime = System.currentTimeMillis();
		this.m_OldGameTime = m_NewGameTime;
		this.m_MaxGameTime = 30000;
		
		this.m_ElaspedTimeCIBLE = 0;
		this.m_NewTimeCIBLE = System.currentTimeMillis();
		this.m_OldTimeCIBLE = this.m_NewTimeCIBLE;
		
		this.m_ElaspedTimeBALL = 0;
		this.m_NewTimeBALL = System.currentTimeMillis();
		this.m_OldTimeBALL = this.m_NewTimeBALL;
		
		this.m_ElaspedTimeFREEZER = 0;
		this.m_NewTimeFREEZER = System.currentTimeMillis();
		this.m_OldTimeFREEZER = this.m_NewTimeFREEZER;
		
		this.m_ElaspedTimeFROZEN = 0;
		this.m_NewTimeFROZEN = 0;
		this.m_OldTimeFROZEN = this.m_NewTimeFROZEN;
	}
	
	public MainThread(SurfaceHolder surfaceHolder, MenuSView panel)
	{
		this.m_surfaceHolder = surfaceHolder;
		this.m_MenuPanel = panel;
		this.m_TypeOfView = TypeOfView.Menu;
		this.m_run = false;
	}
	
	@Override
    public void run() {
        Canvas c;
        while (this.m_run) {
            c = null;
            try 
            {
            	if (this.m_initialized == true)
            	{
	                c = this.m_surfaceHolder.lockCanvas(null);
	                synchronized (this.m_surfaceHolder) 
	                {
	                	switch(this.m_TypeOfView)
	                	{
		                	case Game :
		                	{
		                		if (!this.m_LevelPanel.getPaused())
		                		{
			                		this.m_NewTimeCIBLE = System.currentTimeMillis();
			                		this.m_ElaspedTimeCIBLE += this.m_NewTimeCIBLE - this.m_OldTimeCIBLE;
			                		
			                		this.m_NewTimeBALL = System.currentTimeMillis();
			                		this.m_ElaspedTimeBALL += this.m_NewTimeBALL - this.m_OldTimeBALL;
			                		
			                		this.m_NewTimeFREEZER = System.currentTimeMillis();
			                		this.m_ElaspedTimeFREEZER += this.m_NewTimeFREEZER - this.m_OldTimeFREEZER;
			                		
			                		if(this.m_NewTimeFROZEN > 0)this.m_NewTimeFROZEN = System.currentTimeMillis();
			                		this.m_ElaspedTimeFROZEN += this.m_NewTimeFROZEN - this.m_OldTimeFROZEN;
			                		
			                		this.m_NewGameTime = System.currentTimeMillis();
			                		this.m_ElaspedGameTime += this.m_NewGameTime - this.m_OldGameTime;
			                		
			                		//if (this.m_ElaspedGameTime > this.m_MaxGameTime)
			                		if(m_LevelPanel.isCountDownNull())
			                			m_LevelPanel.endGame();
			                		
			                		if (this.m_ElaspedTimeCIBLE > 3000)
			                		{
			                			this.m_ElaspedTimeCIBLE = 0;
			                			this.m_LevelPanel.AddACible();
			                			
			                		}
			                		
			                		if (this.m_ElaspedTimeBALL > 2000)
			                		{
			                			this.m_ElaspedTimeBALL = 0;
			                			this.m_LevelPanel.AddBall();
			                		}
			                		
			                		if(this.m_ElaspedTimeFREEZER > 15000)
			                		{
			                			this.m_ElaspedTimeFREEZER = 0;
			                			this.m_LevelPanel.AddFreezer();
			                		}
			                		
			                		if(this.m_ElaspedTimeFROZEN > 5000)
			                		{
			                			this.m_ElaspedTimeFROZEN = 0;
			                			this.m_NewTimeFROZEN = 0;
			                			m_LevelPanel.ActivateFreeze(false);
			                		}
			                		
			                		this.m_LevelPanel.update();
			                		this.m_LevelPanel.setGameTime(m_ElaspedGameTime);
			                		this.m_LevelPanel.onDraw(c);
				                	this.m_LevelPanel.setCurrentMapX(this.m_LevelPanel.getCurrentMapX() + 2);
			                		this.m_OldTimeCIBLE = this.m_NewTimeCIBLE;
			                		this.m_OldTimeBALL = this.m_NewTimeBALL;
			                		this.m_OldTimeFREEZER = this.m_NewTimeFREEZER;
			                		this.m_OldTimeFROZEN = this.m_NewTimeFROZEN;
			                		this.m_OldGameTime = this.m_NewGameTime;
			                		
			                			
		                		}
		                		break;
		                	}
		                	case Menu :
		                	{
		                		this.m_MenuPanel.onDraw(c);
		                	}
	                	}
	                	
	                } 
            	}
            	else
            	{
            		switch(this.m_TypeOfView)
                	{
	                	case Game :
	                	{
	                		this.m_LevelPanel.InitializeEverything();
	                		break;
	                	}
	                	case Menu :
	                	{
	                		this.m_MenuPanel.InitializeEverything();
	                		break;
	                	}
                	}
            	}
            }
            finally 
            {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) 
                {
                	this.m_surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
	
	public void ActivateFreeze(boolean active){
		if(true)this.m_NewTimeFROZEN = System.currentTimeMillis();
	}
	
	public void setRunning(boolean run) {
    	this.m_run = run;
    }

	public void setInitialized(boolean _ini)
	{
		this.m_initialized = _ini;
	}
	
	public boolean isGameOver(){
		return m_gameOver;
	}
}
