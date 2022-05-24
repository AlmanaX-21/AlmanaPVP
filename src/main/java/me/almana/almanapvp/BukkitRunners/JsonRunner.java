package me.almana.almanapvp.BukkitRunners;

import me.almana.almanapvp.AlmanaPVP;
import me.almana.almanapvp.utils.PlayerInfoJSONUtils;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class JsonRunner extends BukkitRunnable {

    private final Plugin plugin = AlmanaPVP.getPlugin();

    @Override
    public void run() {

        try {
            PlayerInfoJSONUtils.savePlayerInfo();
            plugin.getLogger().info("All player info was saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
