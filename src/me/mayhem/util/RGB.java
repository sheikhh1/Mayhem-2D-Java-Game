package me.mayhem.util;

/**
 *
 * A Data Transfer Object (DTO) for storing RGB (Red, Green Blue) colours and comparing them more easily
 *
 */
public class RGB {

    private final int red;
    private final int green;
    private final int blue;

    /**
     * Protected constructor as to only pass them through the static factory methods {@link RGB#of(int, int, int)}
     * and {@link RGB#from(int)}
     *
     * @param red - The value of the red for the pixel
     * @param green - the value of the green for the pixel
     * @param blue - the value of the blue for the pixel
     */
    protected RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     *
     * The red value of the pixel
     *
     * @return The red value
     */
    public int getRed() {
        return this.red;
    }

    /**
     *
     * The green value of the pixel
     *
     * @return The green value
     */
    public int getGreen() {
        return this.green;
    }

    /**
     *
     * The blue value of the pixel
     *
     * @return The blue value
     */
    public int getBlue() {
        return this.blue;
    }

    /**
     *
     * Checks if the red, green, and blue values passed are the same as the values stored in this RGB DTO
     *
     * @param red The red to be checked
     * @param green The green to be checked
     * @param blue The blue to be checked
     * @return if they're equal to this DTO
     */
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

    /**
     *
     * Created an RGB DTO with the values specified
     *
     * @param red The red value to be stored
     * @param green The green value to be stored
     * @param blue The blue value to be stored
     * @return The RGB object created
     */
    public static RGB of(int red, int green, int blue) {
        return new RGB(red, green, blue);
    }

    /**
     *
     * Creates an RGB DTO from the pixel value given using bit shifting and logical ANDs
     *
     * @param pixel The pixel value
     * @return The RGB DTO created
     */
    public static RGB from(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return of(red, green, blue);
    }
}
