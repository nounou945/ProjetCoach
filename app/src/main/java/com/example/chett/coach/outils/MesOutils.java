package com.example.chett.coach.outils;

import android.util.Log;

import com.example.chett.coach.modele.AccesLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chett on 22/12/2016.
 */

public abstract class MesOutils  {

    public  static Date convertStringToDate(String unedate){
    Log.d("avant lappli de la meth","************"+unedate);
    //String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT' yyyy";
    String expectedPattern = "dd/MM/yy HH:mm:ss";
    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
    try {
        Date date = formatter.parse(unedate);
       // Log.d("obj date =  ",date);
        //Log.d("obj date",convertDateToString())
        Log.d("apres lappli de la meth","************"+unedate);
        return date;
    } catch (ParseException e){
         e.printStackTrace();
    }
    return null;
    }

    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return date.format(uneDate);
    }

}


