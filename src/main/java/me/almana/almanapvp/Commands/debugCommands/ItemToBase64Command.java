package me.almana.almanapvp.Commands.debugCommands;

import me.almana.almanapvp.utils.Base64Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ItemToBase64Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (player.getInventory().getItemInMainHand().equals(new ItemStack(Material.AIR))) {

                String base64 = null;
                try {
                    base64 = Base64Utils.itemToBase64(player.getInventory().getItemInMainHand());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (base64 == null) {

                    TextComponent error = Component.text("That did not work.").color(NamedTextColor.RED);
                    player.sendMessage(error);
                } else {
                    TextComponent success = Component.text("That did work.").color(NamedTextColor.GREEN);
                    TextComponent base = Component.text(base64).clickEvent(ClickEvent.copyToClipboard(base64));
                    player.sendMessage(success);
                    player.sendMessage(base);
                }
            }
        }
        return true;
    }
}
