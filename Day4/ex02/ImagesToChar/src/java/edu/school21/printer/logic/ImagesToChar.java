package edu.school21.printer.logic;

import com.diogonunes.jcolor.Attribute;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static com.diogonunes.jcolor.Ansi.*;

public class ImagesToChar {
    public static void readPixels(String fileName, Args args){
        try {
            BufferedImage image = ImageIO.read(new File(fileName));
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    int color=image.getRGB(j,i);
                    if(color==-1){
                        Field field=Color.class.getField(args.getBlackColor());
                        System.out.print(colorize(" ",Attribute.BACK_COLOR(((Color)field.get(null)).getRed(), ((Color)field.get(null)).getGreen(), ((Color)field.get(null)).getBlue())));
                    }
                    else{
                        Field field=Color.class.getField(args.getWhiteColor());
                        System.out.print(colorize(" ",Attribute.BACK_COLOR(((Color)field.get(null)).getRed(), ((Color)field.get(null)).getGreen(), ((Color)field.get(null)).getBlue())));
                    }
                }
                System.out.println();
            }
        }
        catch (IOException e){
            System.out.println("File was not found");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}