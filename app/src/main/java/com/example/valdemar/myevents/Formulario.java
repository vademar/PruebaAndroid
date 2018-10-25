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

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;

public class Formulario extends AppCompatActivity implements View.OnClickListener{

    private EditText Nom,Ap,Ci,Pss, insti, cargos, contra;
    private Spinner spiner;
    private Context root;
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
        spiner =(Spinner)findViewById(R.id.spin);
        insti =(EditText)findViewById(R.id.Ins_Uni);
        cargos = (EditText)findViewById(R.id.Cargo) ;
        contra = (EditText)findViewById(R.id.pss);
        Crear = (Button)findViewById(R.id.crear);

        String [] profe = {"Medico","Estudiante","Auxiliar","Ingeniero"};

        ArrayAdapter <String> Adaptar =new ArrayAdapter<String>(this,R.layout.spinner_item_formulario,profe);
        spiner.setAdapter(Adaptar);
        Crear.setOnClickListener(this);
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
        String selec =spiner.getSelectedItem().toString();

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
        if(selec.length()!=0){
            Toast.makeText(this,"Bien venido: "+selec, Toast.LENGTH_SHORT).show();
        }

        if(nombre.length()!=0 && apellido.length()!=0 && cedula.length()!=0 && contraseña.length()!=0 && institucion.length()!=0){
            if(nombre.matches("[aA-zZ,ñÑ, ]*") && apellido.matches("[aA-zZ,ñÑ, ]*")){

                AsyncHttpClient Usuario = new AsyncHttpClient();
                RequestParams param = new RequestParams();
                param.put("nombre",nombre);
                param.put("apellido",apellido);
                param.put("institucion", institucion);
                param.put("cargo",cargo);
                param.put("ci",cedula);
                param.put("password",pass);

                Usuario.post("http://192.168.1.102:4040/registro/", param, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
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
