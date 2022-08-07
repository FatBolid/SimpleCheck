package xyz.booldy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.booldy.command.*;
import xyz.booldy.event.*;
import xyz.booldy.util.ChatUtil;
import xyz.booldy.util.Config;

import java.util.HashMap;

public class CheckPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        plugin = this;
        PluginDescriptionFile pdf = this.getDescription();

        //LOADING (CONSOLE)
        Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8[&4SimpleCheck&8] &8The plugin is loading..."));
        try {
            register();
            this.config = new Config();
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8[&4SimpleCheck&8] &8Plugin loaded &asuccesfully."));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8[&4SimpleCheck&8] &8Version: &7" + pdf.getVersion()));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8[&4SimpleCheck&8] &8Developer: &7Booldy"));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8[&4SimpleCheck&8] &8GitHub: &7FatBolid"));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8[&4SimpleCheck&8] &8Discord: &7Bolid#0001"));
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8[&4SimpleCheck&8] &8The Plugin can't load! Please contact the Developer on &3Discord &8or GitHub."));
        }
    }

    protected void register() {
        //Commands
        getCommand("ss").setExecutor(new CheckCmd());
        getCommand("imhacking").setExecutor(new ImHackingCmd());

        //Events
        getServer().getPluginManager().registerEvents(new SSEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
    }
    public static CheckPlugin getInstance() {
        return plugin;
    }
    private Config config;
    static CheckPlugin plugin;
    public static HashMap<String, String> ss = new HashMap<>();
}
