package me.mayhem.util;

public class RGB {

    private final int red;
    private final int green;
    private final int blue;

    protected RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public boolean isEqual(int red, int green, int blue) {
        return this.red == red && this.green == green && this.blue == blue;
    }

    public static RGB of(int red, int green, int blue) {
        return new RGB(red, green, blue);
    }
}
