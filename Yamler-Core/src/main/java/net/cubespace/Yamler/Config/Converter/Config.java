package net.cubespace.Yamler.Config.Converter;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.InternalConverter;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Config implements Converter {
    public Config(InternalConverter internalConverter) {
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) throws Exception {
        return (obj instanceof Map) ? obj : ((net.cubespace.Yamler.Config.Config) obj).saveToMap();
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        final net.cubespace.Yamler.Config.Config obj = (net.cubespace.Yamler.Config.Config) newInstance(type);
        obj.loadFromMap((section instanceof Map) ? (Map) section : ((ConfigSection) section).getRawMap());
        return obj;
    }

    // recursively handles enclosed classes
    public Object newInstance(Class type) throws Exception {
        final Class enclosingClass = type.getEnclosingClass();
        if (enclosingClass != null) {
            final Object instanceOfEnclosingClass = newInstance(enclosingClass);
            return type.getConstructor(enclosingClass).newInstance(instanceOfEnclosingClass);
        } else {
            return type.newInstance();
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return net.cubespace.Yamler.Config.Config.class.isAssignableFrom(type);
    }
}
