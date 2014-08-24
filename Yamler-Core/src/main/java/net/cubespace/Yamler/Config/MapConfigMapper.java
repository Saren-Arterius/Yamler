package net.cubespace.Yamler.Config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import net.cubespace.Yamler.Config.Converter.Converter;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class MapConfigMapper extends YamlConfigMapper {
    public Map saveToMap() throws Exception {
        final Map<String, Object> returnMap = new HashMap<>();

        for (final Field field: getClass().getDeclaredFields()) {
            if (doSkip(field)) {
                continue;
            }

            String path = (CONFIG_MODE.equals(ConfigMode.DEFAULT)) ? field.getName().replaceAll("_", ".") : field
                    .getName();

            if (field.isAnnotationPresent(Path.class)) {
                final Path path1 = field.getAnnotation(Path.class);
                path = path1.value();
            }

            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }

            try {
                returnMap.put(path, field.get(this));
            } catch (final IllegalAccessException e) {}
        }

        final Converter mapConverter = converter.getConverter(Map.class);
        return (Map) mapConverter.toConfig(HashMap.class, returnMap, null);
    }

    public void loadFromMap(Map section) throws Exception {
        for (final Field field: getClass().getDeclaredFields()) {
            if (doSkip(field)) {
                continue;
            }

            String path = (CONFIG_MODE.equals(ConfigMode.DEFAULT)) ? field.getName().replaceAll("_", ".") : field
                    .getName();

            if (field.isAnnotationPresent(Path.class)) {
                final Path path1 = field.getAnnotation(Path.class);
                path = path1.value();
            }

            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }

            converter.fromConfig((Config) this, field, ConfigSection.convertFromMap(section), path);
        }
    }
}
