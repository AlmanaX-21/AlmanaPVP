package me.almana.almanapvp.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {

    public static ItemStack createItem(Material item, String name, List<String> lores, int amount) {

        ItemStack itemStack = new ItemStack(item, amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lores);

        return itemStack;
    }

    public static ItemStack createItem(Material item, String name, List<String> lores, int amount, Boolean unbreakable) {

        ItemStack itemStack = new ItemStack(item, amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lores);
        meta.setUnbreakable(unbreakable);

        return itemStack;
    }
}
