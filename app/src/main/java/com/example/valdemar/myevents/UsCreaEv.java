package com.example.valdemar.myevents;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

    private TextView Fecha,Hora,FechaF,HoraF, ProfeSelec;
    private EditText Nombre, Descripcion;
    private Button BtnFech, BtnHor, BtnFechF,BtnHorF,CreaEvento, mOrder;

    private int year,mes,dia, hora, min;
    private ArrayList<profe> Profess;
    private String[] ListProfes;
    private ArrayList<Integer> lasprofesiones = new ArrayList<>();
    private boolean[] CheckProfes;

    private final static String CHANNEL_ID ="NOTIFICACION";
    private static final int NOTIFICACION_id = 0;


    private String nomb, fechaI,horaI,fechaF,horaF, Desc,Pro;
    private String Tnom,Tape,Tced,Tpro,Tins,Tcar;
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
        mOrder = (Button)V.findViewById(R.id.mOrder);

        Nombre=(EditText)V.findViewById(R.id.NomEv);
        Descripcion= (EditText)V.findViewById(R.id.Descrip);
        ProfeSelec=(TextView)V.findViewById(R.id.txtprofe);

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
                        Fecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
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
                        FechaF.setText(dayOfMonth+"/"+(month+1)+"/"+year);
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

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setTitle("Elija Los Participantes");
                mBuilder.setMultiChoiceItems(ListProfes, CheckProfes, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!lasprofesiones.contains(position)){
                                lasprofesiones.add(position);
                            }
                        }else if(lasprofesiones.contains(position)){
                            lasprofesiones.remove((Integer) position);
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item="";
                        for(int i=0; i<lasprofesiones.size();i++){
                            item = item + ListProfes[lasprofesiones.get(i)];
                            if(i!=lasprofesiones.size()-1){
                                item =  item + "\n";
                            }
                        }
                        ProfeSelec.setText(item);
                    }
                });

                mBuilder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Borrar Todo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0; i<CheckProfes.length;i++){
                            CheckProfes[i]=false;
                            lasprofesiones.clear();
                            ProfeSelec.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        CreaEvento.setOnClickListener(this);
        return V;
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
                ListProfes = new String[Profess.size()];
                for (int i = 0; i < Profess.size(); i++) {
                    ListProfes [i] = String.valueOf(Profess.get(i));
                }
                CheckProfes = new boolean[ListProfes.length];
            }
        });
    }

    @Override
    public void onClick(View v) {
        nomb= Nombre.getText().toString();
        Desc = Descripcion.getText().toString();
        Pro = ProfeSelec.getText().toString();
        Novacios();

        if(nomb.length()!=0 && fechaI!=null && horaI!= null && fechaF!= null && horaF!= null && Desc.length()!=0){
            AsyncHttpClient Usuario = new AsyncHttpClient();
            RequestParams param = new RequestParams();
            param.put("nombre",nomb);
            param.put("fechaIni",fechaI);
            param.put("horaIni",horaI);
            param.put("fechaFin",fechaF);
            param.put("horaFin",horaF);
            param.put("descripcion",Desc);
            param.put("invitados",Pro);

            Usuario.post(host.Rest_Eventi, param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Trafico();
                    Intent integ = new Intent(getContext(),AcUsuario.class);
                    integ.putExtra("nom",Tnom);
                    integ.putExtra("ape",Tape);
                    integ.putExtra("ced",Tced);
                    integ.putExtra("ins",Tins);
                    integ.putExtra("car",Tcar);
                    integ.putExtra("pro",Tpro);
                    getContext().startActivity(integ);
                    getActivity().finish();
                    Toast.makeText(getContext(),"El Evento Se Esta Creando", Toast.LENGTH_SHORT).show();
                    notificacion();
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        String msn = errorResponse.getString("msn");
                        Toast.makeText(getContext(),"lo Sentimos! "+msn,Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void Novacios() {
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
    }

    private void notificacion() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.iconsedes);
        builder.setContentTitle("Nuevo Evento Creado");
        builder.setContentText(nomb);
        builder.setColor(Color.BLACK);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA,1000,1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setAutoCancel(true);
        Intent inten = new Intent(getContext(),DetallEvento.class);
        inten.putExtra("nom",nomb);
        inten.putExtra("fei",fechaI);
        inten.putExtra("fef",fechaF);
        inten.putExtra("hoi",horaI);
        inten.putExtra("hof",horaF);
        inten.putExtra("des",Desc);
        inten.putExtra("cos",Pro);
        PendingIntent pend = PendingIntent.getActivity(getContext(),0, inten,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pend);
        NotificationManager NmangerC = (NotificationManager)getContext().getSystemService(getContext().NOTIFICATION_SERVICE);
        NmangerC.notify(NOTIFICACION_id,builder.build());
    }

    private void Trafico(){
        Intent inte = getActivity().getIntent();
        Intent Delogueo = getActivity().getIntent();
        Bundle B = getActivity().getIntent().getExtras();
        if (B != null){
            Tnom=B.getString("nom");
            Tape= B.getString("ape");
            Tced=B.getString("ced");
            Tcar=B.getString("car");
            Tins=B.getString("ins");
            Tpro=B.getString("pro");
            //Toast.makeText(getContext(),"desde: "+Tnom,Toast.LENGTH_SHORT).show();
        }
    }
}
