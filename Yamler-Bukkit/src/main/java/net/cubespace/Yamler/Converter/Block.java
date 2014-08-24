package net.cubespace.Yamler.Converter;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.InternalConverter;
import net.cubespace.Yamler.Config.Converter.Converter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Block implements Converter {
    private final InternalConverter converter;

    public Block(InternalConverter converter) {
        this.converter = converter;
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
        final org.bukkit.block.Block block = (org.bukkit.block.Block) obj;

        final Converter locationConverter = converter.getConverter(org.bukkit.Location.class);
        final Map<String, Object> saveMap = new HashMap<>();
        saveMap.put("id", block.getType() + ((block.getData() > 0) ? ":" + block.getData() : ""));
        saveMap.put("location", locationConverter.toConfig(org.bukkit.Location.class, block.getLocation(), null));

        return saveMap;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        final Map<String, Object> blockMap = ((ConfigSection) section).getRawMap();
        final Map<String, Object> locationMap = ((ConfigSection) blockMap.get("location")).getRawMap();

        final Location location = new org.bukkit.Location(Bukkit.getWorld((String) locationMap.get("world")),
                (Double) locationMap.get("x"), (Double) locationMap.get("y"), (Double) locationMap.get("z"));
        final org.bukkit.block.Block block = location.getBlock();

        final String[] temp = ((String) blockMap.get("id")).split(":");
        block.setType(Material.valueOf(temp[0]));

        if (temp.length == 2) {
            block.setData(Byte.valueOf(temp[1]));
        }

        return block;
    }

    @Override
    public boolean supports(Class<?> type) {
        return org.bukkit.block.Block.class.isAssignableFrom(type);
    }

}
