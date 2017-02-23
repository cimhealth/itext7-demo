package com.github.tonydeng.itext7;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

/**
 * Created by tonydeng on 2017/2/23.
 */
public class HelloWorld {

    public static void main(String[] args) throws FileNotFoundException {


        PdfWriter writer = new PdfWriter("hello.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello World!"));
        document.close();
    }
}
