import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpielfeldIO {

    private static File f = new File("testdateien/spielfeld.txt");
    private static Scanner s;
    private static SpielFeld sp = new SpielFeld();

    public static void lesen() throws FileNotFoundException {
        s = new Scanner(f);
        for (int i = 0; s.hasNextLine(); i++) {
            String str = s.nextLine();
            String[] parts = str.split(" ");
            Feld feld = null;
            for (int j = 0; j < parts.length; j++) {
                boolean getroffen;
                if (parts[j].charAt(0) == '_') {
                    getroffen = false;
                } else {
                    getroffen = true;
                }
                if (parts[j].charAt(1) == 'f') {
                    feld = new Feld();
                } else {
                    int id = Integer.parseInt(parts[j].charAt(1) + "");
                    feld = new SchiffTeil(id);
                }
                feld.setGetroffen(getroffen);
                sp.setFeld(feld, new Position(i, j));
            }
        }
    }

    public static SpielFeld getSpielfeld() throws FileNotFoundException {
        lesen();
        return sp;
    }
}