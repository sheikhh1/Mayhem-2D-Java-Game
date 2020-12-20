package me.mayhem.game.attribute;

public abstract class AbstractAttribute<T> implements Attribute<T> {

    private final String identifier;
    private T value;

    public AbstractAttribute(String identifier, T value) {
        this.identifier = identifier;
        this.value = value;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
