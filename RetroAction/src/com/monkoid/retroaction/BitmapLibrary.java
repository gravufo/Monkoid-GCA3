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
	private Context context_;
	public static BitmapLibrary getGreen(){
		if(green_ == null)
			green_ = new BitmapLibrary();
		return green_;	
	}
	
	private BitmapLibrary(){
		
	}
	
	public void init(Context context){
		context_ = context;
		KangooBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.kangoo);
		BlocblueBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_pixel_proto);
		BlocgreenBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_pixel_proto);
		BlocpurpleBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple_pixel_proto);
		BlocyellowBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow_pixel_proto);
		BlocredBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_pixel_proto);

	}
}