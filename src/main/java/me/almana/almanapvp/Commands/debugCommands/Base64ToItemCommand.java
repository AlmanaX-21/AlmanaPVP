package me.almana.almanapvp.Commands.debugCommands;

import me.almana.almanapvp.utils.Base64Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Base64ToItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (args.length > 0) {

                ItemStack item = null;
                try {
                    item = Base64Utils.itemFromBase64(args[0]);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (item == null) {

                    TextComponent error = Component.text("That did not work.").color(NamedTextColor.RED);
                    player.sendMessage(error);
                } else {

                    TextComponent success = Component.text("That did work.").color(NamedTextColor.GREEN);
                    player.sendMessage(success);
                    player.getInventory().addItem(item);
                }
            }
        }
        return true;
    }
}
