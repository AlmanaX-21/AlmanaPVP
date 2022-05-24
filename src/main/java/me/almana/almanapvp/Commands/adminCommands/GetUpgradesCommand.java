package me.almana.almanapvp.Commands.adminCommands;

import me.almana.almanapvp.AlmanaPVP;
import me.almana.almanapvp.Listeners.KillListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetUpgradesCommand implements TabExecutor {

    private final Plugin plugin = AlmanaPVP.getPlugin();
    KillListener kl = new KillListener();
    private List<ItemStack> swords = kl.getSwords();
    private List<ItemStack> bows = kl.getBows();
    private List<ItemStack> helmets = kl.getHelmets();
    private List<ItemStack> chests = kl.getChests();
    private List<ItemStack> legs = kl.getLegs();
    private List<ItemStack> boots = kl.getBoots();


    public GetUpgradesCommand() throws IOException, ClassNotFoundException {
    }

    private void upgradeInventory(Player p, String name, List<ItemStack> items) {

        TextComponent component = Component.text(name + " Upgrades");
        Inventory inv = Bukkit.createInventory(p, 27, component);

        for (ItemStack item: items) {

            inv.addItem(item);
        }
        p.openInventory(inv);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (args.length == 1) {

                if (args[0].equalsIgnoreCase("swords")) {

                    upgradeInventory(player, "Swords", swords);
                } else if (args[0].equalsIgnoreCase("bows")) {

                    upgradeInventory(player, "Bows", bows);
                } else if (args[0].equalsIgnoreCase("helmets")) {

                    upgradeInventory(player, "Helmet", helmets);
                } else if (args[0].equalsIgnoreCase("chestplate")) {

                    upgradeInventory(player, "Chestplate", chests);
                } else if (args[0].equalsIgnoreCase("legs")) {

                    upgradeInventory(player, "Legging", legs);
                } else if (args[0].equalsIgnoreCase("boots")) {

                    upgradeInventory(player, "Boot", boots);
                }
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        List<String> tabs = new ArrayList<>();

        tabs.add("swords");
        tabs.add("bows");
        tabs.add("helmets");
        tabs.add("chestplate");
        tabs.add("legs");
        tabs.add("boots");

        return tabs;
    }
}
