package com.monkoid.retroaction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {

	private View view_;
	private Button newGame_;
	 private  Button exit_;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_menu);
        newGame_ = (Button)findViewById(R.id.bNew);
        exit_ = (Button)findViewById(R.id.bExit);
        newGame_.setOnClickListener(this);
        exit_.setOnClickListener(this);

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
