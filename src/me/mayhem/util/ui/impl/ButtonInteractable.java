package me.mayhem.util.ui.impl;

import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilFont;
import me.mayhem.util.ui.AbstractMouseInteractable;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * An abstract implementation of the {@link me.mayhem.util.ui.Interactable} interface and the {@link me.mayhem.util.ui.AbstractMouseInteractable}
 * that handles when the button is clicked by checking the click is in the area and passing it down to the concrete
 * implementation of the class
 *
 * It handles centering the text both vertically and horizontally
 *
 */
public abstract class ButtonInteractable extends AbstractMouseInteractable {

    private Text[] text;
    private final Font font;

    /**
     * Second constructor that converts the specified string of the font to the JSFML {@link Font}
     *
     * @param shape The shape background
     * @param font  The font to be converted
     * @param text  The text to be written
     */
    public ButtonInteractable(Shape shape, String font, String... text) {
        this(shape, UtilFont.loadFont(font), text);
    }

    /**
     * Default constructor that takes a JSFML {@link Shape} as the background image and hitbox. It takes a font to display
     * the text in and a vararg of text
     *
     * @param shape The background shape
     * @param font  The font to be written in
     * @param text  The text to be written
     */
    public ButtonInteractable(Shape shape, Font font, String... text) {
        super(shape);

        this.font = font;
        this.text = this.createText(text);
    }

    /**
     * Creates an {@link Text} array cache of the written text with the specified {@link Font}
     *
     * @param text The strings to be converted to the {@link Text}
     * @return the {@link Text} cache
     */
    private Text[] createText(String[] text) {
        List<Text> lines = new ArrayList<>();
        int yAxisDifferential = (int) (super.shape.getLocalBounds().height / (text.length + 1));
        int yAxisIncrement = yAxisDifferential;

        for (String textLine : text) {
            Text line = new Text(textLine, this.font);

            line.setColor(Color.BLACK);

            float x = super.shape.getPosition().x + (super.shape.getLocalBounds().width / 2f) - (line.getLocalBounds().width / 2f);
            float y = super.shape.getPosition().y + yAxisDifferential - (line.getLocalBounds().height);

            line.setPosition(new Vector2f(x, y));

            yAxisDifferential += yAxisIncrement;
            lines.add(line);
        }

        return lines.toArray(new Text[0]);
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        renderWindow.draw(super.shape);

        for (Text textLine : this.text) {
            renderWindow.draw(textLine);
        }
    }

    @Override
    public void setPosition(Vector vector) {
        this.shape.setPosition(vector.toVector());
    }

    /**
     *
     * Updated the written text to the specified value
     *
     * @param text the new written text
     */
    public void setText(String... text) {
        this.text = this.createText(text);
    }
}

