package com.example.valdemar.myevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText Ci,Pss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Ci =(EditText)findViewById(R.id.L_ci);
        Pss =(EditText)findViewById(R.id.L_pss);
    }

    public void InicioSesion(View view){

        String cedula= Ci.getText().toString();
        String contraseña= Pss.getText().toString();

        String ci="123456";
        String pss="samian";

        if (cedula.length()== 0) {
            Toast.makeText(this,"Ingrese Su CI", Toast.LENGTH_SHORT).show();
        }
        if (contraseña.length()== 0) {
            Toast.makeText(this,"Ingrese Su Contraseña", Toast.LENGTH_SHORT).show();
        }
        if( contraseña.compareTo(pss) == 0){
            if (cedula.compareTo(ci)==0){
                Intent usuarios = new Intent(this,AcUsuario.class);
                startActivity(usuarios);
                finish();
            }else
                Toast.makeText(this, "Cedula De Identidad Incorrecta", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        }
    }


    public void AlFormulario(View view){
        Intent formulario = new Intent(this,Formulario.class);
        startActivity(formulario);
        finish();
    }
}
