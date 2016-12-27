package com.example.chett.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.example.chett.coach.R;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgmonImg ;
    private ImageButton imgHistorique;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creerMenu();
    }

    public void creerMenu(){
        imgmonImg = (ImageButton) findViewById(R.id.imgmonImg) ;
        imgHistorique=(ImageButton) findViewById((R.id.imgHistorique));
        ecouteMenu(imgmonImg,CalculActivity.class);
        ecouteMenu(imgHistorique,MainActivity.class );


    }

    public void ecouteMenu(ImageButton image, final Class classe) {

     image.setOnClickListener
             (new ImageButton.OnClickListener() {
                 public void onClick(View v) {
                     Intent intent = new Intent(MainActivity.this,classe);
                     startActivity(intent);


                 }
             });
 }

}





