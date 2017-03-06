package com.github.tonydeng.itext7;

import com.itextpdf.layout.element.ListItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by tonydeng on 2017/2/23.
 */
@Slf4j
public class Chapter01Test extends BaseTest {
    private Chapter01 chapter = new Chapter01();

    @Test
    public void testHelloWorld() throws FileNotFoundException {
        log.info("hello world");

        chapter.helloWorld("Tony Deng, 邓涛");
    }

    @Test
    public void testRickAstley() throws IOException {
        chapter.rickAstley(
                new ListItem("Max Deng"),
                new ListItem("邓华锐"),
                new ListItem("Tony Deng"),
                new ListItem("邓涛"),
                new ListItem("Jane Hao"),
                new ListItem("郝杰"));
    }

    @Test
    public void testQuickBrownFox() throws IOException {

        chapter.quickBrownFox(
                getFile("images/dog.bmp"),
                getFile("images/2.jpg"),
                getFile("images/3.jpg"),
                getFile("images/1.png")
        );
    }

    @Test
    public void testUnitedStates() throws IOException {
        chapter.unitedStates(getFile("data/united_states.csv"));
    }
}
