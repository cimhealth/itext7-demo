package com.github.tonydeng.itext7;

import com.github.tonydeng.imagegenerator.utils.FontUtils;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonydeng on 2017/3/26.
 */
@Slf4j
public class Text2PdfTest extends BaseTest {
    private static List<String> LINES;

    private static PdfFont FONT;


    @Before
    public void init() throws IOException {
        FONT = PdfFontFactory.createFont(FontUtils.getDefaultFontByteArray(), PdfEncodings.IDENTITY_H, true);
        LINES = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data/jekyll_hyde1.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            LINES.add(line);
        }
    }

    @Test
    public void testGenerateParagraphText() throws IOException {
        Text2Pdf.generateParagraphText(LINES,FONT,"/data0/t.pdf");
    }

    @Test
    public void testGenerateCanvasText() throws IOException {
        log.info("{}", LINES.size());

        Text2Pdf.generateCanvasText(LINES, FONT, "/data0/t.pdf");
    }
}
