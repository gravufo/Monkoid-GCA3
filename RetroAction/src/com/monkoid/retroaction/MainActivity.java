package com.monkoid.retroaction;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.*;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
       Bitmap newGameBtn =  BitmapFactory.decodeResource(getResources(), R.drawable.new_game_btn_img);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    

}
