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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
public class UsQR extends Fragment {
    private View V;
    private TextView NOM;
    private Button CREA;
    private ImageView IMG;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        V = inflater.inflate(R.layout.fragment_us_qr, container, false);
        NOM = (TextView)V.findViewById(R.id.TVENOM);
        CREA = (Button)V.findViewById(R.id.button);
        IMG = (ImageView)V.findViewById(R.id.imageView3);
        CREA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text =NOM.getText().toString();
                if(Text!=null){
                    try {
                        MultiFormatWriter multi = new MultiFormatWriter();
                        BitMatrix Bma = multi.encode(Text, BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder ();
                        Bitmap bitmap = barcodeEncoder.createBitmap(Bma);
                        IMG.setImageBitmap (bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return  V;
    }
}
