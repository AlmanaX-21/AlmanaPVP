package me.almana.almanapvp.utils;

import com.google.gson.Gson;
import me.almana.almanapvp.AlmanaPVP;
import me.almana.almanapvp.models.PlayerInfo;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.HashMap;

public class PlayerInfoJSONUtils {

    private static HashMap<String, PlayerInfo> playerInfos = new HashMap<>();

    public static HashMap<String, PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }

    public static PlayerInfo createPlayerInfo(String UUID, String skit, String bkit) {

        PlayerInfo playerInfo = new PlayerInfo(UUID, skit, bkit);
        playerInfos.put(UUID, playerInfo);
        return playerInfo;
    }

    public static void deletePlayerInfo(String UUID) {

        if (playerInfos.containsKey(UUID)) {

            playerInfos.remove(UUID);
        }
    }

    public static PlayerInfo findPlayerInfo(String UUID) {

        if (playerInfos.containsKey(UUID)) {

            return playerInfos.get(UUID);
        }
        return null;
    }

    public static PlayerInfo updatePlayerInfo(String UUID, PlayerInfo newPlayerInfo) {

        if (playerInfos.containsKey(UUID)) {

            newPlayerInfo.setPlayerUUID(UUID);
            newPlayerInfo.setPlayerSwordKit(newPlayerInfo.getPlayerSwordKit());
            newPlayerInfo.setPlayerBowKit(newPlayerInfo.getPlayerBowKit());
            return newPlayerInfo;
        } else return null;
    }

    public static void savePlayerInfo() throws IOException {

        Gson gson = new Gson();
        File file = new File(AlmanaPVP.getPlugin().getDataFolder().getAbsolutePath() + "/playerInfo.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(playerInfos, writer);
        writer.flush();
        writer.close();
        AlmanaPVP.getPlugin().getServer().getLogger().info("[AlmanaPVP]:- Saved Player info.");
    }

    public static void loadPlayerInfo() throws FileNotFoundException {

        Gson gson = new Gson();
        File file = new File(AlmanaPVP.getPlugin().getDataFolder().getAbsolutePath() + "/playerInfo.json");
        if (file.exists()) {

            Reader reader = new FileReader(file);
            PlayerInfo[] p = gson.fromJson(reader, PlayerInfo[].class);
            playerInfos = new HashMap<String, PlayerInfo>();
            Bukkit.getLogger().info("[AlmanaPVP]:- Loaded Player Info.");
        }
    }
}
