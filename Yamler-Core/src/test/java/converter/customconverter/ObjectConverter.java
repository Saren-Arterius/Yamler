package converter.customconverter;

import java.lang.reflect.ParameterizedType;

import net.cubespace.Yamler.Config.InternalConverter;
import net.cubespace.Yamler.Config.Converter.Converter;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ObjectConverter implements Converter {
    public ObjectConverter(InternalConverter internalConverter) {

    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) throws Exception {
        return obj;
    }

    @Override
    public Object fromConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) throws Exception {
        return obj;
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
}
