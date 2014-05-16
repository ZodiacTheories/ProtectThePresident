package me.ptp.ProtectThePresident;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public GUI gui = new GUI(this);
	
	public void onEnable(){
		
		gui.createGui();
		
		System.out.println("===============");
		System.out.println("=[PtP] Enabled=");
		System.out.println("===============");
		
	}
	
	public void onDisable(){
		
		System.out.println("================");
		System.out.println("=[PtP] Disabled=");
		System.out.println("================");
		
	}

}
