package com.monkoid.retroaction;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapLibrary {
	private static BitmapLibrary green_ = null;
	public Bitmap KangooBmp_;
	public Bitmap BlocblueBmp_;
	public Bitmap BlocgreenBmp_;
	public Bitmap BlocpurpleBmp_;
	public Bitmap BlocredBmp_;
	public Bitmap BlocyellowBmp_;
	public Bitmap PlatformBmp_;
	public Bitmap BlocInvisibleBmp_;
	private Context context_;

	
	
	public static BitmapLibrary getGreen(){
		if(green_ == null)
			green_ = new BitmapLibrary();
		return green_;	
	}
	
	private BitmapLibrary(){
		
	}
	
	public void init(Context context){
		KangooBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.kangoo);
		BlocblueBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue64);
		BlocgreenBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.green64);
		BlocpurpleBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple64);
		BlocyellowBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow64);
		BlocredBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.red64);
		PlatformBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.new_game_btn_img);
		BlocInvisibleBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty64);
	}
}