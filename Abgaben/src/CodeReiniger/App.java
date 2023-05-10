package CodeReiniger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        cleanSource("./", "C:/tmp/test");
    }

    public static void cleanSource(String srcDir, String destDir) throws IOException {
        copyPath(new File(srcDir), new File(destDir));
        ArrayList<File> destFiles = getJavaFiles(destDir);
        for (File file : destFiles) {
            Path p = Paths.get(file.getAbsolutePath());
            Files.delete(p);
            Files.createFile(p);
        }
        ArrayList<File> srcFiles = getJavaFiles(srcDir);
        for (File srcFile : srcFiles) {
            for (File destFile : destFiles) {
                if (srcFile.getName().equals(destFile.getName())) {
                    writeFile(srcFile, destFile);
                }
            }
        }
    }

    public static void writeFile(File srcFile, File destFile) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(srcFile), "UTF-8"));

        ReinigerWriter writer = new ReinigerWriter(new FileWriter(destFile));
        String str = reader.readLine();
        while (str != null) {
            writer.append(str);
            writer.append("\n");
            str = reader.readLine();
        }
        reader.close();
        writer.close();
    }

    public static ArrayList<File> getJavaFiles(String src) {
        ArrayList<File> javaFiles = new ArrayList<>();
        File f = new File(src);
        File[] files = f.listFiles((file, name) -> {
            File[] filestest = file.listFiles();
            for (File file2 : filestest) {
                if (file2.getName().equals(name)) {
                    return name.endsWith(".java") || file2.isDirectory();
                }
            }
            return false;
        });
        for (File file2 : files) {
            if (file2.isDirectory()) {
                javaFiles.addAll(getJavaFiles(src + "/" + file2.getName()));
            } else {
                javaFiles.add(file2);
            }
        }
        return javaFiles;
    }

    public static void copyPath(File srcFile, File destFile) throws IOException {
        File[] files = srcFile.listFiles();
        for (File file : files) {
            Path destPath = Paths.get(destFile.getAbsolutePath() + "/" + file.getName());
            Path srcPath = Paths.get(srcFile.getAbsolutePath() + "/" + file.getName());
            Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            if (file.isDirectory()) {
                copyPath(srcPath.toFile(), destPath.toFile());
            }
        }
    }
}
