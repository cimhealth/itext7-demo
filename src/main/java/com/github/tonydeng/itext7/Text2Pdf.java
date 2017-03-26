package com.github.tonydeng.itext7;

import com.github.tonydeng.imagegenerator.utils.FontUtils;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by tonydeng on 2017/3/24.
 */
public class Text2Pdf {
    public static final String TEXT
            = "src/main/resources/data/jekyll_hyde1.txt";
    public static final String DEST
            = "/data0/t.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
//        file.getParentFile().mkdirs();
        new Text2Pdf().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf)
                .setTextAlignment(TextAlignment.JUSTIFIED);
        BufferedReader br = new BufferedReader(new FileReader(TEXT));
        String line;
        PdfFont normal = PdfFontFactory.createFont(FontUtils.getDefaultFontByteArray(), PdfEncodings.IDENTITY_H, true);
        boolean title = true;
        while ((line = br.readLine()) != null) {
            document.add(new Paragraph(line).setFont(normal)).setFontSize(title ? 12 : 10);
            title = line.isEmpty();
        }
        document.close();
    }
}
