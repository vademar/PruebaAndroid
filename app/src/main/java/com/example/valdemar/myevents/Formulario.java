package com.example.valdemar.myevents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.valdemar.myevents.servidor.host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Formulario extends AppCompatActivity implements View.OnClickListener{

    private EditText Nom,Ap,Ci,Pss, insti, cargos, contra;
    private Spinner spiner;
    private Context root;
    private ArrayList<String> profeNames;
    private String nombre,apellido,cedula,contraseña,institucion,cargo;
    Button Crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Nom =(EditText)findViewById(R.id.nom);
        Ap =(EditText)findViewById(R.id.ape);
        Ci =(EditText)findViewById(R.id.ci);
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
        profes.get(host.Rest_Profesiones, new JsonHttpResponseHandler(){
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
        nombre= Nom.getText().toString();
        apellido=Ap.getText().toString();
        cedula= Ci.getText().toString();
        contraseña= contra.getText().toString();
        institucion = insti.getText().toString();
        cargo =cargos.getText().toString();

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
            if(nombre.matches("[aA-zZñÑ áéíóú]*") && apellido.matches("[aA-zZñÑ áéíóú]*") && institucion.matches("[aA-zZñÑ áéíóú]*")){
                if(cedula.matches("[A-Z0-9-]{7,11}")){
                    misdatos();
                }else
                    Toast.makeText(root, "el Ci no puede contener letras Min y Carateres", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(this,"Nombres, Apellidos e Institución No Pueden Tener Numeros", Toast.LENGTH_SHORT).show();
        }
    }
    public void misdatos(){
        AsyncHttpClient Usuario = new AsyncHttpClient();
        RequestParams param = new RequestParams();
        param.put("nombre", nombre);
        param.put("apellido", apellido);
        param.put("profesion", spiner.getSelectedItem().toString());
        param.put("institucion", institucion);
        param.put("cargo", cargo);
        param.put("ci", cedula);
        param.put("password", contraseña);

        Usuario.post(host.Rest_Registro, param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Intent inte = new Intent(root, AcUsuario.class);
                inte.putExtra("nom", nombre);
                inte.putExtra("ape", apellido);
                inte.putExtra("ced", cedula);
                inte.putExtra("ins", institucion);
                inte.putExtra("car", cargo);
                inte.putExtra("pro", spiner.getSelectedItem().toString());
                finish();
                root.startActivity(inte);
                Toast.makeText(root, "Welcome: " + nombre, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    String msn = errorResponse.getString("msn");
                    Toast.makeText(root, "lo Sentimos! " + msn, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
