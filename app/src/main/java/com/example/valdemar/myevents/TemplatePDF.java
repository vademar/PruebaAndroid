package com.example.valdemar.myevents;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TemplatePDF {
    private Context context;
    private File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font ftitle =  new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font fSubTitle =  new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
    private Font fText =  new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
    private Font fHighText =  new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
    private String Titulo;
    //para compartir el contexto\\
    public TemplatePDF(Context context) {
        this.context = context;
    }

    private void createFile(String nombre){
        Titulo = nombre+".pdf";
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"Sedes");
        if(!folder.exists())
            folder.mkdirs();
        pdfFile = new File(folder,Titulo);
    }

    public void openDocument(String nombre){
        createFile(nombre);
        try{
            document = new Document(PageSize.A4);
            pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
            document.open();
        }catch (Exception e){
            Log.e("openDocument",e.toString());
        }
    }

    public void closeDocument(){
        document.close();
    }

    public void Tablas(Image img,String nomb,String apel,String cedu,String carg,String inst,String prof){
        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidths(new float[] {12, 20});
            table.setWidthPercentage(32);

            PdfPCell titulo = new PdfPCell(new Paragraph("Credencial Para Eventos",fText));
            PdfPCell imagen = new PdfPCell(img);
            imagen.setFixedHeight(102);
            imagen.setColspan(2);
            imagen.setHorizontalAlignment(Element.ALIGN_CENTER);
            imagen.setVerticalAlignment(Element.ALIGN_MIDDLE);

            titulo.setColspan(2);
            titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titulo.setBackgroundColor(BaseColor.LIGHT_GRAY);

            table.addCell(titulo);
            table.addCell(imagen);

            table.addCell(" Nombre");
            table.addCell(nomb+" "+ apel);

            table.addCell(" Cedula");
            table.addCell(cedu);

            table.addCell(" Profesión");
            table.addCell(prof);

            table.addCell(" Institución");
            table.addCell(inst);

            table.addCell(" Cargo");
            table.addCell(carg);

            // Agregamos la tabla al documento
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public void appviewPdF(Activity activity){
        if(pdfFile.exists()){
            Uri uri = Uri.fromFile(pdfFile);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri,"application/pdf");
            try{
                activity.startActivity(intent);
            }catch (ActivityNotFoundException e){
                activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.adobe.reader")));
                Toast.makeText(context,"No Cuentas Con Una Aplicacion Para Abrir este Formato",Toast.LENGTH_SHORT).show();
            }
        }else
            Toast.makeText(activity.getApplicationContext(),"no se encontro el archivo",Toast.LENGTH_SHORT).show();
    }
}
