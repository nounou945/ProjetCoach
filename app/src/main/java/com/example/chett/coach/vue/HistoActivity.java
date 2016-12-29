package com.example.chett.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.chett.coach.R;
import com.example.chett.coach.controleur.Controle;
import com.example.chett.coach.modele.Profil;

import java.util.ArrayList;

public class HistoActivity extends AppCompatActivity {
    private ImageButton imgAc;
    private Controle controle;
    public HistoActivity(){
        controle=Controle.getInstance(HistoActivity.this);
        controle.setHistoActivity(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        creerAccueil();
        crerListe();
    }

    public void creerAccueil(){
        imgAc=(ImageButton)findViewById(R.id.imgAc);
        ecouteAccueil(imgAc,MainActivity.class);
    }
    public void ecouteAccueil(ImageButton image,final Class classe){
        image.setOnClickListener
                (new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(HistoActivity.this,classe);
                        startActivity(intent);


                    }
                });
    }

    public  void crerListe(){
        ArrayList<Profil>liste=new ArrayList<Profil>();
        liste=controle.getLesProfils();
        ListView lv = (ListView) findViewById(R.id.lstHisto);
        HistoListAdapter objet=new HistoListAdapter(HistoActivity.this,liste);
        lv.setAdapter(objet);

    }
}

