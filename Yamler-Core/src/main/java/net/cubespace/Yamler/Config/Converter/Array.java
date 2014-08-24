package net.cubespace.Yamler.Config.Converter;

import java.lang.reflect.ParameterizedType;

import net.cubespace.Yamler.Config.InternalConverter;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Array implements Converter {
    public Array(InternalConverter internalConverter) {
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) throws Exception {
        return obj;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        final java.util.List values = (java.util.List) section;
        return Array.getArray(type, values);
    }

    private static <T> T[] getArray(Class<T> type, java.util.List list) {
        final T[] array = (T[]) java.lang.reflect.Array.newInstance(type, list.size());
        return (T[]) list.toArray(array);
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.isArray();
    }
}
