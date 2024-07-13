package ex02;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        menu(args[0]);
    }

    public static void menu(String args) throws IOException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        boolean stop = true;
        Path path = Path.of(args);
        while (stop) {
            String command = scanner.nextLine();
            switch (command) {
                case "mv": {
                    move(path);
                    break;
                }
                case "ls": {
                    ls(path.toFile());
                    break;
                }
                case "cd": {
                    path = changeDirectory(path);
                    break;
                }
                case "stop": {
                    stop = false;
                    break;
                }
            }
        }
    }

    public static void move(Path path) {
        try {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            System.out.println("Enter the new path:");
            String newPathStr = scanner.nextLine();
            Path newPath = Paths.get(newPathStr);
            Files.move(path, newPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public static void ls(File file) throws IOException {
        File[] filesList = file.listFiles();
        for (File f : filesList) {
            if (f.isDirectory()) {
                System.out.println(f.getName() + ":");
                ls(f);
            } else if (f.isFile()) {
                System.out.println(f.getName());
            }
        }
    }

    public static Path changeDirectory(Path path) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Enter the new path:");
        String newPathStr = scanner.nextLine();
        if (newPathStr.equals("..")) {
            path = path.getParent();
        } else if (newPathStr.startsWith("../")) {
            path = path.getParent().resolve(newPathStr.replace("../", ""));
        } else {
            path = path.resolve(newPathStr);
        }
        return path;
    }
}
