package me.almana.almanapvp.Commands.adminCommands;

import me.almana.almanapvp.AlmanaPVP;
import me.almana.almanapvp.utils.Base64Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveKitCommand implements TabExecutor {

    private final Plugin plugin = AlmanaPVP.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player p) {

            if (args.length == 1) {

                List<ItemStack> items = Arrays.stream(p.getInventory().getContents()).toList();
                try {
                    String data = Base64Utils.itemListToBase64(items);
                    plugin.getConfig().set(args[0], data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        List<String> tabs = new ArrayList<>();

        tabs.add("SWORD_KIT");
        tabs.add("BOW_KIT");
        //  Tank Kit
        //  Ninja Kit
        //  Archer Kit
        //  Goblin Kit
        //  Pro Kit
        return tabs;
    }
}
