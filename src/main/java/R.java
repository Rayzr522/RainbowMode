import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class R extends JavaPlugin implements Listener {
    List<Player> p = new ArrayList<>();
    char[] colors = new char[]{'c', '6', 'e', 'a', 'b', '5', 'd'};
    int offset = 0;
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }
    private String m(String i) {
        return Arrays.stream(i.split("")).map(c -> "\u00a7" + colors[offset = ++offset % colors.length] + c).collect(Collectors.joining());
    }
    @EventHandler void m(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().contains("rainbowmode") && !p.remove(e.getPlayer())) p.add(e.getPlayer());
    }
    @EventHandler void c(AsyncPlayerChatEvent e) {
        if (p.contains(e.getPlayer())) e.setMessage(m(e.getMessage()));
    }
}
