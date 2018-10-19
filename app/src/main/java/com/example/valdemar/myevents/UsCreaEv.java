package com.example.valdemar.myevents;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class UsCreaEv extends Fragment {

    TextView Fecha;
    TextView Hora;
    Button BtnFech;
    Button BtnHor;

    private int year,mes,dia, hora, min;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_us_crea_ev, container, false);

        Fecha = (TextView)V.findViewById(R.id.Fecha);
        Hora = (TextView)V.findViewById(R.id.Hora);
        BtnFech = (Button)V.findViewById(R.id.BtnFech);
        BtnHor = (Button)V.findViewById(R.id.BtnHor);
        BtnFech.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                year = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH);
                dia = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog Ponerfecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Fecha.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year,mes,dia);
                Ponerfecha.show();
            }
        });

        BtnHor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                hora = c.get(Calendar.HOUR);
                min = c.get(Calendar.MINUTE);
                TimePickerDialog ponerhora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Hora.setText(hourOfDay+" : "+minute);
                    }
                },hora,min,false);
                ponerhora.show();
            }
        });
        return V;
    }
}
