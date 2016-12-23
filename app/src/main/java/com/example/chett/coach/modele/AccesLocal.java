package com.example.chett.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.chett.coach.outils.MesOutils;
import com.example.chett.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

import static com.example.chett.coach.outils.MesOutils.*;

/**
 * Created by chett on 07/12/2016.
 */

public class AccesLocal  {

private String nomBase = "bdCoach.sqlite";
private int versionBase=1;
private MySQLiteOpenHelper accesBD ; // permet d'acceder à la base
private SQLiteDatabase bd ;




    public AccesLocal(Context context){
        accesBD=new MySQLiteOpenHelper (context,nomBase,versionBase);

    }

    /**
     * permet d'ajouter un profil à la table
     * @param profil
     */
    public void ajoutProfil(Profil profil){
        Log.d("date","********"+profil.getDateMesure());
        bd=accesBD.getWritableDatabase();// accès en écriture sur la base


       // Log.d("convert date ",MesOutils.convertDateToString(profil.getDateMesure()));
        String req =  "insert into profil values (\"" + MesOutils.convertDateToString(profil.getDateMesure()) + "\"," + profil.getPoids() + "," + profil.getTaille() + "," + profil.getAge() + "," + profil.getSexe() + ")";
        bd.execSQL(req); //execute la requete
    }

    /**
     * permet de récupérer le dernier profil ajouté
     * @return
     */
    public Profil  recupDernier(){ //j'avais mis profil de type profil il faut aucun par
       Profil profil=null; // j'ai rajouter devant le Type Profil pour quil soit creer
        bd=accesBD.getReadableDatabase(); //accès en lecture sur la base
        String req=" SELECT * FROM PROFIL";// ORDER BY DateMesure DESC";
        Cursor curseur=bd.rawQuery(req,null) ;// pour accéder au résultat de la requête
        //curseur.moveToFirst();//se positionne sur la premiere ligne du curseur
        if(curseur.moveToLast()){
            Log.d("date av conv","*******"+curseur.getString(0));
            Date datemesure = MesOutils.convertStringToDate(curseur.getString(0));

            int poids=curseur.getInt(1);
            int taille=curseur.getInt(2);
            int age=curseur.getInt(3);
            int sexe=curseur.getInt(4);
            //Date dateMesure= new Date();
            //Date datemesure = MesOutils.convertStringToDate(curseur.getString(0));

            //Log.d("date=", "******"+curseur.getString(0));

            profil=new Profil(poids,taille,age,sexe,datemesure);
            Log.d("dernierformatdaterecup","*******"+datemesure);
        }
        curseur.close();//on ferme le curseur

        return profil;
    }


}
