package com.monkoid.retroaction;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapLibrary {
	private static BitmapLibrary green_ = null;
	public Bitmap KangooBmp_;
	public Bitmap PlatformBmp_;
	
	public static BitmapLibrary getGreen(){
		if(green_ == null)
			green_ = new BitmapLibrary();
		return green_;	
	}
	
	private BitmapLibrary(){
		
	}
	
	public void init(Context context){
		KangooBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.kangoo);
		PlatformBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.new_game_btn_img);
	}
}