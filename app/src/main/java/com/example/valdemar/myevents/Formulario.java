package com.example.valdemar.myevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {

    private EditText Nom,Ap,Ci,Pss, insti;
    private Spinner spiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Nom =(EditText)findViewById(R.id.nom);
        Ap =(EditText)findViewById(R.id.ape);
        Ci =(EditText)findViewById(R.id.ci);
        Pss =(EditText)findViewById(R.id.pss);
        spiner =(Spinner)findViewById(R.id.spin);
        insti =(EditText)findViewById(R.id.Ins_Uni);

        String [] profe = {"Medico","Estudiante","Auxiliar","Ingeniero"};

        ArrayAdapter <String> Adaptar =new ArrayAdapter<String>(this,R.layout.spinner_item_formulario,profe);
        spiner.setAdapter(Adaptar);
    }

    //codigo pra el boton crear usuario
    public void CreaCuent (View view){

        String nombre= Nom.getText().toString();
        String apellido=Ap.getText().toString();
        String cedula= Ci.getText().toString();
        String contraseña= Pss.getText().toString();
        String institucion = insti.getText().toString();

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
                Intent inte = new Intent(this,AcUsuario.class);
                startActivity(inte);

                finish();
            }else
                Toast.makeText(this,"Solo Se Admiten Letras En Este Campo", Toast.LENGTH_SHORT).show();
        }
    }

}
