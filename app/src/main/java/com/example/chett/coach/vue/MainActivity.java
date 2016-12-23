package com.example.chett.coach.vue;

import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chett.coach.R;
import com.example.chett.coach.controleur.Controle;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class MainActivity extends AppCompatActivity {

    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme ;
    private ImageView imgSmiley ;
    private TextView lblIMG; //label de l'image du smiley
    private Controle controle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * lien avec les objs graph
     */
    public void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids) ; // permet de récupérer un objet graphique à partir de son id.
        txtTaille = (EditText) findViewById(R.id.txtTaille) ;
        txtAge = (EditText) findViewById(R.id.txtAge) ;
        rdHomme = (RadioButton) findViewById(R.id.rdHomme) ;
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley) ;
        lblIMG = (TextView) findViewById(R.id.lblIMG) ;
        rdFemme = (RadioButton) findViewById(R.id.rdFemme) ;
        controle=Controle.getInstance(this); //pq ??
        ecouteCalcul();
        recupProfil(); //on la commenter car elle n'etais pas encore cree puis decommenter
    }

    /**
     *
     */
    public void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener
                (new Button.OnClickListener() { //setOnClikListener qui permet d'affecter un listener (donc une écoute)
                    public void onClick(View v) {
                        Integer poids = 0;
                        Integer taille = 0;
                        Integer age = 0;
                        Integer sexe = 0;
                       // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                        try {
                            poids = Integer.parseInt(txtPoids.getText().toString());
                            taille = Integer.parseInt(txtTaille.getText().toString());
                            age = Integer.parseInt(txtAge.getText().toString());
                            sexe = 0;
                        }catch (Exception e){

                        }
                        if(rdHomme.isChecked()){
                            sexe=1;
                        }
                        if(taille == 0 ||age == 0 || poids==0){
                            Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            affichResult(poids,taille,age,sexe);
                        }


                    } // makeText de la classe Toast permet d'afficher quelques instant un message sur le téléphone
                });
    }

    private void affichResult(int poids,int taille , int age,int sexe){

        controle.creerProfil(poids,taille,age,sexe,this);
        String monMessage= controle.getMessage();
        float monImg= controle.getImg();
        if(sexe== 0){
            if(monImg<15){
                imgSmiley.setImageResource(R.drawable.graisse);

                lblIMG.setTextColor(RED);
            }
            if(monImg<=30){
                imgSmiley.setImageResource(R.drawable.normal);
                lblIMG.setTextColor(GREEN);
            }

            if(monImg>30){
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setTextColor(RED);
            }

        }

        else {
            //Test pour Homme
            if (monImg < 10) {
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setTextColor(RED);
            } else {
                if (monImg <= 25) {
                    imgSmiley.setImageResource(R.drawable.normal);
                    lblIMG.setTextColor(GREEN);
                } if(monImg>25){
                    imgSmiley.setImageResource(R.drawable.graisse);
                    lblIMG.setTextColor(RED);
                }
            }
        }
        lblIMG.setText("Votre IMG est de  : "+monImg+". "+monMessage);
    }

    /**
     * recupere les donner du profil
     */
    private void recupProfil(){
     if(controle.getTaille()!=0){
         txtTaille.setText(""+controle.getTaille());
        }
      if(controle.getPoids()!=0){
            txtPoids.setText(""+controle.getPoids());
        }
      if(controle.getAge()!=0){
          txtAge.setText(""+controle.getAge());
      }
        if(controle.getSexe()==1){
            rdHomme.setChecked(true);
        }
        if(controle.getSexe()==0){
            rdFemme.setChecked(true);
    }
        findViewById(R.id.btnCalc).performClick();

    }

}


