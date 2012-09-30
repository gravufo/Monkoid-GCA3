package com.monkoid.retroaction;

import android.content.Context;
import android.media.MediaPlayer;

public class CidHandler{

	private static CidHandler cid_life = null;
	public static MediaPlayer playerIntro = null;
	public static MediaPlayer playerGame = null;
	
	public static CidHandler getCidLife()
	{
		if(cid_life == null){
			cid_life = new CidHandler();
		}
		return cid_life;
	}
	
	public CidHandler initIntro(Context context){
		playerIntro = MediaPlayer.create(context, R.raw.intro);
		this.introToggle(true);
		return this;
	}
	
	public CidHandler initGame(Context context){
		playerGame = MediaPlayer.create(context, R.raw.game);
		this.gameToggle(true);
		return this;
	}
	
	private CidHandler(){
		
	}
	
	public void introToggle(boolean enable)
	{
		if(playerIntro != null)
		if(enable && !playerIntro.isPlaying())
		{
			playerIntro.start();
			playerIntro.setLooping(true);
		}
		else if(!enable && playerIntro.isPlaying())
		{
			playerIntro.stop();
		}
	}
	
	public void gameToggle(boolean enable)
	{
		if(playerGame != null)
		if(enable && !playerGame.isPlaying())
		{
			playerGame.start();
			playerGame.setLooping(true);
		}
		else if(!enable && playerGame.isPlaying())
		{
			playerGame.stop();
		}
	}
}
