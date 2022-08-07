package xyz.booldy.util;

import xyz.booldy.CheckPlugin;

import java.io.File;

public class Config {
    public Config() {
        load();
    }

    private void load() {
        if(!CheckPlugin.getInstance().getDataFolder().exists())
            CheckPlugin.getInstance().getDataFolder().mkdir();
        if(!(new File(CheckPlugin.getInstance().getDataFolder(), "config.yml")).exists())
            CheckPlugin.getInstance().saveDefaultConfig();
    }
}
