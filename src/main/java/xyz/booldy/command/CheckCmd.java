package xyz.booldy.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.booldy.CheckPlugin;
import xyz.booldy.util.ChatUtil;

public class CheckCmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("ss.use")) {
            sender.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("NoPermission").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX"))));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("CheckUsage").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX"))));
            return true;
        }
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("PlayerOffline").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX")).replace("%PLAYER%", args[0])));
            return true;
        }
        if (target == sender) {
            sender.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("FrozenErrorMessage").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX"))));
            return true;
        }
        if (CheckPlugin.ss.containsKey(target.getName())) {
            CheckPlugin.ss.remove(target.getName());
            target.sendTitle("", "");
            sender.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("UnFrozenMessage").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX")).replace("%PLAYER%", target.getName())));
            return true;
        }
        CheckPlugin.ss.put(target.getName(), sender.getName());
        sender.sendMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("FrozenMessage").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX")).replace("%PLAYER%", target.getName())));
        return false;
    }
}

