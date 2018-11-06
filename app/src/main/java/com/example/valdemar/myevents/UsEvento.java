package com.example.valdemar.myevents;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.valdemar.myevents.caja.MyAdapter;
import com.example.valdemar.myevents.caja.even;

import java.util.ArrayList;


public class UsEvento extends Fragment {
    private ListView LIST;
    private View V;
    private ArrayList<even> LISTINFO;
    private MyAdapter ADAPTER;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        V = inflater.inflate(R.layout.fragment_us_evento, container, false);

        LISTINFO = new ArrayList<even>();
        componentes();
        return V;
    }

    private void componentes() {
        LIST =(ListView)V.findViewById(R.id.listviewlayout);
        LISTINFO.add(new even("CURSO DE GENETICA","25/15/18","14:00","25/15/08","16:00","la investigacion para el avance cientifico de la sociedad unida se presenta dictar los cursos qiue por derecho debemos de saber","profesionales 150, medico 100, Auxiliar 100"));
        ADAPTER= new MyAdapter(getContext(),LISTINFO);

        LIST.setAdapter(ADAPTER);
    }

}
