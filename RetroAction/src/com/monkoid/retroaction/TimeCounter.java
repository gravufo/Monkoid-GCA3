package com.monkoid.retroaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

public class TimeCounter implements Drawable {
	public long timeInNs = 0;
	public long delta = 0;
	public long start = 0;
	public Intent intent;
	public Activity activity;
	public View view;
	public int size;

	public TimeCounter( long timeInNs,Intent i, Activity activity, View view) {
		super();
		this.timeInNs = timeInNs;
		intent = i;
		this.activity = activity;
		this.view = view;
		size = 80;
	}

	public void onDraw(Canvas canvas) {
		if(canvas != null)
		{
			if(this.start == 0){
				this.start = System.currentTimeMillis();
				this.delta =0;
			}
			else{
				this.delta = System.currentTimeMillis() - start;
			}
				long temp = (timeInNs - delta);
				Integer temp1 =  (int)temp;
				
				if(temp1 <= 0){
					view.mainThread_.Curly=false;
					Bundle dataBundle = new Bundle();

					dataBundle.putInt("pointage", view.t.UpdateCaRace());

					this.intent.putExtra("pointage",view.t.UpdateCaRace().intValue());
					this.activity.startActivity(this.intent);
					temp1 = 0;
				}
					
			if(temp1 < 10000)
				size=90;
			if(temp1 < 5000)
				size=110;
			if(temp1<1000)
				size =130;
			
			Double temps = (double)temp1;
			temps/=1000;
			Paint p = new Paint();
			p.setColor(Color.WHITE);
			p.setStyle(Paint.Style.FILL);
			p.setAntiAlias(true);
			p.setTextSize(size);
			temps+=0.000000001; // I AM THE BOSS
			canvas.drawText(temps.toString().substring(0, 4)+" sec", 150,150, p);
		}

	}

}
