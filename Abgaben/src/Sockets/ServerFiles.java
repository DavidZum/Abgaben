package Sockets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerFiles {

    private static String dir = "C:/tmp/server";

    public static void main(String[] args) throws IOException {
        System.out.println(listFiles(dir));
    }

    public static String getDir() {
        return dir;
    }

    public static File createFile(String name) throws IOException {
        Path p = Paths.get(dir + "/" + name);
        return Files.createFile(p).toFile();
    }

    public static void writeFile(String value, File f) throws IOException {
        FileWriter writer = new FileWriter(f, true);
        writer.write(value);
        writer.close();
    }

    public static String listFiles(String dir) {
        String str = "";
        File f = new File(dir);
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                str += listFiles(dir + "/" + file.getName());
            } else {
                str+= file.getName() + "\n";
            }
        }
        return str;
    }

    public static String getFileGroesse(File file) throws IOException {
        Path p = file.toPath();
        return "" + Files.size(p);
    }
}
