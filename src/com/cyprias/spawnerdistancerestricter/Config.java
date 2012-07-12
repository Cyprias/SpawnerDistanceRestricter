package com.cyprias.spawnerdistancerestricter;


import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;


public class Config {
	private SpawnerDistanceRestricter plugin;
	private static Configuration config;
	
	static int distanceBetweenSpawners;
	static boolean checkBedrockToSkybox;
	static String stTooClose;
	
	public Config(SpawnerDistanceRestricter plugin) {
		this.plugin = plugin;
		config = plugin.getConfig().getRoot();
		config.options().copyDefaults(true);
		plugin.saveConfig();
		checkBedrockToSkybox = config.getBoolean("checkBedrockToSkybox");
		distanceBetweenSpawners = config.getInt("distanceBetweenSpawners");
		stTooClose = config.getString("stTooClose").replaceAll("(?i)&([a-k0-9])", "\u00A7$1");

	}
}
