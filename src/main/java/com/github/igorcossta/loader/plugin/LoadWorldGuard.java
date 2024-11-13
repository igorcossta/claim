package com.github.igorcossta.loader.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Utility class for load WorldGuard
 */
public class LoadWorldGuard {
  private final Plugin claim;

  public LoadWorldGuard(Plugin claim) {
    this.claim = claim;
  }

  public void loadPlugin() throws Exception {
    final Plugin wg = claim.getServer().getPluginManager().getPlugin("WorldGuard");
    if (wg != null && wg.isEnabled()) {
      claim.getLogger().info(String.format("Plugin %s is loaded successfully.", wg.getName()));
    } else {
      throw new Exception("failed to load world guard.");
    }
  }
}
