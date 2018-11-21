package com.example.valdemar.myevents;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetallEvento extends AppCompatActivity {
    private TextView nom,fechai,fechaf,horai,horaf,desc,cost, Fechas,lf,lho,lh,ldes,lcos,funi;
    private String Tnom,Tape,Tced,Tpro,Tins,Tcar;
    private String Enom,Efei,Efef,Ehoi,Ehof,Edesc,Ecost;
    private Typeface Letra;
    private String fuente;
    private Button REG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_evento);
        REG =(Button)findViewById(R.id.btn_reg);
        nom=(TextView)findViewById(R.id.NOM);
        fechai=(TextView)findViewById(R.id.FEI);
        fechaf=(TextView)findViewById(R.id.FEF);
        horai=(TextView)findViewById(R.id.HOI);
        horaf=(TextView)findViewById(R.id.HOF);
        desc=(TextView)findViewById(R.id.DES);
        cost=(TextView)findViewById(R.id.COS);
        Fechas=(TextView)findViewById(R.id.Lfecha);
        lf=(TextView)findViewById(R.id.Lf);
        lh=(TextView)findViewById(R.id.Lh);
        ldes=(TextView)findViewById(R.id.Ldes);
        lcos=(TextView)findViewById(R.id.LCos);
        lho=(TextView)findViewById(R.id.Lho);
        funi=(TextView)findViewById(R.id.FUni);

        EvenDatos();
        cambio();
    }

    private void EvenDatos() {
        Intent detalle = getIntent();
        Bundle B = getIntent().getExtras();
        if(B != null){
            if(B.getString("fei").toString().compareTo(B.getString("fef").toString())==0){
                //Toast.makeText(this, "son iguales", Toast.LENGTH_SHORT).show();
                nom.setText(B.getString("nom"));
                funi.setText(B.getString("fei"));
                horai.setText(B.getString("hoi"));
                horaf.setText(B.getString("hof"));
                desc.setText(B.getString("des"));
                cost.setText(B.getString("cos"));
            }else{
                //Toast.makeText(this, "no son iguales", Toast.LENGTH_SHORT).show();
                fechai.setText(B.getString("fei"));
                lf.setText("a");
                fechaf.setText(B.getString("fef"));
                nom.setText(B.getString("nom"));
                horai.setText(B.getString("hoi"));
                horaf.setText(B.getString("hof"));
                desc.setText(B.getString("des"));
                cost.setText(B.getString("cos"));
            }
            Enom = B.getString("nom");
            Efei = B.getString("fei");
            Efef = B.getString("hof");
            Ehoi = B.getString("hoi");
            Ehof = B.getString("hof");
            Edesc = B.getString("des");
            Ecost = B.getString("cos");

            Tnom = B.getString("Tnom");
            Tape = B.getString("Tape");
            Tced = B.getString("Tced");
            Tpro = B.getString("Tpro");
            Tins = B.getString("Tins");
            Tcar = B.getString("Tcar");
        }
    }

    private void cambio() {
        fuente ="fuentes/PhItalic.ttf";
        this.Letra = Typeface.createFromAsset(getAssets(),fuente);
        nom.setTypeface(Letra);
        fechai.setTypeface(Letra);
        fechaf.setTypeface(Letra);
        horai.setTypeface(Letra);
        horaf.setTypeface(Letra);
        desc.setTypeface(Letra);
        cost.setTypeface(Letra);
        Fechas.setTypeface(Letra);
        lf.setTypeface(Letra);
        lh.setTypeface(Letra);
        ldes.setTypeface(Letra);
        lcos.setTypeface(Letra);
        lho.setTypeface(Letra);
        funi.setTypeface(Letra);
    }

    public void Registros(View view){
        Toast.makeText(this, Tnom+" "+Tape+"\n"+Tced+"\n"+Tpro+"\n"+Tins+"\n"+Tcar+"Se Esta Registrando en: "+Enom, Toast.LENGTH_LONG).show();

    }
}