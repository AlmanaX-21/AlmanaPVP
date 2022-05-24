package me.almana.almanapvp.Listeners;

import me.almana.almanapvp.AlmanaPVP;
import me.almana.almanapvp.models.PlayerInfo;
import me.almana.almanapvp.utils.Base64Utils;
import me.almana.almanapvp.utils.PlayerInfoJSONUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class KillListener implements Listener {

    private final Plugin plugin = AlmanaPVP.getPlugin();
    private HashMap<String, PlayerInfo> playerInfos = PlayerInfoJSONUtils.getPlayerInfos();
    List<ItemStack> swords = Base64Utils.itemListFromBase64(plugin.getConfig().getString("SWORD_LIST"));
    List<ItemStack> bows = Base64Utils.itemListFromBase64(plugin.getConfig().getString("BOW_LIST"));
    List<ItemStack> helmets = Base64Utils.itemListFromBase64(plugin.getConfig().getString("HELMET_LIST"));
    List<ItemStack> chests = Base64Utils.itemListFromBase64(plugin.getConfig().getString("CHEST_LIST"));
    List<ItemStack> legs = Base64Utils.itemListFromBase64(plugin.getConfig().getString("LEG_LIST"));
    List<ItemStack> boots = Base64Utils.itemListFromBase64(plugin.getConfig().getString("BOOT_LIST"));


    public KillListener() throws IOException, ClassNotFoundException {
    }

    private void upgradeItem(Player p, List<ItemStack> items, ItemStack item) {

        int pres = items.indexOf(item);
        p.getInventory().remove(item);
        p.getInventory().setItemInMainHand(items.get(pres + 1));
    }

    @EventHandler
    public void onPlayerKill(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player victim && e.getDamager() instanceof Player killer) {
            if (victim.isDead()) {

                Random random = new Random();
                int chance = random.nextInt(100);
                killer.setHealth(killer.getHealth() + 4);
                int level = (int) Math.round(killer.getLevel() * 7.5);
                int addedXp = random.nextInt(level);
                killer.giveExp(addedXp);

                TextComponent t = Component.text("You gained ", NamedTextColor.GREEN)
                                .append(Component.text(addedXp).color(NamedTextColor.GREEN))
                                        .append(Component.text(" xp").color(NamedTextColor.GREEN));
                killer.sendActionBar(t);

                if (chance <= 20) {
                    killer.getInventory().getItemInMainHand();
                    if (killer.getInventory().getItemInMainHand().equals(Material.BOW)){

                        upgradeItem(killer, bows, killer.getInventory().getItemInMainHand());
                        TextComponent component = Component.text("Your bow was upgraded to tier " + bows.indexOf(killer.getInventory().getItemInMainHand())
                                , NamedTextColor.GREEN);
                        killer.sendMessage(component);
                    } else {

                        upgradeItem(killer, swords, killer.getInventory().getItemInMainHand());
                        TextComponent component = Component.text("Your sword was upgraded to tier " + swords.indexOf(killer.getInventory().getItemInMainHand())
                                , NamedTextColor.GREEN);
                        killer.sendMessage(component);
                    }
                } else if (chance <= 40) {

                    upgradeItem(killer, helmets, killer.getInventory().getHelmet());
                    TextComponent component = Component.text("Your helmet was upgraded to tier " + helmets.indexOf(killer.getInventory().getHelmet())
                            , NamedTextColor.GREEN);
                    killer.sendMessage(component);
                } else if (chance <= 60) {

                    upgradeItem(killer, chests, killer.getInventory().getChestplate());
                    TextComponent component = Component.text("Your chestplate was upgraded to tier " + chests.indexOf(killer.getInventory().getChestplate())
                            , NamedTextColor.GREEN);
                    killer.sendMessage(component);
                } else if (chance <= 80) {

                    upgradeItem(killer, legs, killer.getInventory().getLeggings());
                    TextComponent component = Component.text("Your legging was upgraded to tier " + legs.indexOf(killer.getInventory().getLeggings())
                            , NamedTextColor.GREEN);
                    killer.sendMessage(component);
                } else {

                    upgradeItem(killer, boots, killer.getInventory().getBoots());
                    TextComponent component = Component.text("Your boot was upgraded to tier " + boots.indexOf(killer.getInventory().getBoots())
                            , NamedTextColor.GREEN);
                    killer.sendMessage(component);
                }
            }
        }
    }

    @EventHandler
    public void deathMessage(PlayerDeathEvent e) {

        List<String> deathMessages = plugin.getConfig().getStringList("DEATH_MESSAGES");
        Random r = new Random();
        Component deathMessage = Component.text(deathMessages.get(r.nextInt(deathMessages.size())));

        Player victim = e.getPlayer();
        TextComponent component = Component.text(victim.getName(), NamedTextColor.RED)
                .append(deathMessage).append(e.getEntity().getKiller().displayName()).color(NamedTextColor.GREEN);
        e.deathMessage(component);
    }

    public List<ItemStack> getSwords() {
        return swords;
    }

    public List<ItemStack> getBows() {
        return bows;
    }

    public List<ItemStack> getHelmets() {
        return helmets;
    }

    public List<ItemStack> getChests() {
        return chests;
    }

    public List<ItemStack> getLegs() {
        return legs;
    }

    public List<ItemStack> getBoots() {
        return boots;
    }
}
