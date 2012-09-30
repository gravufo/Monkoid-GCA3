package com.monkoid.retroaction;

import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;

public class TreeRender {
	
	public Vector<Drawable> drawables = new Vector<Drawable>();
	private Context context_ = null;
	public int size = 0;
	
	public TreeRender(){
	}
	
	public void add(Drawable drawable){
		drawables.add(drawable);
	}
	
	public void draw(Canvas canvas){
			for(Drawable d: drawables)
				d.onDraw(canvas);
	}
}
