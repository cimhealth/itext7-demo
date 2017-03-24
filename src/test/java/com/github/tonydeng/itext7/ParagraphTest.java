package com.github.tonydeng.itext7;

import com.cim120.scu.file.FileUtils;
import com.github.tonydeng.imagegenerator.utils.FontUtils;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tonydeng on 2017/3/24.
 */
@Slf4j
public class ParagraphTest extends BaseTest {
    private static List<String> lines = Arrays.asList(
            "监测动态心电图23小时31分钟。 平均心率90 bpm， 最慢心率47 bpm， 发生于07-09 14:21， 最快心率185 bpm， 发生于07-09 17:12。 共分析心搏总数114242次， 有59次大",
            "于2.0秒的停搏， 其中最长为2.8秒， 发生于0时53分。",
            "全程心房颤动。ST-T未见明显异常。哈哈哈收到发",
            "动态心电图建议: 心房颤动(全程)，心室率偏快，伴长RR间歇 ",
            "(注:建议永久起搏器置入治疗)"


    );
    private static final int SUMMARY_X2 = 15;
    private static final int SUMMARY_Y2 = 200;
    private static final int SUMMARY_W2 = 530;
    private static final int SUMMARY_H2 = 120;

    @Test
    public void test() throws IOException {
        PdfReader reader = getReader(FileUtils.read("/data0/1.pdf"));
        PdfDocument pdfDoc = new PdfDocument(reader, new PdfWriter("/data0/t.pdf"));
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        //重绘指定报告结论区域
        canvas.saveState();
        Rectangle toMove2 = new Rectangle(15, 200, 530, 120);
        canvas.setFillColor(Color.WHITE);
        canvas.rectangle(toMove2);
        canvas.fill();
        canvas.restoreState();

        Document doc = new Document(pdfDoc);
        PdfFont font = PdfFontFactory.createFont(FontUtils.getDefaultFontByteArray(),
                PdfEncodings.IDENTITY_H, true);
        //定义段落
        Paragraph paragraph = new Paragraph().setFont(font).setFontSize(11);
        //设置段落前后间距
        paragraph.setWordSpacing(1);
        //设置缩进
//        doc.showTextAligned(p, image.getX() + 10, image.getY() + image.getHeight(), 1, TextAlignment.LEFT, VerticalAlignment.TOP, 0);
        com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List().setFont(font).setFontSize(10);
        lines.forEach(
                s -> {
                    log.info("{}", s);
//                    Paragraph p = new Paragraph(s).setFontSize(10).setFont(font);
//                    doc.showTextAligned(p,15+10,200+120,1,TextAlignment.LEFT, VerticalAlignment.TOP, 0);

//                    LocationTextExtractionStrategy.TextChunk chunk = new LocationTextExtractionStrategy.TextChunk(s,);
                    list.add(s);
                }
        );
//        Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(font);
//        Text author = new Text("Robert Louis Stevenson").setFont(font);
//        Paragraph p = new Paragraph().add(title).add(" by ").add(author);
        doc.add(list);


        pdfDoc.close();
        reader.close();
    }

    @Test
    public void testCanvas() throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter("/data0/t.pdf"));

        //Add new page
        PageSize ps = PageSize.A4;
        PdfPage page = pdf.addNewPage(ps);

        PdfCanvas canvas = new PdfCanvas(page);

//        List<String> text = new ArrayList();
//        text.add("         Episode V         ");
//        text.add("  THE EMPIRE STRIKES BACK  ");
//        text.add("It is a dark time for the");
//        text.add("Rebellion. Although the Death");
//        text.add("Star has been destroyed,");
//        text.add("Imperial troops have driven the");
//        text.add("Rebel forces from their hidden");
//        text.add("base and pursued them across");
//        text.add("the galaxy.");
//        text.add("Evading the dreaded Imperial");
//        text.add("Starfleet, a group of freedom");
//        text.add("fighters led by Luke Skywalker");
//        text.add("has established a new secret");
//        text.add("base on the remote ice world");
//        text.add("of Hoth...");

        //Replace the origin of the coordinate system to the top left corner
        canvas.concatMatrix(1, 0, 0, 1, 0, ps.getHeight());
        canvas.beginText()
                .setFontAndSize(PdfFontFactory.createFont(FontUtils.getDefaultFontByteArray(),
                        PdfEncodings.IDENTITY_H, true), 14)
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

    @Test
    public void testNewYorkTimes() throws IOException {
       Chapter03 chapter03 = new Chapter03();

       chapter03.NewYorkTimes("/data0/t.pdf");
    }
}
