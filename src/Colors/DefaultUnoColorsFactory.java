package Colors;

public class DefaultUnoColorsFactory {
    public static Color redColor() {
        return new Color(255, 0, 0);
    }

    public static Color greenColor() {
        return new Color(0, 255, 0);
    }

    public static Color blueColor() {
        return new Color(0, 0, 255);
    }

    public static Color yellowColor() {
        return new Color(150, 150, 180);
    }
}
