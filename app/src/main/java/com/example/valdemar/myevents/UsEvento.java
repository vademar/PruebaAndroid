package com.example.valdemar.myevents;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.valdemar.myevents.caja.MyAdapter;
import com.example.valdemar.myevents.caja.even;
import com.example.valdemar.myevents.servidor.host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import cz.msebera.android.httpclient.Header;


public class UsEvento extends Fragment implements AdapterView.OnItemClickListener{
    private ListView LIST;
    private View V;
    private ArrayList<even> LISTINFO;
    private MyAdapter ADAPTER;
    private String Tnom,Tape,Tced,Tpro,Tins,Tcar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        V = inflater.inflate(R.layout.fragment_us_evento, container, false);
        LISTINFO = new ArrayList<even>();
        LIST =(ListView)V.findViewById(R.id.listviewlayout);
        Elrelleno();
        DataUser();
        LIST.setOnItemClickListener(this);
        return V;
    }
    private void Elrelleno() {
        AsyncHttpClient profes = new AsyncHttpClient();
        profes.get(host.Rest_Eventi, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("event");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String  nom = obj.optString("nombre");
                        String fechi = obj.optString("fechaIni");
                        String hori = obj.optString("horaIni");
                        String fechf = obj.optString("fechaFin");
                        String horf = obj.optString("horaFin");
                        String des = obj.optString("descripcion");
                        String cos = obj.optString("invitados");
                        even evento = new even(nom,fechi,hori,fechf,horf,des,cos);
                        LISTINFO.add(evento);
                    }
                    ADAPTER= new MyAdapter(getContext(),LISTINFO);
                    Collections.reverse(LISTINFO);
                    LIST.setAdapter(ADAPTER);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void DataUser(){
        Intent inte = getActivity().getIntent();
        Intent Delogueo = getActivity().getIntent();
        Intent integ = getActivity().getIntent();
        Bundle A = getActivity().getIntent().getExtras();
        if (A != null){
            Tnom = A.getString("nom");
            Tape = A.getString("ape");
            Tced = A.getString("ced");
            Tcar = A.getString("car");
            Tins = A.getString("ins");
            Tpro = A.getString("pro");
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String NOM = this.LISTINFO.get(position).getNomb();
        String FECI = this.LISTINFO.get(position).getFechI();
        String FECF = this.LISTINFO.get(position).getFechF();
        String HORI= this.LISTINFO.get(position).getHorI();
        String HORF = this.LISTINFO.get(position).getHorF();
        String DES = this.LISTINFO.get(position).getDescri();
        String COS = this.LISTINFO.get(position).getCost();

        Intent detalle = new Intent(getContext(),DetallEvento.class);
        detalle.putExtra("nom",NOM);
        detalle.putExtra("fei",FECI);
        detalle.putExtra("fef",FECF);
        detalle.putExtra("hoi",HORI);
        detalle.putExtra("hof",HORF);
        detalle.putExtra("des",DES);
        detalle.putExtra("cos",COS);
        detalle.putExtra("Tnom",Tnom);
        detalle.putExtra("Tape",Tape);
        detalle.putExtra("Tced",Tced);
        detalle.putExtra("Tcar",Tcar);
        detalle.putExtra("Tins",Tins);
        detalle.putExtra("Tpro",Tpro);
        this.startActivity(detalle);
    }
}
