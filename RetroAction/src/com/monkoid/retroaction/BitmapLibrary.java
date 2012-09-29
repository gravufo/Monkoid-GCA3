package com.monkoid.retroaction;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapLibrary {
	
	enum COLORS {RED, YELLOW, BLUE, GREEN, PURPLE, EMPTY};
	
	private static BitmapLibrary green_ = null;
	public Bitmap KangooBmp_;
	public Bitmap Blocblue64_;
	public Bitmap Blocgreen64_;
	public Bitmap Blocpurple64_;
	public Bitmap Blocred64_;
	public Bitmap Blocyellow64_;
	public Bitmap BlocInvisible64_;
	public Bitmap Blocblue32_;
	public Bitmap Blocgreen32_;
	public Bitmap Blocpurple32_;
	public Bitmap Blocred32_;
	public Bitmap Blocyellow32_;
	public Bitmap BlocInvisible32_;
	public Bitmap Blocblue16_;
	public Bitmap Blocgreen16_;
	public Bitmap Blocpurple16_;
	public Bitmap Blocred16_;
	public Bitmap Blocyellow16_;
	public Bitmap BlocInvisible16_;
	private Context context_;
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
		Blocblue64_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue64);
		Blocgreen64_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.green64);
		Blocpurple64_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple64);
		Blocyellow64_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow64);
		Blocred64_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.red64);
		BlocInvisible64_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty64);
		Blocblue32_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue32);
		Blocgreen32_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.green32);
		Blocpurple32_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple32);
		Blocyellow32_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow32);
		Blocred32_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.red32);
		BlocInvisible32_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty32);
		Blocblue16_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue16);
		Blocgreen16_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.green16);
		Blocpurple16_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple16);
		Blocyellow16_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow16);
		Blocred16_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.red16);
		BlocInvisible16_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty16);
		PlatformBmp_ = BitmapFactory.decodeResource(context.getResources(), R.drawable.center16);
	}
}