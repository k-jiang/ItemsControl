package net.kjiang.itemscontrol;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

/***
 * ItemsControl.java
 * 
 * Main class of a bukkit plug-in.
 * 
 * DirtControl is an internal closed-source bukkit-plugin project for WhateverMC Server (Suibian-MC).
 * The plug-in is released under 3-clause BSD license. Some rights reserved by kjiang 2014.
 * 
 * @author k_jiang (GitHub:k-jiang)
 * @version 0.0.1.172
 */

/***
 * ===== IMPORTANT =====
 * This is a preview of ItemsControl.
 * This code is only a introduce to how does the plug-in work.
 * 
 */

public final class ItemsControl extends JavaPlugin implements Listener {
	@Override
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("Load complete. Plug-in enabled.");
		getLogger().info("\n          --==== !! Warning!! ====--\nThis is a demo version shows how does the plugin:\n 1. control behaver on placing Dirt blocks on world 'world' (the Dirt block would never have grass growing on it).\n 2. stop player placing a TNT block on any worlds.\nThis plug-in should NOT be used in a production environment and use it AT YOUR OWN RISK.");
	}
	@Override
	public void onDisable(){
		HandlerList.unregisterAll();
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	void onBlockPlaceEvent(BlockPlaceEvent evt) {
		Block block = evt.getBlock();
		Player player = evt.getPlayer();
		
		// feature control user placing a dirt block that would never growth grass on a specific world
		// specify the world "world"
		if (player.getWorld().getName().equalsIgnoreCase("world")) {
			player.sendMessage("You are standing on world 'world' and placing a Dirt block.");
			// replacing meta id if dirt placed
			if (block.getType().equals(Material.DIRT) && block.getData()==(byte)0) {
				block.setData((byte)1);
				// debug
				player.sendMessage("ItemsControl changing dirt from 3:0 ==> 3:1 to prevent it growing grass.");
			}
		}
		
		// feature stop player placing TNT on any worlds by replacing the block to AIR
		if (block.getType().equals(Material.TNT)) {
			block.setType(Material.AIR);
			player.sendMessage("The ItemsControl plug-in stops you placing a TNT");
		}
	}
}
