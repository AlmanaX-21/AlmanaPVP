package me.almana.almanapvp.models;

public class PlayerInfo {

    private String playerUUID;
    private String playerSwordKit;
    private String playerBowKit;

    public PlayerInfo(String playerUUID,  String playerSwordKit, String playerBowKit) {
        this.playerUUID = playerUUID;
        this.playerSwordKit = playerSwordKit;
        this.playerBowKit = playerBowKit;
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(String playerUUID) {
        this.playerUUID = playerUUID;
    }

    public String getPlayerSwordKit() {
        return playerSwordKit;
    }

    public void setPlayerSwordKit(String playerSwordKit) {
        this.playerSwordKit = playerSwordKit;
    }

    public String getPlayerBowKit() {
        return playerBowKit;
    }

    public void setPlayerBowKit(String playerBowKit) {
        this.playerBowKit = playerBowKit;
    }
}
