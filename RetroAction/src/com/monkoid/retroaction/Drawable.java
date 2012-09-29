package com.monkoid.retroaction;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface Drawable {
	public void onDraw(Canvas canvas);
	public void initBmp(Bitmap bmp);
}
