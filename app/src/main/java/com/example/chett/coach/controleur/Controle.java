package com.example.chett.coach.controleur;

import android.content.Context;

import com.example.chett.coach.modele.Profil;
import com.example.chett.coach.outils.Serializer;

import java.util.Date;

/**
 * Created by chett on 30/11/2016.
 */

public final class Controle {

private static Controle instance = null;
private static Profil profil ;
private static String nomFic ="saveprofil"; // nom du fichier binaire qui va mémoriser la sérialisation du profil.

    private Controle(){
        super(); //on appelle la classe mere Object
    }

    public final static Controle getInstance(Context context){

        if(Controle.instance==null){
            Controle.instance=new Controle();
            recupSerialize(context);

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
        this.profil=new Profil(poids, taille, age, sexe,new Date());
        //Serializer.serialize(nomFic, profil,context);
    }

    /**
     *
     * @return
     */
    public float getImg(){
        if(profil!=null){
        return profil.getImg() ; // on recup l'img du profil instancié
        }
        return 0;
    }


    /**
     *
     * @return
     */
    public String getMessage(){
        if(profil!=null){
        return profil.getMessage(); // on recup le msg du profil instancié
        }
        return null ;
    }


    /**
     * deserialise le profil pour recup le profil
     * @param context
     */
    private static void  recupSerialize(Context context){
        profil=(Profil)Serializer.deSerialize(nomFic,context);

    }

    /**
     * retourne la taille du profil
     * @return
     */
    public int getTaille(){
        if (profil!=null){

        return(profil.getTaille());
    }
    return 0;
    }

    /*
    recup le poids du profil
     */
    public int getPoids(){
        if (profil!=null){

        return(profil.getPoids());
    }
    return 0;
    }

    /**
     * retourne le sexe selon un profil
     * @return
     */
    public int getSexe(){
        if (profil!=null){

        return(profil.getSexe());
    }
        return 0;
    }

    /**
     * retourne l'age du profil
     * @return
     */
    public int getAge(){
        if (profil!=null){

        return(profil.getAge());
    }
    return 0;
    }

    /**
     * retourne la date
     * @return
     */

}
