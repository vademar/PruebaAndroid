package com.example.valdemar.myevents;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetallEvento extends AppCompatActivity {
    private TextView nom,fechai,fechaf,horai,horaf,desc,cost;
    private Typeface Letra;
    private String fuente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fuente ="fuentes/Charline.otf";
        setContentView(R.layout.activity_detall_evento);
        nom=(TextView)findViewById(R.id.NOM);
        fechai=(TextView)findViewById(R.id.FEI);
        fechaf=(TextView)findViewById(R.id.FEF);
        horai=(TextView)findViewById(R.id.HOI);
        horaf=(TextView)findViewById(R.id.HOF);
        desc=(TextView)findViewById(R.id.DES);
        cost=(TextView)findViewById(R.id.COS);

        this.Letra = Typeface.createFromAsset(getAssets(),fuente);
        nom.setTypeface(Letra);
        fechai.setTypeface(Letra);
        fechaf.setTypeface(Letra);
        horai.setTypeface(Letra);
        horaf.setTypeface(Letra);
        desc.setTypeface(Letra);
        cost.setTypeface(Letra);

        Intent detalle = getIntent();
        Bundle B = getIntent().getExtras();
        if(B != null){
            nom.setText(B.getString("nom"));
            fechai.setText(B.getString("fei"));
            fechaf.setText(B.getString("fef"));
            horai.setText(B.getString("hoi"));
            horaf.setText(B.getString("hof"));
            desc.setText(B.getString("des"));
            cost.setText(B.getString("cos"));
        }
    }
}
