package com.monkoid.retroaction;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapLibrary {
	private static BitmapLibrary green_ = null;
	public Bitmap KangooBmp_;
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
	}
}