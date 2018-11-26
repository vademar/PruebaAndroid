package com.example.valdemar.myevents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

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

    //para compartir el contexto\\
    public TemplatePDF(Context context) {
        this.context = context;
    }

    private void createFile(){
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"Sedes");
        if(!folder.exists())
            folder.mkdirs();
        pdfFile = new File(folder,"MyQr.pdf");
    }

    public void openDocument(){
        createFile();
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

    public void addMetaData(String title, String subject, String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }

    private void addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }

    public void addTitles(String title, String subTitle, String date){
        try{
            paragraph = new Paragraph();
            addChildP(new Paragraph(title,ftitle));
            addChildP(new Paragraph(subTitle,fSubTitle));
            addChildP(new Paragraph("Generado"+date,fHighText));
            paragraph.setSpacingAfter(30);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("addTitles",e.toString());
        }
    }
    public void addIMG(Image img){
        try {
            document.add(img);
        }catch (Exception e){
            Log.e("addIMG",e.toString());
        }
    }
    public void addParagraph(String text){
        try{
            paragraph = new Paragraph(text,fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("addParagraph",e.toString());
        }
    }
    public void createTable(String[]header, ArrayList<String[]>clients){
        try{
            paragraph =  new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable =  new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC=0;
            while (indexC<header.length){
                pdfPCell = new PdfPCell(new Phrase(header[indexC++],fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.BLUE);
                pdfPTable.addCell(pdfPCell);
            }
            for(int indexR=0; indexR<clients.size(); indexR++){
                String[]row = clients.get(indexR);
                for(indexC=0; indexC<clients.size();indexC++){
                    pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
                    pdfPTable.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("createTable",e.toString());
        }
    }
    public void viewPdF(){
        Intent intent = new Intent(context,ViewPDFActivity.class);
        intent.putExtra("path",pdfFile.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
