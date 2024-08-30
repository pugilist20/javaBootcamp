package edu.school21.printer;

import edu.school21.renderer.Renderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix;

    @Autowired
    public PrinterWithPrefixImpl(@Qualifier("rendererStandardImpl") Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String string) {
        System.out.println(renderer.print(prefix) +" "+ renderer.print(string));

    }
}
