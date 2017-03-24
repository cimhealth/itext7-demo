package com.github.tonydeng.itext7;

import com.itextpdf.kernel.pdf.PdfReader;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * Created by tonydeng on 2017/3/6.
 */
public class BaseTest {

    public static File getFile(final String path) throws IOException {
        final URL url = BaseTest.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new FileNotFoundException("couldn't find " + path);
        }
        URI uri = null;
        try {
            uri = url.toURI();
        } catch (final java.net.URISyntaxException ex) {
            throw new IOException(ex);
        }
        return new File(uri);
    }

    protected static PdfReader getReader(byte[] file) throws IOException {
        InputStream is = new ByteArrayInputStream(file);
        PdfReader reader = new PdfReader(is);
        reader.setUnethicalReading(true);
        return reader;
    }
}
