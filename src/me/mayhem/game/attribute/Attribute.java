package me.mayhem.game.attribute;

public interface Attribute<T> {

    String getIdentifier();

    T getValue();

    void setValue(T t);

}
