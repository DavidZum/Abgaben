public class SchiffTeil extends Feld {

    int id;

    public SchiffTeil(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        if (super.isGetroffen()) {
            return "*";
        } else {
            return "_";
        }
    }

    @Override
    public boolean equals(Object obj) {
       SchiffTeil s = (SchiffTeil) obj;
       return this.id == s.id;
    }

    

}
