package me.mayhem.util.ui.impl;

import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilFont;
import me.mayhem.util.ui.AbstractKeyboardMouseInteractable;
import org.jsfml.graphics.*;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;

/**
 *
 * An abstract implementation of the {@link me.mayhem.util.ui.Interactable} interface and the {@link AbstractKeyboardMouseInteractable}
 * that checks if the area has been clicked before updating the written text on the screen.
 *
 */
public abstract class TextAreaInteractable extends AbstractKeyboardMouseInteractable {

    private final Font font;

    private Vector position;
    private Text writtenText;
    private String written = "";
    private boolean locked = false;
    private boolean heightUpdated = false;

    /**
     *
     * Creates the text interactable area with the given shape as a background and using the specified {@link Font}
     *
     * @param shape The shape for the background
     * @param font The font for the written text to appear in
     */
    public TextAreaInteractable(Shape shape, Font font) {
        super(shape);

        this.font = font;
        this.position = new Vector(shape.getPosition().x + 20,
                (shape.getPosition().y + shape.getGlobalBounds().height / 2f));
    }

    /**
     *
     * Default constructor that converts the named font into a JSFML {@link Font}
     *
     * @param shape The shape for the background
     * @param font The font for the written text to appear in
     */
    public TextAreaInteractable(Shape shape, String font) {
        this(shape, UtilFont.loadFont(font));
    }

    @Override
    protected void takeInput(KeyEvent event) {
        if (event.key == Keyboard.Key.BACKSPACE) {
            if (this.written.isEmpty()) {
                return;
            }

            this.written = this.written.substring(0, this.written.length() - 1);
            this.updateText();
            this.locked = false;
            return;
        }

        if (this.isInvalid(event.key) || locked) {
            return;
        }

        if (event.key == Keyboard.Key.SPACE) {
            this.written +=  " ";
            this.updateText();
            return;
        }

        if (event.shift) {
            this.written += event.key.name();
        } else {
            this.written += event.key.name().toLowerCase();
        }

        this.updateText();
    }

    /**
     *
     * Checks if the key pressed is a valid input (currently only allowing space and character keys)
     *
     * @param key The key being checked
     * @return If the key pressed is valid or not (false if valid, true if invalid)
     */
    private boolean isInvalid(Keyboard.Key key) {
        if (key == Keyboard.Key.SPACE) {
            return false;
        }

        return key.name().length() > 1;
    }

    /**
     *
     * Updates the cached JSFML {@link Text} object
     * This method is used as not to generate the same object every draw tick meaning memory performance is saved
     * and also processing time
     *
     */
    private void updateText() {
        this.writtenText = new Text(this.written, this.font);

        if (!this.heightUpdated) {
            this.heightUpdated = true;
            this.position.subtract(0, writtenText.getGlobalBounds().height);
        }

        this.writtenText.setPosition(this.position.toVector());
        this.writtenText.setColor(Color.BLACK);

        if (writtenText.getGlobalBounds().width >= (this.shape.getGlobalBounds().width - 40)) {
            this.locked = true;
        }
    }

    /**
     *
     * The written text as a string
     *
     * @return The text that has been written by the user
     */
    public String getWritten() {
        return this.written;
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        renderWindow.draw(super.shape);

        if (this.writtenText != null) {
            renderWindow.draw(this.writtenText);
        }
    }

    @Override
    public void setPosition(Vector position) {
        this.shape.setPosition(position.toVector());
        this.position = new Vector(shape.getPosition().x + 20,
                (shape.getPosition().y + shape.getGlobalBounds().height / 2f));
    }
}
