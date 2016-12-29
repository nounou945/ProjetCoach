package com.example.chett.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.chett.coach.modele.AccesDistant;
import com.example.chett.coach.modele.AccesLocal;
import com.example.chett.coach.modele.Profil;
import com.example.chett.coach.outils.Serializer;
import com.example.chett.coach.vue.CalculActivity;
import com.example.chett.coach.vue.HistoActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chett on 30/11/2016.
 */

public final class Controle {

private static Controle instance = null;
//private static Profil profil ;
private static String nomFic ="saveprofil"; // nom du fichier binaire qui va mémoriser la sérialisation du profil.
private static AccesLocal accesLocal;
private static AccesDistant accesDistant;
private static Context context;
private static HistoActivity histoActivity;
private ArrayList<Profil>lesProfils=new ArrayList<Profil>();

    private Controle(){
        super(); //on appelle la classe mere Object
    }

    public final static Controle getInstance(Context context){

        if(Controle.instance==null){
            Controle.context = context;
            Controle.instance=new Controle();
            //accesLocal=new AccesLocal(context);
            accesDistant = new AccesDistant();
            //accesDistant.envoi("dernier", new JSONArray());
            accesDistant.envoi("tous", new JSONArray());
            //profil=accesLocal.recupDernier();// afin de valoriser l'objet profil (au cas où il y aurait un profil à récupérer

          // recupSerialize(context);

        }
        return Controle.instance;
    }



    /**
     * Création du profil
     * @param poids
     * @param taille
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(int poids,int taille,int age, int sexe,Context context){
        Profil profil;
        profil=new Profil(poids, taille, age, sexe,new Date());//avant this.profil car prop dans la classe
        //Serializer.serialize(nomFic, profil,context);
        //accesLocal.ajoutProfil(profil); //d'ajouter le nouveau profil dans la base locale
        lesProfils.add(profil);
        accesDistant.envoi("enreg", profil.convertToJSONArray());
    }

    /**
     *
     * @return
     */
    public float getImg(){
        if(lesProfils!=null){
        return (lesProfils.get(lesProfils.size()-1).getImg()); // on recup l'img du profil instancié
        }
        return 0;
    }


    /**
     *
     * @return
     */
    public String getMessage(){
        if(this.lesProfils!=null){
        return lesProfils.get(lesProfils.size()-1).getMessage();
        }
        return null ;
    }


    /**
     * deserialise le profil pour recup le profil
     * @param context
     */
    private static void  recupSerialize(Context context){
       // profil=(Profil)Serializer.deSerialize(nomFic,context);

    }

    /**
     * retourne la taille du profil
     * @return
     */
    /*public int getTaille(){
        if (profil!=null){

        return(profil.getTaille());
    }
    return 0;
    }*/

    /*
    recup le poids du profil
     */
   /* public int getPoids(){
        if (profil!=null){

        return(profil.getPoids());
    }
    return 0;
    }*/

    /**
     * retourne le sexe selon un profil
     * @return
     */
  /*  public int getSexe(){
        if (profil!=null){

        return(profil.getSexe());
    }
        return 0;
    }*/

    /**
     * retourne l'age du profil
     * @return
     */
   /* public int getAge(){
        if (profil!=null){

        return(profil.getAge());
    }
    return 0;
    }*/

    /**
     * retourne la date
     * @return
     */

/*public void setProfil(Profil profil){
    this.profil=profil;
    ((CalculActivity)context).recupProfil();

}*/

    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    public void setLesProfils(ArrayList<Profil>lesProfils){
        this.lesProfils=lesProfils;
        if(histoActivity!=null){
        histoActivity.crerListe();
        Log.d("*****************",""+histoActivity);
        }
    }

    public void delProfil(Profil objet){
    accesDistant.envoi("supp",objet.convertToJSONArray());

        lesProfils.remove(lesProfils.indexOf(objet));
    }
    public void setHistoActivity(HistoActivity histoActivity) {
        Controle.histoActivity = histoActivity;

    }
}
