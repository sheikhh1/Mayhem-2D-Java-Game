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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RGB rgb = (RGB) o;

        return this.isEqual(rgb.red, rgb.green, rgb.blue);
    }

    @Override
    public int hashCode() {
        int rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
        return rgb;
    }

    @Override
    public String toString() {
        return "RGB{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    public static RGB of(int red, int green, int blue) {
        return new RGB(red, green, blue);
    }

    public static RGB from(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return of(red, green, blue);
    }
}
