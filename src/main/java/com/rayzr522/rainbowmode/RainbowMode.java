package com.rayzr522.rainbowmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RainbowMode extends JavaPlugin implements Listener {
    private char[] colors     = new char[] { 'c', '6', 'e', 'a', 'b', '5', 'd' };
    private int    lastOffset = 0;
    boolean        enabled    = false;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.broadcastMessage(makeRainbow("RAINBOW") + " mode " + makeRainbow((enabled = !enabled) ? "enabled" : "disabled") + "!");
        if (!enabled)
            lastOffset = 0;
        return true;
    }

    private String makeRainbow(String input) {
        StringBuilder out = new StringBuilder();
        int offset = lastOffset;
        for (char c : input.toCharArray()) {
            out.append(ChatColor.COLOR_CHAR).append(colors[offset]).append(c);
            offset = (offset + 1) % colors.length;
        }
        lastOffset = offset;
        return out.append(ChatColor.RESET).toString();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (enabled)
            e.setMessage(makeRainbow(e.getMessage()));
    }
}
