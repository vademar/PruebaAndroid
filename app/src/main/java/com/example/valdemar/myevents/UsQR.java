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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
public class UsQR extends Fragment {
    private View V;
    private TextView NOM,AP,CI,PROF,CAR,INS;
    private Button CREA;
    private ImageView IMG;
    private String DATOS;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        V = inflater.inflate(R.layout.fragment_us_qr, container, false);
        NOM = (TextView)V.findViewById(R.id.TVENOM);
        AP = (TextView)V.findViewById(R.id.TVEAPE);
        CI = (TextView)V.findViewById(R.id.TVECI);
        CAR = (TextView)V.findViewById(R.id.TVECAR);
        INS = (TextView)V.findViewById(R.id.TVEINS);
        PROF = (TextView)V.findViewById(R.id.TVEPROF);

        CREA = (Button)V.findViewById(R.id.button);
        IMG = (ImageView)V.findViewById(R.id.imageView3);
        CREA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = NOM.getText().toString();
                String ap = AP.getText().toString();
                String ci = CI.getText().toString();
                String car = CAR.getText().toString();
                String ins = INS.getText().toString();
                String prof = PROF.getText().toString();
                DATOS = nom+"; "+ap+"; "+ci+"; "+car+"; "+ins+"; "+prof;
                if(nom!=null){
                    try {
                        MultiFormatWriter multi = new MultiFormatWriter();
                        BitMatrix Bma = multi.encode(DATOS, BarcodeFormat.QR_CODE,600,600);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder ();
                        Bitmap bitmap = barcodeEncoder.createBitmap(Bma);
                        IMG.setImageBitmap (bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getContext(),"Datos: "+DATOS, Toast.LENGTH_SHORT).show();

            }
        });
        LlenarDatos();
        return  V;
    }
    private void LlenarDatos() {
        Toast.makeText(getContext(),"En la Funcion ", Toast.LENGTH_SHORT).show();

    }

}
