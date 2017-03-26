package com.github.tonydeng.itext7;

import com.github.tonydeng.imagegenerator.utils.FontUtils;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.extern.slf4j.Slf4j;
import sun.font.FontUtilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by tonydeng on 2017/3/24.
 */
@Slf4j
public class Chapter03 {
    public static final String APPLE_IMG = "/data0/img/10-10.jpg";
    public static final String APPLE_TXT = "src/main/resources/data/ny_times_apple.txt";
    public static final String FACEBOOK_IMG = "src/main/resources/images/ny_times_fb.jpg";
    public static final String FACEBOOK_TXT = "src/main/resources/data/ny_times_fb.txt";
    public static final String INST_IMG = "src/main/resources/images/ny_times_inst.jpg";
    public static final String INST_TXT = "src/main/resources/data/ny_times_inst.txt";


    static PdfFont timesNewRoman = null;
    static PdfFont timesNewRomanBold = null;

    public void NewYorkTimes(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PageSize ps = PageSize.A4;

        // Initialize document
        Document document = new Document(pdf, ps);

        //Set column parameters
//        float offSet = 36;
//        float columnWidth = (ps.getWidth() - offSet * 2 + 10) / 3;
//        float columnHeight = ps.getHeight() - offSet * 2;

        float offSet = 36;
        float columnWidth = (ps.getWidth() - offSet * 2 + 10) / 3;
        float columnHeight = ps.getHeight() - offSet * 2;

        //Define column areas
        Rectangle[] columns = {new Rectangle(10, 200, 600, 400)};
//                new Rectangle(offSet + columnWidth, offSet, columnWidth, columnHeight),
//                new Rectangle(offSet + columnWidth * 2 + 5, offSet, columnWidth, columnHeight)};
        document.setRenderer(new ColumnDocumentRenderer(document, columns));

        Image apple = new Image(ImageDataFactory.create(APPLE_IMG)).setWidth(columnWidth);
        String articleApple = new String(Files.readAllBytes(Paths.get(APPLE_TXT)), StandardCharsets.UTF_8);
        String[] array = articleApple.split("\n");
//        Paragraph p3 = new Paragraph().setFont(PdfFontFactory.createFont(FontUtils.getDefaultFontByteArray(),
//                PdfEncodings.IDENTITY_H, true))
//                .setFontSize(10)
//                .setWidth(580)
//                .setFixedLeading(10);
        Style style = new Style()
                .setWidth(580)
                .setFont(PdfFontFactory.createFont(FontUtils.getDefaultFontByteArray(),PdfEncodings.IDENTITY_H, true))
                .setFontSize(10);
//                .setFontColor(Color.RED)
//                .setBackgroundColor(Color.LIGHT_GRAY);
        Paragraph p = new Paragraph();
        for (String a : array) {
//            addArticle(document, "总   结", "By Tony Deng 17, 2017", apple, a);
           p.add(a).addStyle(style);
        }
        document.add(p);

//        Image facebook = new Image(ImageDataFactory.create(FACEBOOK_IMG)).setWidth(columnWidth);
//        String articleFB = new String(Files.readAllBytes(Paths.get(FACEBOOK_TXT)), StandardCharsets.UTF_8);
//        addArticle(document, "With \"Smog Jog\" Through Beijing, Zuckerberg Stirs Debate on Air Pollution", "By PAUL MOZUR MARCH 18, 2016", facebook, articleFB);
//        Image inst = new Image(ImageDataFactory.create(INST_IMG)).setWidth(columnWidth);
//        String articleInstagram = new String(Files.readAllBytes(Paths.get(INST_TXT)), StandardCharsets.UTF_8);
//        addArticle(document, "Instagram May Change Your Feed, Personalizing It With an Algorithm","By MIKE ISAAC MARCH 15, 2016", inst, articleInstagram);
        PdfCanvas canvas = new PdfCanvas(pdf.getFirstPage());


        canvas.saveState();
        PdfExtGState state = new PdfExtGState();
        state.setFillOpacity(0.6f);
        canvas.setExtGState(state);
        canvas.addImage(ImageDataFactory.create(APPLE_IMG), 313, 300, 146, false);

        canvas.restoreState();
//        document
        document.close();
    }

    private static void addArticle(Document doc, String title, String author, Image img, String text) throws IOException {
        timesNewRoman = PdfFontFactory.createFont(FontUtils.getDefaultFontByteArray(),
                PdfEncodings.IDENTITY_H, true);
        timesNewRomanBold = PdfFontFactory.createFont(FontUtils.getDefaultFontByteArray(),
                PdfEncodings.IDENTITY_H, true);

//        Paragraph p1 = new Paragraph(title)
//                .setFont(timesNewRomanBold)
//                .setFontSize(14);
//        doc.add(p1);
//        doc.add(img);
//        Paragraph p2 = new Paragraph()
//                .setFont(timesNewRoman)
//                .setFontSize(7)
//                .setFontColor(Color.GRAY)
//                .add(author);
//        doc.add(p2);
        Paragraph p3 = new Paragraph()
                .setFont(timesNewRoman)
                .setFontSize(10)
                .setWidth(580)
                .setFixedLeading(10)
                .add(text);
        doc.add(p3);
//       Document doc1 = doc.showTextAligned(p3, 15, 200, TextAlignment.LEFT);
    }
}
