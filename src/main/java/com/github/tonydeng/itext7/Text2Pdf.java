package com.github.tonydeng.itext7;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.IOException;
import java.util.List;

/**
 * Created by tonydeng on 2017/3/24.
 */
public class Text2Pdf {
    public static final String TEXT
            = "src/main/resources/data/jekyll_hyde1.txt";
    public static final String DEST
            = "/data0/t.pdf";


    public static void generateParagraphText(List<String> lines, PdfFont font, String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf)
                .setTextAlignment(TextAlignment.JUSTIFIED).setFont(font);
        boolean title = true;
        Paragraph p = new Paragraph();
        for (String line : lines) {
            document.add(new Paragraph(new Text(line))).setFontSize(title ? 12 : 10);
//            p.add(new Text(line).setFontSize(title ? 12 : 10));
            title = line.isEmpty();
        }
//        document.add(p);
        document.close();
    }

    public static void generateCanvasText(List<String> lines, PdfFont font, String desc) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(desc));
        PageSize ps = PageSize.A4;
        PdfPage page = pdf.addNewPage(ps);
        PdfCanvas canvas = new PdfCanvas(page);
        canvas.concatMatrix(1, 0, 0, 1, 0, ps.getHeight());
        canvas.beginText()
                .setFontAndSize(font, 11)
                .setLeading(14 * 1.2f)
                .moveText(70, -40);
        for (String s : lines) {
            //Add text and move to the next line
            canvas.newlineShowText(s);
        }
        canvas.endText();

        //Close document
        pdf.close();
    }
}
