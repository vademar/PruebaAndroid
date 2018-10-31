package com.example.valdemar.myevents;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.valdemar.myevents.caja.profe;
import com.example.valdemar.myevents.servidor.host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;


public class UsCreaEv extends Fragment implements View.OnClickListener {

    TextView Fecha,Hora,FechaF,HoraF, Invi;
    EditText Nombre, Descripcion;
    Button BtnFech, BtnHor, BtnFechF,BtnHorF,CreaEvento;

    private int year,mes,dia, hora, min;
    private ArrayList<profe> Profess;
    private Spinner spiner;
    private String nomb, fechaI,horaI,fechaF,horaF, Desc,Pro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_us_crea_ev, container, false);

        Fecha = (TextView)V.findViewById(R.id.Fecha);
        Hora = (TextView)V.findViewById(R.id.Hora);
        BtnFech = (Button)V.findViewById(R.id.BtnFech);
        BtnHor = (Button)V.findViewById(R.id.BtnHor);

        FechaF = (TextView)V.findViewById(R.id.fecha2);
        HoraF = (TextView)V.findViewById(R.id.hora2);
        BtnFechF = (Button)V.findViewById(R.id.btnFech2);
        BtnHorF = (Button)V.findViewById(R.id.btnHor2);
        Profess= new ArrayList<profe>();
        spiner =(Spinner)V.findViewById(R.id.Spinprofesion);

        Nombre=(EditText)V.findViewById(R.id.NomEv);
        Descripcion= (EditText)V.findViewById(R.id.Descrip);
        Invi=(TextView)V.findViewById(R.id.txtprofe);

        CreaEvento = (Button) V.findViewById(R.id.CrearEvento);

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
                        fechaI=Fecha.getText().toString();
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
                        horaI=Hora.getText().toString();
                    }
                },hora,min,false);
                ponerhora.show();
            }
        });

        BtnFechF.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                year = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH);
                dia = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog Ponfecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        FechaF.setText(dayOfMonth+"/"+month+"/"+year);
                        fechaF=FechaF.getText().toString();
                    }
                },year,mes,dia);
                Ponfecha.show();
            }
        });

        BtnHorF.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                hora = c.get(Calendar.HOUR);
                min = c.get(Calendar.MINUTE);
                TimePickerDialog ponhora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        HoraF.setText(hourOfDay+" : "+minute);
                        horaF=HoraF.getText().toString();
                    }
                },hora,min,false);
                ponhora.show();
            }
        });

        Lasprofesiones();
        LlenarTxt();
        CreaEvento.setOnClickListener(this);
        return V;
    }

    private void LlenarTxt() {
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String cadena="";
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                String item = parent.getItemAtPosition(i).toString();
                String X=parent.getItemAtPosition(0).toString();
                if(item.compareTo(X)==0&&cadena.length()==0){
                    item="";
                    cadena= cadena+item;
                }else{
                    cadena = cadena +item;
                    cadena = cadena +"\n";
                }
                Invi.setText(cadena);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Lasprofesiones() {
        final AsyncHttpClient profes = new AsyncHttpClient();
        profes.get(host.Rest_Profesiones, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Profesion");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        profe s = new profe();
                        s.setProfesion(obj.optString("profesiones"));
                        s.setPrecio(obj.optString("precio"));
                        Profess.add(s);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                spiner.setAdapter(new ArrayAdapter<profe>(getContext(),android.R.layout.simple_spinner_dropdown_item, Profess));
            }
        });
    }

    @Override
    public void onClick(View v) {
        nomb= Nombre.getText().toString();
        Desc = Descripcion.getText().toString();
        Pro = Invi.getText().toString();
        if (nomb.length()== 0) {
            Toast.makeText(getContext(),"Ingrese Nombre Para Evento", Toast.LENGTH_SHORT).show();
        }
        if (fechaI== null) {
            Toast.makeText(getContext(),"La Fecha Inicio es Necesario", Toast.LENGTH_SHORT).show();
        }
        if (horaI== null) {
            Toast.makeText(getContext(),"La Hora Inicio es Necesario", Toast.LENGTH_SHORT).show();
        }
        if (fechaF== null) {
            Toast.makeText(getContext(),"La Fecha Final es Necesario", Toast.LENGTH_SHORT).show();
        }
        if (horaF== null) {
            Toast.makeText(getContext(),"La Hora Final es Necesario", Toast.LENGTH_SHORT).show();
        }
        if(Desc.length()==0){
            Toast.makeText(getContext(),"Describa Su Evento", Toast.LENGTH_SHORT).show();
        }
        if(Pro.length()==0){
            Toast.makeText(getContext(),"Este Campo Puede Estar Vacio", Toast.LENGTH_SHORT).show();
        }

        if(nomb.length()!=0 && fechaI!=null && horaI!= null && fechaF!= null && horaF!= null && Desc.length()!=0){
            AsyncHttpClient Usuario = new AsyncHttpClient();
            RequestParams param = new RequestParams();
            param.put("nombre",nomb);
            param.put("fechaIni",fechaI);
            param.put("horaIni",horaI);
            param.put("fechaFin",fechaF);
            param.put("horaFin",horaF);
            param.put("decripcion",Desc);
            param.put("invitados",Pro);

            Usuario.post(host.Rest_Eventi, param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    //temporal hasta q haga una mejor//
                    Intent inte = new Intent(getContext(),AcUsuario.class);
                    getContext().startActivity(inte);
                }
            });
            Toast.makeText(getContext(),"El Evento Se Esta Creando", Toast.LENGTH_SHORT).show();
        }
    }
}
