package com.cyprias.spawnerdistancerestricter;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Events implements Listener {
	private SpawnerDistanceRestricter plugin;

	public Events(SpawnerDistanceRestricter plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.isCancelled())
			return;
		if (event.getBlock().getTypeId() != 52)
			return;
		if (plugin.hasPermission(event.getPlayer(), "spawnerdistancerestricter.exempt"))
			return;
		Block block = event.getBlock();

		int bX, bY, bZ;
		bX = block.getX();
		bY = block.getY();
		bZ = block.getZ();

		int distance = Config.distanceBetweenSpawners;
		World world = block.getWorld();

		Block tBlock;
		// block.getRelative(arg0, arg1, arg2)
		for (int x = 0 - distance; x <= distance; x++) {
			for (int y = 0 - distance; y <= distance; y++) {
				for (int z = 0 - distance; z <= distance; z++) {

					if (x == 0 && y == 0 && z == 0)
						continue;

					if (block.getRelative(x, y, z).getTypeId() == 52) {
						event.getPlayer().sendMessage(Config.stTooClose);

						event.setCancelled(true);
						return;
						// plugin.info("X Y Z " + x + " " + y + " " +
						// z);
					}

				}

			}
		}
	}

	public boolean isSpawner(World world, int x, int y, int z) {
		if (world.getBlockAt(x, y, z).getTypeId() == 52)
			return true;
		return false;
	}

}
