package me.almana.almanapvp;

import me.almana.almanapvp.BukkitRunners.JsonRunner;
import me.almana.almanapvp.Commands.adminCommands.GetUpgradesCommand;
import me.almana.almanapvp.Commands.debugCommands.Base64ToItemCommand;
import me.almana.almanapvp.Commands.debugCommands.ItemToBase64Command;
import me.almana.almanapvp.Commands.adminCommands.SaveKitCommand;
import me.almana.almanapvp.Listeners.JoinLeaveListeners;
import me.almana.almanapvp.Listeners.KillListener;
import me.almana.almanapvp.utils.PlayerInfoJSONUtils;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class AlmanaPVP extends JavaPlugin {

    LuckPerms lp;
    public static AlmanaPVP plugin;
    JsonRunner jr = new JsonRunner();

    @Override
    public void onEnable() {

        plugin = this;

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {

            lp = provider.getProvider();
            plugin.getLogger().info("Luckperms Connected.");
        }

        Bukkit.getServer().getPluginManager().registerEvents(new JoinLeaveListeners(), this);
        try {
            Bukkit.getServer().getPluginManager().registerEvents(new KillListener(), this);
            Bukkit.getPluginCommand("getupgrades").setExecutor(new GetUpgradesCommand());

            File file = new File(getDataFolder().getAbsolutePath() + "/playerInfo.json");
            file.getParentFile().mkdir();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            PlayerInfoJSONUtils.loadPlayerInfo();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.getLogger().info("[AlmanaPVP]:- Loaded Player Info");

        Bukkit.getPluginCommand("savekit").setExecutor(new SaveKitCommand());
        Bukkit.getPluginCommand("itemtobase64").setExecutor(new ItemToBase64Command());
        Bukkit.getPluginCommand("base64toitem").setExecutor(new Base64ToItemCommand());
        jr.runTaskTimerAsynchronously(this, 3600L, 1L);
        this.getLogger().info("Plugin Enabled.");
    }

    public LuckPerms getLp() {
        return lp;
    }

    public static AlmanaPVP getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {

    }
}
