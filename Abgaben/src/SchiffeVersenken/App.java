package SchiffeVersenken;

import java.util.Scanner;

public class App {

    static SpielFeld sp = new SpielFeld();

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        sp.init();
        while (!sp.fertig()) {
            System.out.printf("Verbleibende Schiffe: %d\n", sp.getAnzahlSchiffe());
            System.out.println(sp);
            System.out.println("Koordinaten eingeben (x y): ");
            String str = s.nextLine();
            String[] s = str.split(" ");
            Position p = new Position(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            sp.schuss(p);
        }
        System.out.printf("Schüsse insgesamt: %d \ndavon Schiffe: %d",
                sp.getWasserGetroffen() + sp.getSchiffeGetroffen(), sp.getSchiffeGetroffen());
    }
}

