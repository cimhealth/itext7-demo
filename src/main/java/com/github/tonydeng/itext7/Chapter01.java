package com.github.tonydeng.itext7;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by tonydeng on 2017/2/23.
 */
public class Chapter01 {

    public void helloWorld(String world) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter("hello.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello " + world + "!"));
        document.close();
    }

    public void rickAstley(java.util.List<ListItem> items) throws IOException {
        PdfWriter writer = new PdfWriter("reck_astley.pdf");

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        // Add a Paragraph
        document.add(new Paragraph("iText is:").setFont(font));
        // Create a List
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);
        // Add ListItem objects
//        list.add(new ListItem("Never gonna give you up"))
//                .add(new ListItem("Never gonna let you down"))
//                .add(new ListItem("Never gonna run around and desert you"))
//                .add(new ListItem("Never gonna make you cry"))
//                .add(new ListItem("Never gonna say goodbye"))
//                .add(new ListItem("Never gonna tell a lie and hurt you"));

        items.forEach(i-> list.add(i));
        // Add the list
        document.add(list);

        //Close document
        document.close();
    }

    public void quickBrownFox(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter("quick_brown_fox.pdf");

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Compose Paragraph
        Image fox = new Image(ImageDataFactory.create(""));
        Image dog = new Image(ImageDataFactory.create(""));
        Paragraph p = new Paragraph("The quick brown ")
                .add(fox)
                .add(" jumps over the lazy ")
                .add(dog);
        // Add Paragraph to document
        document.add(p);

        //Close document
        document.close();
    }
}
