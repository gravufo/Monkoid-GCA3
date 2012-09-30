package com.monkoid.retroaction;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapLibrary {
	
	enum COLORS {RED, YELLOW, BLUE, GREEN, PURPLE, EMPTY};
	
	private static BitmapLibrary green_ = null;
	public Bitmap KangooBmp_;
	public Bitmap Blocblue_;
	public Bitmap Blocgreen_;
	public Bitmap Blocpurple_;
	public Bitmap Blocred_;
	public Bitmap Blocyellow_;
	public Bitmap BlocInvisible_;
	public Bitmap Bloclaservertical_;
	public Bitmap Bloclaserhorizontale_;
	public Bitmap PlatformBmp_;

	public static BitmapLibrary getGreen(){
		if(green_ == null)
			green_ = new BitmapLibrary();
		return green_;	
	}
	private BitmapLibrary(){
		
	}
	
	public void init(Context context, int size){
		KangooBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.kangoo);
		KangooBmp_ = Bitmap.createScaledBitmap(KangooBmp_, size, size ,false);
		
		Blocblue_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue64);
		Blocblue_ = Bitmap.createScaledBitmap(Blocblue_, size, size ,false);
		
		Blocgreen_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.green64);
		Blocgreen_ = Bitmap.createScaledBitmap(Blocgreen_, size, size ,false);
		
		Blocpurple_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple64);
		Blocpurple_ = Bitmap.createScaledBitmap( Blocpurple_, size, size ,false);
		
		Blocyellow_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow64);
		Blocyellow_ = Bitmap.createScaledBitmap( Blocyellow_, size, size ,false);
		
		Blocred_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.red64);
		Blocred_ = Bitmap.createScaledBitmap( Blocred_, size, size ,false);
		
		Bloclaservertical_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.laser64vertical);
		Bloclaservertical_ = Bitmap.createScaledBitmap( Bloclaservertical_, size, size ,false);
		
		Bloclaserhorizontale_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.laser64horizontal);
		Bloclaserhorizontale_ = Bitmap.createScaledBitmap( Bloclaserhorizontale_, size, size ,false);
		
		BlocInvisible_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty64);
		BlocInvisible_ = Bitmap.createScaledBitmap( BlocInvisible_, size, size ,false);
		
		
		PlatformBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.center64);
		PlatformBmp_ = Bitmap.createScaledBitmap( PlatformBmp_, size, size ,false);
	}
}