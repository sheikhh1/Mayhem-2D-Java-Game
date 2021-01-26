package me.mayhem.game.attribute;

import me.mayhem.game.attribute.type.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AttributeFactory {

    private static final List<AttributeTypeData> REGISTERED_ATTRIBUTES = new ArrayList<>();

    static {
        registerAttribute(BooleanAttribute.class);
        registerAttribute(DoubleAttribute.class);
        registerAttribute(FloatAttribute.class);
        registerAttribute(IntegerAttribute.class);
        registerAttribute(StringAttribute.class);
        registerAttribute(LongAttribute.class);
    }

    private static void registerAttribute(Class<? extends Attribute<?>> clazz) {
        Constructor<? extends Attribute<?>> constructor = getConstructor(clazz);

        if (constructor == null) {
            return;
        }

        REGISTERED_ATTRIBUTES.add(new AttributeTypeData(constructor, clazz, constructor.getParameterTypes()[1]));
    }

    private static Constructor<? extends Attribute<?>> getConstructor(Class<? extends Attribute<?>> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterTypes().length == 2 &&
                    constructor.getParameterTypes()[0] == String.class) {
                return (Constructor<? extends Attribute<?>>) constructor;
            }
        }

        return null;
    }

    public static Attribute<?> from(String identifier, Object object) {
        for (AttributeTypeData registeredAttribute : REGISTERED_ATTRIBUTES) {
            if (Objects.equals(registeredAttribute.getAttributeType(), object.getClass())) {
                try {
                    return registeredAttribute.getConstructor().newInstance(identifier, object);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private static final class AttributeTypeData {

        private final Constructor<? extends Attribute<?>> constructor;
        private final Class<? extends Attribute<?>> clazz;
        private final Class<?> attributeType;

        AttributeTypeData(Constructor<? extends Attribute<?>> constructor, Class<? extends Attribute<?>> clazz, Class<?> attributeType) {
            this.constructor = constructor;
            this.clazz = clazz;
            this.attributeType = attributeType;
        }

        public Constructor<? extends Attribute<?>> getConstructor() {
            return this.constructor;
        }

        public Class<? extends Attribute<?>> getClazz() {
            return this.clazz;
        }

        public Class<?> getAttributeType() {
            return this.attributeType;
        }
    }
}
