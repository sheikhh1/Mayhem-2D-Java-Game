package me.mayhem.util.ui.impl;

import me.mayhem.util.file.UtilFont;
import me.mayhem.util.ui.AbstractMouseInteractable;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.List;

public abstract class ButtonInteractable extends AbstractMouseInteractable {

    private final Text[] text;
    private final Font font;

    public ButtonInteractable(Shape shape, Font font, String... text) {
        super(shape);

        this.font = font;
        this.text = this.createText(text);
    }

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

    public ButtonInteractable(Shape shape, String font, String... text) {
        this(shape, UtilFont.loadFont(font), text);
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        renderWindow.draw(super.shape);

        for (Text textLine : this.text) {
            renderWindow.draw(textLine);
        }
    }
}
