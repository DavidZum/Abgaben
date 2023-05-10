package SchiffeVersenken;

import java.util.ArrayList;

public class SpielFeld {
    private Feld[][] felder = new Feld[10][10];
    private int wasserGetroffen;
    private int schiffeGetroffen;
    private int schiffeInsgesamt;

    public SpielFeld() {

    }

    public Feld[][] getFelder() {
        return felder;
    }

    public int getWasserGetroffen() {
        return wasserGetroffen;
    }

    public int getSchiffeGetroffen() {
        return schiffeGetroffen;
    }

    public int[] getIds() {
        int[] ids = new int[getAnzahlSchiffe()];
        int stelle = 0;
        for (int i = 0; i < schiffeInsgesamt; i++) {
            for (int j = 0; j < felder.length; j++) {
                boolean b = false;
                for (int j2 = 0; j2 < felder[i].length; j2++) {
                    if (felder[j][j2] instanceof SchiffTeil) {
                        SchiffTeil s = (SchiffTeil) felder[j][j2];
                        if (s.getId() == i && !(s.isGetroffen())) {
                            b = true;
                            ids[stelle] = s.getId();
                            stelle++;
                        }
                    }
                    if (b) {
                        break;
                    }
                }
                if (b) {
                    break;
                }
            }
        }
        return ids;
    }

    public int getAnzahlSchiffe() {
        int anzahl = 0;
        for (int i = 0; i < schiffeInsgesamt; i++) {
            for (int j = 0; j < felder.length; j++) {
                boolean b = false;
                for (int j2 = 0; j2 < felder[i].length; j2++) {
                    if (felder[j][j2] instanceof SchiffTeil) {
                        SchiffTeil s = (SchiffTeil) felder[j][j2];
                        if (s.getId() == i && !(s.isGetroffen())) {
                            b = true;
                            anzahl++;
                        }
                    }
                    if (b) {
                        break;
                    }
                }
                if (b) {
                    break;
                }
            }
        }
        return anzahl;
    }

