package me.qcbj.SnowballBlock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private Object Block;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("SnowballBlock has been enabled!");
    }

    @EventHandler
    public void onThrow (ProjectileHitEvent event) {
        Projectile projectile =  (Projectile) event.getEntity();
        Player player = (Player) projectile.getShooter();

        if (projectile instanceof Snowball && player instanceof Player) {
            ItemStack item = new ItemStack(player.getInventory().getItemInOffHand());
            Material itemType = item.getType();
            if (itemType.isBlock()) {
                int x = projectile.getLocation().getBlockX();
                int y = projectile.getLocation().getBlockY();
                int z = projectile.getLocation().getBlockZ();
                final World world = player.getWorld();
                Location location = new Location(world, x, y, z);
                location.getBlock().setType(itemType);
            } else {
                return;
            }
        } else {
            return;
        }

    }

}
