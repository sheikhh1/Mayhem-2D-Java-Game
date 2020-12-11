package me.mayhem.entity.attribute;

public interface Attribute<T> {

    T getValue();

    void setValue(T t);

}
