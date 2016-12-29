package com.example.chett.coach.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chett.coach.R;
import com.example.chett.coach.controleur.Controle;
import com.example.chett.coach.modele.Profil;
import com.example.chett.coach.outils.MesOutils;

import java.util.ArrayList;


/**
 * Created by chett on 28/12/2016.
 */

public class HistoListAdapter extends BaseAdapter {
   private static ArrayList<Profil>lesProfils=new ArrayList<Profil>();
   private LayoutInflater inflater;
    private Context context;

    /**
     *  permettre de retourner le nombre de lignes de la liste (getCount
     * @return
     */

    public HistoListAdapter(Context context,ArrayList<Profil>objProfil){

        this.lesProfils=objProfil;
        inflater=LayoutInflater.from(context);
        this.context=context;
    }


    private class  ViewHolder {
        TextView txtListDate;
        TextView txtListImg;
        ImageButton imgListSuppr;
    }
    @Override
    public int getCount() {
        return lesProfils.size();
        //return 0;
    }

    /**
     * l'item actuellement sélectionné (getItem
     * @param position
     * @return
     */

    /**
     * numéro de l'item actuellement sélectionné (getIdItem)
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {

        return lesProfils.get(position);
        //return null;
    }

    /**
     * le numéro de l'item actuellement sélectionné (getIdItem)
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
       // return 0;
    }

    /**
     * construction de la vue complète d'une ligne contenant aussi les événements sur les objets de cette vue
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder est un objet de la petite classe
        ViewHolder holder ;
        // si la ligne n'existe pas encore
        if (convertView == null) {         holder = new ViewHolder() ;
        // la ligne est construite à partir de la structure de la ligne (récupéré dans layout_list_histo)
            convertView = inflater.inflate(R.layout.layout_liste_histo, null) ;
        // chaque propriété de holder (correspondant aux objets graphiques) est liée à un des objets graphiques
            holder.txtListDate = (TextView)convertView.findViewById(R.id.txtListDate) ;
            holder.txtListImg = (TextView)convertView.findViewById(R.id.txtListImg) ;
            holder.imgListSuppr = (ImageButton)convertView.findViewById(R.id.imgListSuppr) ;
            holder.imgListSuppr.setImageResource(R.drawable.suppr);
            // affecte un tag (un indice) à la ligne (qui permettra de la repérer facilement)
            convertView.setTag(holder) ;
        }else{
            // si la ligne existe déjà, holder récupère le contenu (à partir de son tag, donc son indice)
            holder = (ViewHolder)convertView.getTag();
        }
        // holder est maintenant lié à la ligne graphique
        // valorisation des propriétés de holder avec le profil de lesProfils (à un indice précis : position)
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(position).getDateMesure())) ;
        holder.txtListImg.setText(MesOutils.format2Deciman(lesProfils.get(position).getImg()
        )) ;
        holder.imgListSuppr.setTag(position) ;
        // gestion de l'événement clic sur le bouton de suppression
        holder.imgListSuppr.setOnClickListener(new View.OnClickListener(){
            @Override         public void onClick(View v) {
                // code a exécuter sur le clic d'un bouton suppr
                // récupère l'indice de la ligne concernée
                int position = (Integer)v.getTag() ;
                // averti le controleur qu'il faut enlever un profil
                Controle controle = Controle.getInstance(context);
                controle.delProfil(lesProfils.get(position));
                // rafraichi la liste visuelle
                notifyDataSetChanged() ;

            }
        }) ;
        return convertView ;
    }    //return null;

}





