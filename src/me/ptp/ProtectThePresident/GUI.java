package me.ptp.ProtectThePresident;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
 
public class GUI extends JavaPlugin implements Listener {

//I haven't done permissions and stuff, this is just a basic outline
	
	public static ArrayList<String> teams = new ArrayList<String>();
	public static ArrayList<String> otherteam = new ArrayList<String>();
 
    private Inventory i;
 
    public void createGUI() {
        this.i = Bukkit.createInventory(null, 9, "Team Chooser");
 
        ItemStack terrorist = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = terrorist.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Terrorist");
        terrorist.setItemMeta(meta);
        
        ItemStack swat = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta1 = swat.getItemMeta();
        meta1.setDisplayName(ChatColor.AQUA + "Swat");
        swat.setItemMeta(meta1);
 
        this.i.setItem(4, terrorist);
        this.i.setItem(6, swat);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(!(e.getInventory().getName().equalsIgnoreCase("Team Chooser"))) return;
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);
        if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR ||!e.getCurrentItem().hasItemMeta()) return;
 
        switch(e.getCurrentItem().getType()){
        case DIAMOND_SWORD:
            p.sendMessage(ChatColor.RED + "You have chosen to be in the Terrorist team");
            teams.add(p.getName());
            p.closeInventory();
            break;
        case DIAMOND_CHESTPLATE:
        	p.sendMessage(ChatColor.AQUA + "You have chosen to be in the Swat team");
        	otherteam.add(p.getName());
            p.closeInventory();
            break;
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.CHEST));
    }
 
    @EventHandler
    public void onAction(PlayerInteractEvent e) {
        Action a = e.getAction();
        ItemStack is = e.getItem();
        if(a==Action.PHYSICAL || is == null || is.getType()==Material.AIR) return;
            if(is.getType()==Material.CHEST) {
                e.getPlayer().openInventory(this.i);
            }
        }
}
