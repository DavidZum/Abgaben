package SchiffeVersenken;

public class Feld {
    private boolean getroffen;

    public Feld() {

    }

    public boolean isGetroffen() {
        return getroffen;
    }

    public void setGetroffen(boolean getroffen) {
        this.getroffen = getroffen;
    }

    @Override
    public String toString() {
        if (getroffen) {
            return "~";
        } else {
            return "_";
        }
    }
}

