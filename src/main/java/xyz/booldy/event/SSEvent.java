package xyz.booldy.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import xyz.booldy.CheckPlugin;
import xyz.booldy.util.ChatUtil;

public class SSEvent implements Listener {

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.ss.containsKey(p.getName())) {
            p.sendTitle("","");
            Bukkit.broadcastMessage(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("LogoutWhileFrozenMessage").replace("%PREFIX%", CheckPlugin.getInstance().getConfig().getString("PREFIX")).replace("%PLAYER%", p.getDisplayName())));
            CheckPlugin.ss.remove(p.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), CheckPlugin.getInstance().getConfig().getString("LogoutWhileFrozenCmd").replace("%PLAYER%", p.getName()));
        }
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerMove")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                p.sendTitle(ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("FrozenTitle")), ChatUtil.fixColor(CheckPlugin.getInstance().getConfig().getString("FrozenSubTitle")), 0, 1000000, 0);
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void PlayerDropItemEvent(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerDropItem")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void PlayerSwapHandItemEvent(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerSwapHandItems")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void PlayerItemPickEvent(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerPickupItem")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerBreakBlock")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerPlaceBlock")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void PlayerTeleportEvent(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerTeleport")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void PlayerEatEvent(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
        if (CheckPlugin.getInstance().getConfig().getBoolean("BlockPlayerFoodLevelChange")) {
            if (CheckPlugin.ss.containsKey(p.getName())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }
}