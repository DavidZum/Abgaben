package SchiffeVersenken;

public class Position {
    int x;
    int y;
    boolean rotation;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, boolean rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getRotation() {
        return rotation;
    }

    @Override
    public boolean equals(Object obj) {
        Position p = (Position) obj;
        return p.getX() == this.getX() && p.getY() == this.getY() && p.getRotation() == this.getRotation();
    }

}

