package File_Tree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    private static int AnzahlOrdner;
    private static int anzahlFiles;

    public static void main(String[] args) throws Exception {
        printFileTree("./", "├─", 0);
        /*
         * Filterangaben:
         * 0 = alles; 1 = nur .java Dateien; 2 = nur .class Dateien; 3 = Name länger als
         * 10 Zeichen; 4 = größer als 1MB
         */
        System.out.printf("Ordner: %d\nFiles: %d", AnzahlOrdner, anzahlFiles);
    }

    public static void printFileTree(String path, String einrueckung, int filter) throws IOException {
        File f = new File(path);
        File[] files = f.listFiles((file, name) -> {
            File[] filestest = file.listFiles();
            for (File file2 : filestest) {
                if (file2.getName().equals(name)) {
                    switch (filter) {
                        case 0:
                            return true;
                        case 1:
                            return name.endsWith(".java") || file2.isDirectory();
                        case 2:
                            return name.endsWith(".class") || file2.isDirectory();
                        case 3:
                            return file2.getName().length() > 10 || file2.isDirectory();
                        case 4:
                            return file2.length() > 1000000 || file2.isDirectory();
                    }
                }
            }
            return false;

        });
        for (File file : files) {
            Path p = Paths.get(path + "/" + file.getName());
            if (file.isDirectory()) {
                AnzahlOrdner++;
                System.out.println(einrueckung + file.getName());
                printFileTree(path + "/" + file.getName(), einrueckung + "  ├─ ", filter);
            } else {
                anzahlFiles++;
                System.out.println(einrueckung + file.getName() + " (Größe: " + Files.size(p) + " B; Änderungsdatum: "
                        + Files.getLastModifiedTime(p) + "; Owner: " + Files.getOwner(p) + "; Festplatte: "
                        + Files.getFileStore(p) + ")");
            }
        }
    }
}
