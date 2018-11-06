package com.example.valdemar.myevents.caja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.valdemar.myevents.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context CONTEXT;
    private ArrayList<even> LIST;
    public MyAdapter(Context contex, ArrayList<even>list){
        this.CONTEXT = contex;
        this.LIST = list;
    }
    @Override
    public int getCount() {
        return this.LIST.size();
    }

    @Override
    public Object getItem(int position) {
        return this.LIST.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflate =(LayoutInflater) this.CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.us_item_evento,null);
        }
        TextView nombre = (TextView)convertView.findViewById(R.id.Vnombre);
        TextView descri = (TextView)convertView.findViewById(R.id.Descrip);
        TextView fechaI = (TextView)convertView.findViewById(R.id.VfechaI);
        TextView fechaF = (TextView)convertView.findViewById(R.id.VfechaF);
        TextView horaI = (TextView)convertView.findViewById(R.id.VhoraI);
        TextView horaF = (TextView)convertView.findViewById(R.id.VhoraF);
        TextView costo = (TextView)convertView.findViewById(R.id.VCostos);

        nombre.setText(this.LIST.get(position).getNomb());
        descri.setText(this.LIST.get(position).getDescri());
        fechaI.setText(this.LIST.get(position).getFechI());
        fechaF.setText(this.LIST.get(position).getFechF());
        horaI.setText(this.LIST.get(position).getHorI());
        horaF.setText(this.LIST.get(position).getHorF());
        costo.setText(this.LIST.get(position).getCost());

        return convertView;
    }
}
