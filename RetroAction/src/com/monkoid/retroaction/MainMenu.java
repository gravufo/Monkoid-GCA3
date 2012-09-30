package com.monkoid.retroaction;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends Activity implements OnClickListener {

	private Button newGame_;
	private Button exit_;
	private TextView  points;
	private CidHandler cid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main_menu);
		newGame_ = (Button) findViewById(R.id.bNew);
		exit_ = (Button) findViewById(R.id.bExit);
		points = (TextView)findViewById(R.id.textView1);
		newGame_.setOnClickListener(this);
		exit_.setOnClickListener(this);
		cid = CidHandler.getCidLife().initIntro(this);
		points.setText("");
	}

	@Override
    protected void onDestroy()
    {
		cid.introToggle(false);
    	super.onDestroy();
    }
	
	@Override
    protected void onPause()
    {
		cid.introToggle(false);
    	super.onPause();
    }
    
	@Override
    protected void onResume()
    {
		super.onResume();
		cid.gameToggle(false);
		cid.introToggle(true);
		int latValue;
		Bundle extras = getIntent().getExtras(); 
		if(extras !=null)
		{
			latValue= (int)extras.getInt("pointage");
			points.setText("Last game score :"+((Integer)latValue).toString()+" pts");
		}
    }
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClick(android.view.View arg0) {
		   switch(arg0.getId()) {
	        case R.id.bExit:
	            finish();
	            break;
	        case R.id.bNew:
	        	Intent game = new Intent(MainMenu.this,MainActivity.class);
	        	startActivity(game);
	        	break;
	        
	    }
	}
}
