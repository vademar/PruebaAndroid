package com.example.valdemar.myevents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class UsQR extends Fragment {
    private View V;
    private TextView NOMB,APEL,CEDU,PROF,CARG,INST;
    private Button imprime;
    private ImageView IMG;
    private String nomb,apel,cedu,prof,inst,carg, DATOS;
    private String[]header = {"id","nombre","apellido"};
    private String shorText; //= "hola";
    private String longText; //= "donde va la posicion de mi QR";
    private TemplatePDF templatePDF;
    private Bitmap bitmap;
    private ByteArrayOutputStream stream;
    private Image imge;
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

        imprime =(Button)V.findViewById(R.id.Imprimir);
        IMG = (ImageView)V.findViewById(R.id.imageView3);
        GenerateQR();
        imprime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //templatePDF.viewPdF();
                templatePDF.appviewPdF(getActivity());
            }
        });

        templatePDF = new TemplatePDF(getContext());
        templatePDF.openDocument();
        //templatePDF.addIMG(imge);
        //templatePDF.addMetaData("clientes", "Evento", "marines");
        //templatePDF.addTitles("Eventos","Usuario","24/11/18");
        //templatePDF.addParagraph(shorText);
        //templatePDF.addParagraph(longText);
        templatePDF.Tablas(imge,nomb,apel,cedu,carg,inst,prof);
        //templatePDF.createTable(header,getClients());
        templatePDF.closeDocument();

        LlenarDatos();
        return  V;
    }
    private void LlenarDatos() {
        Intent inte = getActivity().getIntent();
        Intent Delogueo = getActivity().getIntent();
        Intent integ = getActivity().getIntent();
        Bundle B = getActivity().getIntent().getExtras();
        if (B != null){
            NOMB.setText(B.getString("nom"));
            APEL.setText(B.getString("ape"));
            CEDU.setText(B.getString("ced"));
            CARG.setText(B.getString("car"));
            INST.setText(B.getString("ins"));
            PROF.setText(B.getString("pro"));
        }
    }

    private void GenerateQR(){
        LlenarDatos();
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
                bitmap = barcodeEncoder.createBitmap(Bma);
                stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitmapData = stream.toByteArray();
                imge = Image.getInstance(bitmapData);
                imge.scaleAbsolute(100,100);
                imge.setAlignment(Element.ALIGN_LEFT);
                IMG.setImageBitmap (bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadElementException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String[]>getClients(){
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"1","juan","quintana"});
        rows.add(new String[]{"2","juan","quintana"});
        rows.add(new String[]{"3","juan","quintana"});
        rows.add(new String[]{"4","juan","quintana"});
        rows.add(new String[]{"5","juan","quintana"});
        return rows;
    }
}
