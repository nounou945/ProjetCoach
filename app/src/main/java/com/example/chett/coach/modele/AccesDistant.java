package com.example.chett.coach.modele;

import android.util.Log;

import com.example.chett.coach.controleur.Controle;
import com.example.chett.coach.outils.AccesHTTP;
import com.example.chett.coach.outils.AsyncResponse;
import com.example.chett.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chett on 25/12/2016.
 */

public class AccesDistant implements AsyncResponse {
    final String SERVERADDR="http://192.168.0.21/coach/serveurcoach.php";
   private Controle controle ;


public AccesDistant(){
    //super();
  this.controle=Controle.getInstance(null);

}

    /**
     * permettre de gérer le retour du serveur
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output);
        String[] message = output.split("%");
        if(message.length>1)
        {
            if(message[0].equals("enreg")){
                Log.d("*************","Enreg" + message);
            }
            if(message[0].equals("supp")){
                Log.d("*************","Supp" + message);
            }
        if(message[0].equals("tous")){
            try{
                //JSONObject info= new JSONObject(message[1]);
                JSONArray tab= new JSONArray(message[1]);
                ArrayList listProf=new ArrayList<Profil>();
                for(int i=0;i<tab.length();i++ ){
                JSONObject info=(JSONObject)(tab.get(i));
                Integer poids=info.getInt("poids");
                Integer taille=info.getInt("taille");
                Integer age=info.getInt("age");
                Integer sexe=info.getInt("sexe");
                Date date= MesOutils.convertStringToDate(info.getString("datemesure"));
                listProf.add(new Profil(poids,taille,age,sexe,date));
                }
                controle.setLesProfils(listProf);


                //controle.setProfil(new Profil(poids,taille,age,sexe,date));

            }


            catch (JSONException e) {
                e.printStackTrace();


            }

             }
        }
    }

    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees= new AccesHTTP();
        accesDonnees.delegate = this; // le lien de délégation entre AccesDistant et AccesHTTP
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }
}
