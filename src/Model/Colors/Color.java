package Model.Colors;

public class Color {
    private final int red;
    private final int green;
    private final int blue;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Color other = (Color)obj;
        //noinspection RedundantIfStatement
        if (other.blue == blue && other.green == green && other.red == red) {
            return true;
        }
        return false;
    }
}
