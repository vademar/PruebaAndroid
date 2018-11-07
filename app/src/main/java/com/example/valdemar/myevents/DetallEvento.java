package com.example.valdemar.myevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetallEvento extends AppCompatActivity {
    private TextView nom,fechai,fechaf,horai,horaf,desc,cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_evento);
        nom=(TextView)findViewById(R.id.NOM);
        fechai=(TextView)findViewById(R.id.textView4);
        fechaf=(TextView)findViewById(R.id.textView6);
        horai=(TextView)findViewById(R.id.textView7);
        horaf=(TextView)findViewById(R.id.textView8);
        desc=(TextView)findViewById(R.id.textView9);
        cost=(TextView)findViewById(R.id.textView10);

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