    public void init() {
        felder = new Feld[10][10];
        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                felder[i][j] = new Feld();
            }
        }
        schiffSetzen(5, 0);
        schiffSetzen(4, 1);
        schiffSetzen(4, 2);
        schiffSetzen(3, 3);
        schiffSetzen(3, 4);
        schiffSetzen(3, 5);
        schiffSetzen(2, 6);
        schiffSetzen(2, 7);
        schiffSetzen(2, 8);
        schiffSetzen(2, 9);
        schiffeInsgesamt = 10;
    }

    private void schiffSetzen(int laenge, int id) {
        int folge = 0;
        ArrayList<Position> positions = new ArrayList<>();
        boolean nochmal = true;
        while (nochmal) {
            for (int i = 0; i < felder.length; i++) {
                for (int j = 0; j < felder[i].length; j++) {
                    if (schiffMoeglich(new Position(i, j, true))) {
                        folge++;
                    } else {
                        folge = 0;
                    }
                    boolean geht = true;
                    for (Position position : positions) {
                        if (position.equals(new Position(i, j - folge + 1, true))) {
                            geht = false;
                            folge = 0;
                        }
                    }
                    if (folge == laenge && geht) {
                        positions.add(new Position(i, j - folge + 1, true));
                        break;
                    }
                    if (i == felder.length - 1 && j == felder[i].length - 1) {
                        nochmal = false;
                    }
                }
                if (folge == laenge) {
                    folge = 0;
                    break;
                }
                folge = 0;
            }

        }

        nochmal = true;
        folge = 0;

        while (nochmal) {
            for (int i = 0; i < felder.length; i++) {
                for (int j = 0; j < felder[i].length; j++) {
                    if (schiffMoeglich(new Position(j, i, false))) {
                        folge++;
                    } else {
                        folge = 0;
                    }
                    boolean geht = true;
                    for (Position position : positions) {
                        if (position.equals(new Position(j - folge + 1, i, false))) {
                            geht = false;
                            folge = 0;
                        }
                    }
                    if (folge == laenge && geht) {
                        positions.add(new Position(j - folge + 1, i, false));
                        break;
                    }
                    if (i == felder.length - 1 && j == felder[i].length - 1) {
                        nochmal = false;
                    }
                }
                if (folge == laenge) {
                    folge = 0;
                    break;
                }
                folge = 0;
            }

        }

        Position p = positions.get((int) (Math.random() * positions.size()));
        for (int i = 0; i < laenge; i++) {
            if (p.getRotation()) {
                felder[p.getX()][p.getY() + i] = new SchiffTeil(id);
            } else {
                felder[p.getX() + i][p.getY()] = new SchiffTeil(id);
            }

        }
    }

    private boolean schiffMoeglich(Position p) {
        boolean[] b = new boolean[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = false;
        }
        if (p.getX() + 1 < felder.length) {
            if (!(felder[p.getX() + 1][p.getY()] instanceof SchiffTeil)) {
                b[0] = true;
            }
        } else {
            b[0] = true;
        }
        if (p.getX() - 1 >= 0) {
            if (!(felder[p.getX() - 1][p.getY()] instanceof SchiffTeil)) {
                b[1] = true;
            }
        } else {
            b[1] = true;
        }
        if (p.getY() + 1 < felder.length) {
            if (!(felder[p.getX()][p.getY() + 1] instanceof SchiffTeil)) {
                b[2] = true;
            }
        } else {
            b[2] = true;
        }
        if (p.getY() - 1 >= 0) {
            if (!(felder[p.getX()][p.getY() - 1] instanceof SchiffTeil)) {
                b[3] = true;
            }
        } else {
            b[3] = true;
        }
        if (p.getX() + 1 < felder.length && p.getY() + 1 < felder.length) {
            if (!(felder[p.getX() + 1][p.getY() + 1] instanceof SchiffTeil)) {
                b[4] = true;
            }
        } else {
            b[4] = true;
        }
        if (p.getX() + 1 < felder.length && p.getY() - 1 >= 0) {
            if (!(felder[p.getX() + 1][p.getY() - 1] instanceof SchiffTeil)) {
                b[5] = true;
            }
        } else {
            b[5] = true;
        }
        if (p.getX() - 1 >= 0 && p.getY() - 1 >= 0) {
            if (!(felder[p.getX() - 1][p.getY() - 1] instanceof SchiffTeil)) {
                b[6] = true;
            }
        } else {
            b[6] = true;
        }
        if (p.getX() - 1 >= 0 && p.getY() + 1 < felder.length) {
            if (!(felder[p.getX() - 1][p.getY() + 1] instanceof SchiffTeil)) {
                b[7] = true;
            }
        } else {
            b[7] = true;
        }
        return b[0] && b[1] && b[2] && b[3] && b[4] && b[5] && b[6] && b[7];
    }

    public boolean fertig() {
        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                if (felder[i][j] instanceof SchiffTeil && !felder[i][j].isGetroffen()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void schuss(Position p) {
        felder[p.getX()][p.getY()].setGetroffen(true);
        if (felder[p.getX()][p.getY()] instanceof SchiffTeil) {
            schiffeGetroffen++;
        } else {
            wasserGetroffen++;
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                str += felder[i][j].toString() + " ";
            }
            str += "\n";
        }
        return str;
    }

    public String testToString() {
        String str = "";
        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                if (felder[i][j] instanceof SchiffTeil) {
                    SchiffTeil s = (SchiffTeil) felder[i][j];
                    str += felder[i][j].toString() + s.getId() +" ";
                }
                else {
                    str += felder[i][j].toString() + "f ";
                }
            }
            str += "\n";
        }
        return str;
    }

    public void setFeld(Feld f, Position p) {
        felder[p.getX()][p.getY()] = f;
    }

    public void clear() {
        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder.length; j++) {
                felder[i][j] = new Feld();
            }
        }
    }
}

