package com.github.tonydeng.itext7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
}
