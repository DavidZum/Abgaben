import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpielFeldTest {

    SpielFeld sp = null;

    @Test
    public void testInit() {
        sp = new SpielFeld();
        sp.init();
        for (int i = 0; i < sp.getFelder().length; i++) {
            for (int j = 0; j < sp.getFelder()[i].length; j++) {
                if (sp.getFelder()[i][j] instanceof SchiffTeil) {
                    SchiffTeil s = (SchiffTeil) sp.getFelder()[i][j];
                    Feld[] felder = getFelder(new Position(i, j));
                    for (int k = 0; k < felder.length; k++) {
                        if (felder[k] instanceof SchiffTeil) {
                            SchiffTeil s2 = (SchiffTeil) felder[k];
                            assertEquals(s, s2);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testGetAnzahlSchiffe() {
        sp = new SpielFeld();
        sp.init();
        int davor = sp.getAnzahlSchiffe();
        for (int i = 0; i < sp.getFelder().length; i++) {
            for (int j = 0; j < sp.getFelder().length; j++) {
                if (sp.getFelder()[i][j] instanceof SchiffTeil) {
                    SchiffTeil s = (SchiffTeil) sp.getFelder()[i][j];
                    if (s.getId() == 1) {
                        sp.getFelder()[i][j] = new Feld();
                    }
                }
            }
        }
        int danach = sp.getAnzahlSchiffe();
        assertTrue(davor - 1 == danach);
    }

    @Test
    public void testGetIds() {
        sp = new SpielFeld();
        sp.init();
        int[] davor = sp.getIds();
        for (int i = 0; i < sp.getFelder().length; i++) {
            for (int j = 0; j < sp.getFelder().length; j++) {
                if (sp.getFelder()[i][j] instanceof SchiffTeil) {
                    SchiffTeil s = (SchiffTeil) sp.getFelder()[i][j];
                    if (s.getId() == 1) {
                        sp.getFelder()[i][j].setGetroffen(true);
                    }
                }
            }
        }
        int[] danach = sp.getIds();
        assertTrue(davor.length - 1 == danach.length);
    }

    @Test
    public void testFertig() {
        sp = new SpielFeld();
        sp.init();
        for (int i = 0; i < sp.getFelder().length; i++) {
            for (int j = 0; j < sp.getFelder()[i].length; j++) {
                if (sp.getFelder()[i][j] instanceof SchiffTeil) {
                    sp.getFelder()[i][j].setGetroffen(true);
                }
            }
        }
        assertTrue(sp.fertig());
    }

    private Feld[] getFelder(Position p) {
        Feld[] felder = new Feld[8];
        if (p.getX() + 1 < sp.getFelder().length) {
            felder[0] = sp.getFelder()[p.getX() + 1][p.getY()];
        }
        if (p.getX() - 1 >= 0) {
            felder[1] = sp.getFelder()[p.getX() - 1][p.getY()];
        }
        if (p.getY() + 1 < sp.getFelder().length) {
            felder[2] = sp.getFelder()[p.getX()][p.getY() + 1];
        }
        if (p.getY() - 1 >= 0) {
            felder[3] = sp.getFelder()[p.getX()][p.getY() - 1];
        }
        if (p.getX() + 1 < sp.getFelder().length && p.getY() + 1 < sp.getFelder().length) {
            felder[4] = sp.getFelder()[p.getX() + 1][p.getY() + 1];
        }
        if (p.getX() + 1 < sp.getFelder().length && p.getY() - 1 >= 0) {
            felder[5] = sp.getFelder()[p.getX() + 1][p.getY() - 1];
        }
        if (p.getX() - 1 >= 0 && p.getY() - 1 >= 0) {
            felder[6] = sp.getFelder()[p.getX() - 1][p.getY() - 1];
        }
        if (p.getX() - 1 >= 0 && p.getY() + 1 < sp.getFelder().length) {
            felder[7] = sp.getFelder()[p.getX() - 1][p.getY() + 1];
        }

        for (int i = 0; i < felder.length; i++) {
            if (felder[i] == null) {
                felder[i] = new Feld();
            }
        }
        return felder;
    }
}
