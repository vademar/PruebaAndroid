package com.example.valdemar.myevents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.valdemar.myevents.servidor.host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Formulario extends AppCompatActivity implements View.OnClickListener{

    private EditText Nom,Ap,Ci,Pss, insti, cargos, contra;
    private Spinner spiner;
    private Context root;
    private ArrayList<String> profeNames;

    Button Crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Nom =(EditText)findViewById(R.id.nom);
        Ap =(EditText)findViewById(R.id.ape);
        Ci =(EditText)findViewById(R.id.ci);
        Pss =(EditText)findViewById(R.id.pss);
        insti =(EditText)findViewById(R.id.Ins_Uni);
        cargos = (EditText)findViewById(R.id.Cargo) ;
        contra = (EditText)findViewById(R.id.pss);
        Crear = (Button)findViewById(R.id.crear);
        profeNames = new ArrayList<String>();

        spiner =(Spinner)findViewById(R.id.spin);
        Crear.setOnClickListener(this);
        getAllProfesiones();
    }

    private void getAllProfesiones() {

        final AsyncHttpClient profes = new AsyncHttpClient();
        profes.get(host.Rest_Profesiones_Get, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Profesion");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        profeNames.add(obj.getString("profesiones"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                spiner.setAdapter(new ArrayAdapter<String>(root, android.R.layout.simple_spinner_dropdown_item, profeNames));
            }
        });
    }
    //codigo pra el boton crear usuario
    public void onClick(View v) {
        String nombre= Nom.getText().toString();
        String apellido=Ap.getText().toString();
        String cedula= Ci.getText().toString();
        String contraseña= Pss.getText().toString();
        String institucion = insti.getText().toString();
        String cargo =cargos.getText().toString();
        String pass = contra.getText().toString();

        if (nombre.length()== 0) {
            Toast.makeText(this,"Ingrese Nombres", Toast.LENGTH_SHORT).show();
        }
        if (apellido.length()== 0) {
            Toast.makeText(this,"Ingrese Apellidos", Toast.LENGTH_SHORT).show();
        }
        if (cedula.length()== 0) {
            Toast.makeText(this,"Su Cedula Es Necesaria", Toast.LENGTH_SHORT).show();
        }
        if (contraseña.length()== 0) {
            Toast.makeText(this,"La Contraseña Es Necesaria", Toast.LENGTH_SHORT).show();
        }
        if (institucion.length()== 0) {
            Toast.makeText(this,"Ingrese Institución o Universidad", Toast.LENGTH_SHORT).show();
        }

        if(nombre.length()!=0 && apellido.length()!=0 && cedula.length()!=0 && contraseña.length()!=0 && institucion.length()!=0){
            if(nombre.matches("[aA-zZ,ñÑ, ]*") && apellido.matches("[aA-zZ,ñÑ, ]*")){

                AsyncHttpClient Usuario = new AsyncHttpClient();
                RequestParams param = new RequestParams();
                param.put("nombre",nombre);
                param.put("apellido",apellido);
                param.put("profesion", spiner.getSelectedItem().toString());
                param.put("institucion", institucion);
                param.put("cargo",cargo);
                param.put("ci",cedula);
                param.put("password",pass);

                Usuario.post(host.Rest_User_Post, param, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        //temporal hasta q haga una mejor decicion//
                        Intent inte = new Intent(root,AcUsuario.class);
                        root.startActivity(inte);
                    }
                });
                finish();
            }else
                Toast.makeText(this,"Solo Se Admiten Letras En Este Campo", Toast.LENGTH_SHORT).show();
        }
    }
}
