package com.monkoid.retroaction;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private View view_;
	private CidHandler cid;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.view_ = new View(this, this, this.getResources());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(this.view_);
        cid = CidHandler.getCidLife().initGame(this);
    }
    
    @Override
    protected void onDestroy()
    {
    	cid.gameToggle(false);
    	view_.mainThread_.Curly = false;
    	super.onDestroy();
    }
    
    @Override
    protected void onPause()
    {
    	cid.gameToggle(false);
    	view_.mainThread_.Curly = false;
    	super.onPause();
    }
    
	@Override
    protected void onResume()
    {
		super.onResume();
		cid.gameToggle(true);
    }
    
 /*   @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public static void onGameFinished()
    {
    	
    }
}
