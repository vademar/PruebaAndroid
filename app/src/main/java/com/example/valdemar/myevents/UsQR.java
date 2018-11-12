package com.example.valdemar.myevents;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valdemar.myevents.caja.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class UsQR extends Fragment {
    private View V;
    private TextView NOMB,APEL,CEDU,PROF,CARG,INST;
    private Button CREA;
    private ImageView IMG;
    private String nomb,apel,cedu,prof,inst,carg, DATOS;
    private ArrayList<User> LISTINFO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        V = inflater.inflate(R.layout.fragment_us_qr, container, false);
        NOMB = (TextView)V.findViewById(R.id.TVENOM);
        APEL = (TextView)V.findViewById(R.id.TVEAPE);
        CEDU = (TextView)V.findViewById(R.id.TVECI);
        CARG = (TextView)V.findViewById(R.id.TVECAR);
        INST = (TextView)V.findViewById(R.id.TVEINS);
        PROF = (TextView)V.findViewById(R.id.TVEPROF);

        CREA = (Button)V.findViewById(R.id.button);
        IMG = (ImageView)V.findViewById(R.id.imageView3);
        CREA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomb = NOMB.getText().toString();
                apel = APEL.getText().toString();
                cedu = CEDU.getText().toString();
                carg = CARG.getText().toString();
                inst = INST.getText().toString();
                prof = PROF.getText().toString();
                DATOS = nomb+"; "+apel+"; "+cedu+"; "+carg+"; "+inst+"; "+prof;
                if(nomb!=null){
                    try {
                        MultiFormatWriter multi = new MultiFormatWriter();
                        BitMatrix Bma = multi.encode(DATOS, BarcodeFormat.QR_CODE,600,600);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder ();
                        Bitmap bitmap = barcodeEncoder.createBitmap(Bma);
                        IMG.setImageBitmap (bitmap);
                        Toast.makeText(getContext(),"Datos: "+DATOS, Toast.LENGTH_SHORT).show();
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        LlenarDatos();
        return  V;
    }
    private void LlenarDatos() {
        Toast.makeText(getContext(),"En la Funcion ", Toast.LENGTH_SHORT).show();
    }

}
