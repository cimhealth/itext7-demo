package com.github.tonydeng.itext7;

import com.cim120.scu.file.FileUtils;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

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

    public void unitedStates(File data) throws IOException {
        PdfWriter writer = new PdfWriter("united_states.pdf");

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf, PageSize.A4.rotate());

        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        Table table = new Table(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1});

        table.setWidthPercent(100);

        BufferedReader br = new BufferedReader(new FileReader(data));

        String line = br.readLine();
        processTable(table, line, bold, true);
        while ((line = br.readLine()) != null) {
            processTable(table, line, font, false);
        }
        br.close();
        document.add(table);
        document.close();
    }

    private void processTable(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader)
                table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            else
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
        }
    }
}
