package com.hunk.route.domain;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author hunk
 * @date 2022/3/2
 *     <p>
 */
public class Download {

    @Test
    public void start() throws Exception {
        for (int i = 1; i <= 22; i++) {
            String url, path;
            url = null;
            path = null;
            downloadPicture(url, path);
            TimeUnit.SECONDS.sleep(4L);
        }
    }

    private void downloadPicture(String urlList, String path) throws IOException {
        URL url = new URL(urlList);
        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = dataInputStream.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        fileOutputStream.write(output.toByteArray());
        dataInputStream.close();
        fileOutputStream.close();
    }
}
