package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = {"--white", "-w"})
    private String whiteColor;
    @Parameter(names = {"--blac", "-b"})
    private String blackColor;

    public void run(){
        System.out.println(whiteColor +" "+ blackColor);
    }

    public String getWhiteColor() {
        return whiteColor;
    }

    public String getBlackColor() {
        return blackColor;
    }
}
