package com.example.valdemar.myevents;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.valdemar.myevents.caja.MyAdapter;
import com.example.valdemar.myevents.caja.even;
import com.example.valdemar.myevents.servidor.host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


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
        Elrelleno();
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
                    LIST =(ListView)V.findViewById(R.id.listviewlayout);
                    LIST.setAdapter(ADAPTER);
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
