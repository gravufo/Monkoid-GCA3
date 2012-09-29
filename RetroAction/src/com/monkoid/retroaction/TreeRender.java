package com.monkoid.retroaction;

import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;

public class TreeRender {
	
	public Vector drawables = new Vector();
	private Context context_ = null;
	
	public TreeRender(){
	}
	
	public void add(Drawable drawable){
		drawables.add(drawable);
	}
	
	public void draw(Canvas canvas){
		Iterator vItr = drawables.iterator(); 
		while(vItr.hasNext())
			((Drawable) vItr.next()).onDraw(canvas);
	}
}
