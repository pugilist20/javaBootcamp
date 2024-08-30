package edu.school21.printer;

import edu.school21.renderer.Renderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PrinterWithDateTimeImpl implements Printer {
    Renderer renderer;
    @Autowired
    public PrinterWithDateTimeImpl(@Qualifier("rendererStandardImpl") Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String string) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"))+" "+ renderer.print(string) );
    }
}
