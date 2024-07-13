package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesToChar {
    public static void readPixels(String fileName){
        try {
            BufferedImage image = ImageIO.read(new File(fileName));
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    int color=image.getRGB(j,i);
                    if(color==-1){
                        System.out.print(".");
                    }
                    else{
                        System.out.print("0");
                    }
                }
                System.out.println();
            }
        }
        catch (IOException e){
            System.out.println("File was not found");
        }
    }
}