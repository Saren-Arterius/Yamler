package net.cubespace.Yamler.Config.Converter;

import net.cubespace.Yamler.Config.*;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Set implements Converter {
    private InternalConverter internalConverter;

    public Set(InternalConverter internalConverter) {
        this.internalConverter = internalConverter;
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
        java.util.Set<Object> values = (java.util.Set<Object>) obj;
        java.util.List newList = new ArrayList();

        Iterator<Object> iterator = values.iterator();
        while(iterator.hasNext()) {
            Object val = iterator.next();

            Converter converter = internalConverter.getConverter(val.getClass());

            if (converter != null)
                newList.add(converter.toConfig(val.getClass(), val, null));
            else
                newList.add(val);
        }

        return newList;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        java.util.List<Object> values = (java.util.List<Object>) section;
        java.util.Set<Object> newList = new HashSet<>();

        try {
            newList = (java.util.Set<Object>) type.newInstance();
        } catch (Exception e) { }

        for (Object val : values) {
            Converter converter = internalConverter.getConverter(val.getClass());

            if (converter != null)
                newList.add(converter.toConfig(val.getClass(), val, null));
            else
                newList.add(val);
        }

        return newList;
    }

    @Override
    public boolean supports(Class<?> type) {
        return java.util.Set.class.isAssignableFrom(type);
    }

}
