package me.mayhem.util.ui.impl;

import me.mayhem.util.file.UtilFont;
import me.mayhem.util.ui.AbstractKeyboardMouseInteractable;
import org.jsfml.graphics.*;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;

public abstract class TextAreaInteractable extends AbstractKeyboardMouseInteractable {

    private final Font font;
    private Text writtenText;
    private String written;

    public TextAreaInteractable(Shape shape, Font font) {
        super(shape);

        this.font = font;
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

        this.writtenText.setPosition(this.shape.getPosition());
        this.writtenText.setColor(Color.BLACK);
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        renderWindow.draw(super.shape);

        if (this.writtenText != null) {
            renderWindow.draw(this.writtenText);
        }
    }
}
