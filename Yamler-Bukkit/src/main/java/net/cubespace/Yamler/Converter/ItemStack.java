package net.cubespace.Yamler.Converter;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.InternalConverter;
import net.cubespace.Yamler.Config.Converter.Converter;

import org.bukkit.Material;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ItemStack implements Converter {
    private final InternalConverter converter;

    public ItemStack(InternalConverter converter) {
        this.converter = converter;
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
        final org.bukkit.inventory.ItemStack itemStack = (org.bukkit.inventory.ItemStack) obj;

        final Map<String, Object> saveMap = new HashMap<>();
        saveMap.put("id", itemStack.getType()
                + ((itemStack.getDurability() > 0) ? ":" + itemStack.getDurability() : ""));
        saveMap.put("amount", itemStack.getAmount());

        final Converter listConverter = converter.getConverter(List.class);

        final Map<String, Object> meta = new HashMap<>();
        meta.put("name", itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : null);
        meta.put(
                "lore",
                itemStack.getItemMeta().hasLore() ? listConverter.toConfig(List.class, itemStack.getItemMeta()
                        .getLore(), null) : null);

        saveMap.put("meta", meta);

        return saveMap;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        final Map<String, Object> itemstackMap = ((ConfigSection) section).getRawMap();
        final Map<String, Object> metaMap = ((ConfigSection) itemstackMap.get("meta")).getRawMap();

        final String[] temp = ((String) itemstackMap.get("id")).split(":");
        final org.bukkit.inventory.ItemStack itemStack = new org.bukkit.inventory.ItemStack(Material.valueOf(temp[0]));
        itemStack.setAmount((int) itemstackMap.get("amount"));

        if (temp.length == 2) {
            itemStack.setDurability(Short.valueOf(temp[1]));
        }

        if (metaMap.get("name") != null) {
            itemStack.getItemMeta().setDisplayName((String) metaMap.get("name"));
        }

        if (metaMap.get("lore") != null) {
            final Converter listConverter = converter.getConverter(List.class);
            itemStack.getItemMeta().setLore(
                    (List<String>) listConverter.fromConfig(List.class, metaMap.get("lore"), null));
        }

        return itemStack;
    }

    @Override
    public boolean supports(Class<?> type) {
        return org.bukkit.inventory.ItemStack.class.isAssignableFrom(type);
    }

}
