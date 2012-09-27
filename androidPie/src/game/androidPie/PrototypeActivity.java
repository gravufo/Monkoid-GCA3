package game.androidPie;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;

public class PrototypeActivity extends ActivityGroup {
    
	private Resources m_Res;
	private MenuSView m_panel;
	private MediaPlayer m_Player;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.m_Res = this.getResources();
        this.m_panel = new MenuSView(this, this, this.m_Res);
        this.m_Player = MediaPlayer.create(this, R.raw.song);
        this.m_Player.start();
        this.setContentView(this.m_panel);
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1)
		{
			this.m_panel = new MenuSView(this, this, this.m_Res);
	        this.setContentView(this.m_panel);
		}
    }
    
    @Override
	protected void onPause() {
			this.m_Player.pause();
		super.onPause();
	}


	@Override
	protected void onResume() {
		super.onResume();
			this.m_Player.start();
	}

	@Override
	protected void onDestroy() {
		this.m_Player.stop();
		super.onDestroy();
	}
	
	
}