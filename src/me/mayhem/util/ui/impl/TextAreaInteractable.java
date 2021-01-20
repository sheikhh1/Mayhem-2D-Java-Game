package me.mayhem.util.ui.impl;

import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilFont;
import me.mayhem.util.ui.AbstractKeyboardMouseInteractable;
import org.jsfml.graphics.*;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;

public abstract class TextAreaInteractable extends AbstractKeyboardMouseInteractable {

    private final Font font;
    private Vector position;
    private Text writtenText;
    private String written = "";
    private boolean locked = false;
    private boolean heightUpdated = false;

    public TextAreaInteractable(Shape shape, Font font) {
        super(shape);

        this.font = font;
        this.position = new Vector(shape.getPosition().x + 20,
                (shape.getPosition().y + shape.getGlobalBounds().height / 2f));
    }

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

        if (event.key.name().length() > 1 || locked) {
            return;
        }

        if (event.shift) {
            this.written += event.key.name();
        } else {
            this.written += event.key.name().toLowerCase();
        }

        this.updateText();
    }

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

    @Override
    public void draw(RenderWindow renderWindow) {
        renderWindow.draw(super.shape);

        if (this.writtenText != null) {
            renderWindow.draw(this.writtenText);
        }
    }
}
