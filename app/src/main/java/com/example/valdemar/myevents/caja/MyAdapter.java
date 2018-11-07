package com.example.valdemar.myevents.caja;

import android.content.Context;
import android.icu.text.MessagePattern;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valdemar.myevents.R;
import com.example.valdemar.myevents.servidor.host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAdapter extends BaseAdapter {
    private Context CONTEXT;
    private ArrayList<even> LIST;
    private String[] User;
    private String Nomb;
    private Button regis;
    private int Posi;
    private ArrayList<String> profeNames;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Posi= position;
        if(convertView == null){
            LayoutInflater inflate =(LayoutInflater) this.CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.us_item_evento,null);
        }
        final TextView nombre = (TextView)convertView.findViewById(R.id.Vnombre);
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

        Nomb= nombre.getText().toString();
        regis = (Button)convertView.findViewById(R.id.party);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final AsyncHttpClient profes = new AsyncHttpClient();
                profes.get(host.Rest_Registro, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("mostrar");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject obj = jsonArray.getJSONObject(i);
                                profeNames.add(obj.getString("nombre"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });*/
                Toast.makeText(CONTEXT, "Registrado en evento: "+Nomb+" position : "+position, Toast.LENGTH_SHORT).show();
                regis.setEnabled(false);
            }
        });

        return convertView;
    }
}
