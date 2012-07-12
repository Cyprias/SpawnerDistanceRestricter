package com.cyprias.spawnerdistancerestricter;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnerDistancesRestricter extends JavaPlugin {
	public static File folder = new File("plugins/SpawnerDistancesRestricter");
	public static String chatPrefix = "§f[§aSDR§f] ";
	public Events events;
	public Config config;

	private String stPluginEnabled = "§f%s §7v§f%s §7is enabled.";
	String pluginName;
	public void onEnable() {
		config = new Config(this);
		events = new Events(this);
		getServer().getPluginManager().registerEvents(events, this);
		pluginName = this.getDescription().getName();
		
		info(String.format(stPluginEnabled, pluginName, this.getDescription().getVersion()));
	}

	public void info(String msg) {
		getServer().getConsoleSender().sendMessage(chatPrefix + msg);
	}

	public boolean hasPermission(CommandSender sender, String node) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		//if (player.isOp()) {
		//	return true;
		//}

		if (player.isPermissionSet(node))
			return player.hasPermission(node);

		String[] temp = node.split("\\.");
		String wildNode = temp[0];
		for (int i = 1; i < (temp.length); i++) {
			wildNode = wildNode + "." + temp[i];

			if (player.isPermissionSet(wildNode + ".*"))
				// plugin.info("wildNode1 " + wildNode+".*");
				return player.hasPermission(wildNode + ".*");

		}
		if (player.isPermissionSet(wildNode))
			return player.hasPermission(wildNode);

		if (player.isPermissionSet(wildNode))
			return player.hasPermission(wildNode);

		return player.hasPermission(pluginName.toLowerCase() + ".*");
	}
	
}
