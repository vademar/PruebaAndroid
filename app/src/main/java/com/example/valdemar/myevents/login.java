package com.example.valdemar.myevents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.valdemar.myevents.caja.MyAdapter;
import com.example.valdemar.myevents.caja.even;
import com.example.valdemar.myevents.caja.profe;
import com.example.valdemar.myevents.servidor.host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {

    private EditText Ci,Pss;
    private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Ci =(EditText)findViewById(R.id.L_ci);
        Pss =(EditText)findViewById(R.id.L_pss);
    }
    public void logeeo(View view){
        String CI= Ci.getText().toString();
        String PSS= Pss.getText().toString();
        if(CI.length()==0){
            Toast.makeText(root, "Ingrese su Cedula de Identidad", Toast.LENGTH_SHORT).show();
        }else if(PSS.length()==0){
            Toast.makeText(root, "Ingrese su Contraseña", Toast.LENGTH_SHORT).show();
        }
        AsyncHttpClient logue = new AsyncHttpClient();
        logue.get(host.Rest_login+CI+"="+PSS, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject Login = response.getJSONObject("usuarios");
                    //String signupDate= Login.optString("signupDate");
                    //String id = Login.optString("id");
                    String nombre = Login.optString("nombre");
                    String apellido = Login.optString("apellido");
                    String ci = Login.optString("ci");
                    String profesion = Login.optString("profesion");
                    String institucion = Login.optString("institucion");
                    String cargo = Login.optString("cargo");
                    //String password = Login.optString("password");
                    Toast.makeText(getApplicationContext(),"Welcome: "+nombre, Toast.LENGTH_SHORT).show();
                    Intent Delogueo = new Intent(root, AcUsuario.class);
                    Delogueo.putExtra("nom",nombre);
                    Delogueo.putExtra("ape",apellido);
                    Delogueo.putExtra("ced",ci);
                    Delogueo.putExtra("pro",profesion);
                    Delogueo.putExtra("ins",institucion);
                    Delogueo.putExtra("car",cargo);
                    startActivity(Delogueo);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)  {
                Toast.makeText(root,"Verifique Sus Datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AlFormulario(View view){
        Intent formulario = new Intent(this,Formulario.class);
        startActivity(formulario);
        finish();
    }
}
