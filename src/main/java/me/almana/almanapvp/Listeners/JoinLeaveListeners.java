package me.almana.almanapvp.Listeners;

import me.almana.almanapvp.AlmanaPVP;
import me.almana.almanapvp.utils.PlayerInfoJSONUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinLeaveListeners implements Listener {

    private final Plugin plugin = AlmanaPVP.getPlugin();
    String starterSwordKit = plugin.getConfig().getString("SWORD_KIT");
    String starterBowKit = plugin.getConfig().getString("BOW_KIT");

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (PlayerInfoJSONUtils.findPlayerInfo(p.getUniqueId().toString()) == null) {

            PlayerInfoJSONUtils.createPlayerInfo(p.getUniqueId().toString(), starterSwordKit, starterBowKit);
            plugin.getLogger().info(p.getName() + "'s info was created.");
        }
    }
}
