package xyz.booldy.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.booldy.CheckPlugin;
import xyz.booldy.util.ChatUtil;

public class ImHackingCmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if (CheckPlugin.ss.containsKey(p.getName())) {
            Bukkit.broadcastMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("ImHackingMessage").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX")).replace("%PLAYER%", p.getDisplayName())));
            p.sendTitle("","");
            CheckPlugin.ss.remove(p.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), CheckPlugin.getInstance().getConfig().getString("ImHackingCmd").replace("%PLAYER%", p.getName()));
        } else {
            p.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("UrNotFrozenMessage").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX"))));
        }
        return false;
    }
}

