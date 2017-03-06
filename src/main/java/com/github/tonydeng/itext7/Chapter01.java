package com.github.tonydeng.itext7;

import com.cim120.scu.file.FileUtils;
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
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by tonydeng on 2017/2/23.
 */
@Slf4j
public class Chapter01 {
    private static PdfFont ZH_FONT, EN_FONT;

    static {

//        int count = fontFactory.registerSystemDirectories();
//        log.info("register system ZH_FONT count:'{}'", count);
//        try {
//            ZH_FONT = fontFactory.createFont("SimSum", PdfEncodings.IDENTITY_H);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            ZH_FONT = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            EN_FONT = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void helloWorld(String world) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter("hello.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello " + world + "!"));
        document.close();
    }

    public void rickAstley(ListItem... items) throws IOException {
        PdfWriter writer = new PdfWriter("reck_astley.pdf");
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Add a Paragraph
        document.add(new Paragraph("iText is:").setFont(ZH_FONT));
        // Create a List
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(ZH_FONT);
        // Add ListItem objects
        for (ListItem item : items) {
            list.add(item);
        }
        // Add the list
        document.add(list);

        //Close document
        document.close();
    }

    public void quickBrownFox(File... images) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter("quick_brown_fox.pdf");

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Compose Paragraph
        Paragraph p = new Paragraph("The quick brown ");
        for (File image : images) {
            log.info("image: '{}' '{}'", image, FileUtils.isExists(image));
            p.add(new Image(ImageDataFactory.create(image.getPath()))).add(" Image ");
        }

        // Add Paragraph to document
        document.add(p);

        //Close document
        document.close();
    }
}
