package dev.yhdiamond.wisprandomblocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WalkListen implements Listener {
    WispRandomBlocks plugin;
    List<Material> blocks = Arrays.asList(Material.values());
    public WalkListen(WispRandomBlocks plugin) {
        this.plugin = plugin;
        for (Material material : blocks) {
            if (material.isItem() || material.isAir()) {
                blocks.remove(material);
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if (plugin.gamestarted){
            Player p = e.getPlayer();
            Block b = p.getWorld().getBlockAt(p.getLocation());
            if (plugin.storedloc.get(p.getUniqueId()).getX() != b.getX() || plugin.storedloc.get(p.getUniqueId()).getY() != b.getY() || plugin.storedloc.get(p.getUniqueId()).getZ() != b.getZ()){
                Block underblock = p.getWorld().getBlockAt(p.getLocation().subtract(0,1,0));
                if (underblock.getType() != Material.AIR && underblock.isLiquid() == false && underblock.getType() != Material.FROSTED_ICE){
                    ItemStack underblockitem = new ItemStack(underblock.getType(), 1);
                    if (underblock.getType() != Material.CAVE_AIR &&
                            underblock.getType() != Material.VOID_AIR &&
                            underblock.getType() != Material.TALL_GRASS &&
                            underblock.getType() != Material.TALL_SEAGRASS &&
                            underblock.getType() != Material.KELP &&
                            underblock.getType() != Material.SEAGRASS &&
                            underblock.getType() != Material.GRASS &&
                            underblock.getType() != Material.FERN &&
                            underblock.getType() != Material.LARGE_FERN){
                        Random random = new Random();
                        Material randommaterial = blocks.get(random.nextInt(blocks.size()));
                        underblock.setType(randommaterial);
                    }
                }
            }
            plugin.storedloc.put(p.getUniqueId(), b.getLocation());
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (plugin.gamestarted){
            Player p = e.getPlayer();
            if (!plugin.storedloc.containsKey(p.getUniqueId())){
                plugin.storedloc.put(p.getUniqueId(), p.getWorld().getBlockAt(p.getLocation()).getLocation());
            }
        }

    }
}