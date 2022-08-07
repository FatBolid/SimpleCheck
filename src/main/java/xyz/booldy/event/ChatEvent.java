package xyz.booldy.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import xyz.booldy.CheckPlugin;
import xyz.booldy.util.ChatUtil;

public class ChatEvent implements Listener {

    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("PlayerMessageAdminOnly")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                String msg = e.getMessage();
                for (Player ops : Bukkit.getOnlinePlayers()) {
                    if (ops.isOp()) {
                        ops.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("MessageByFrozenPlayerToAdmins").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX")).replace("%PLAYER%", p.getDisplayName()).replace("%MESSAGE%", msg)));
                        p.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("MessageByFrozenPlayerToAdmins").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX")).replace("%PLAYER%", p.getDisplayName()).replace("%MESSAGE%", msg)));
                    }
                    e.setCancelled(true);
                }
            }
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void SSCommandBlockEvent(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerCommands")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                if (!CheckPlugin.getInstance().getConfig().getList("SSAllowedCommands").contains(e.getMessage())) {
                    e.setCancelled(true);
                    p.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("NoPermission").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX"))));
                } else {
                    return;
                }
            }
        } else {
            e.setCancelled(false);
        }
    }
}
