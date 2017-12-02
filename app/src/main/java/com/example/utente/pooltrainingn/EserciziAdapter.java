package com.example.utente.pooltrainingn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Utente on 27/11/2017.
 */



public class EserciziAdapter extends BaseAdapter {

    private List<Esercizi> esercizi= Collections.emptyList();
    private Context context;

    public EserciziAdapter(Context context) {
        this.context=context;
    }
    @Override
    public int getCount() {
        return esercizi.size();
    }

    @Override
    public Object getItem(int position) {
        return esercizi.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void update(List<Esercizi> newList) {

        esercizi = newList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.riga_esercizio, parent, false);

        TextView nomeEsercizio= (TextView)convertView.findViewById(R.id.textViewNomeEsercizio);
        TextView  numeroVasche= (TextView)convertView.findViewById(R.id.textViewNVascheAggiunte);

        Esercizi e = esercizi.get(position);
        nomeEsercizio.setText(e.getNomeEsercizio());
        numeroVasche.setText(String.valueOf(e.getNumeroVasche()));
        return convertView;
    }
}
