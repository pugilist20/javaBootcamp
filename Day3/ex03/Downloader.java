package ex03;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static ex03.Main.urls;

public class Downloader extends Thread {
    static volatile int counter=0;

    @Override
    public void run() {
        try {
            downloadFiles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void downloadFiles() throws IOException {
        if(counter<urls.size()) {
            int index;
            synchronized (this) {
                index = counter;
                counter += 2;
            }
            System.out.println(Thread.currentThread().getName() + " start download file number " + urls.get(index));
            URL website = new URL(urls.get(index + 1));
            try (InputStream in = website.openStream()) {
                String[] strs = urls.get(index + 1).split("/");
                Path target = Paths.get("src\\ex03\\" + strs[strs.length - 1]);
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println(Thread.currentThread().getName() + " finish download file number " + urls.get(index));
            if (counter <urls.size()) {
                downloadFiles();
            }
        }
    }
}
