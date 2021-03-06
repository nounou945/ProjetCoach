package com.example.chett.coach.modele;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chett on 15/11/2016.
 */

public class Profil implements Serializable {

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    private int poids;
    private int taille;
    private int age;
    private int sexe;
    private float img;
    private String message;//rajout
    private Date dateMesure ;

    public Profil(int poids, int taille, int age, int sexe, Date dateMesure) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        calculIMG();
        resultIMG();
        this.dateMesure=dateMesure;
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    // valorise la propriété img en lui affectant le calcul
    private void calculIMG(){
        float taille = ((float)this.taille)/100;
        img = (float)(((1.2 * poids) / (taille * taille)) + (0.23 * age ) - (10.83 * sexe) - 5.4);
    }

    private void resultIMG(){
        if(sexe==0){ // femme
            if(img<Profil.minFemme){
                this.message="trop maigre";
            }
            else if(img<=Profil.maxFemme){
                this.message="normal";
            }
            else /*(img>Profil.maxFemme)*/{
                this.message="trop élevé"; //avant trop de graisse mnt trop eleve sinon test marche pas
            }
        }
        if(sexe==1){ // homme
            if(img<Profil.minHomme){
                this.message="trop maigre";
            }
            else if(img<=Profil.maxHomme){
                this.message="normal";
            }
            else /*(img>Profil.maxHomme)*/{
                this.message="trop de graisse";
            }
        }


    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public JSONArray  convertToJSONArray(){
        ArrayList List=new ArrayList();
        List.add(dateMesure);
        List.add(poids);
        List.add(taille);
        List.add(age);
        List.add(sexe);
        return(new JSONArray(List));
    }
}
