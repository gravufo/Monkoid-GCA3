package game.androidPie;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;

public class GameActivity extends Activity{

	private Resources m_Res;
	private Level m_panel;
	private MediaPlayer m_Player;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.m_Res = this.getResources();
		this.m_Player = MediaPlayer.create(this, R.raw.whistle);
		this.m_Player.start();
		this.m_panel = new Level(this, this, this.getResources());
        this.setContentView(this.m_panel);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		this.m_panel.setPaused(true);
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Go To The Homemenu ?")
    	       .setCancelable(false)
    	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	               finish();
    	           }
    	       })
    	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   m_panel.setPaused(false);
    	        	   dialog.cancel();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
		return alert;
	}
	
	
	@Override
	// Si la touche back est pressé, on retourne au menu
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	this.onCreateDialog(5);
	    }
	    return false;
	}
}
