package com.github.igorcossta.loader.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Utility class for load WorldEdit
 */
public class LoadWorldEdit {
  private final Plugin claim;

  public LoadWorldEdit(Plugin claim) {
    this.claim = claim;
  }

  public void loadPlugin() throws Exception {
    final Plugin we = claim.getServer().getPluginManager().getPlugin("WorldEdit");
    if (we != null && we.isEnabled()) {
      claim.getLogger().info(String.format("Plugin %s is loaded successfully.", we.getName()));
    } else {
      throw new Exception("failed to load world edit.");
    }
  }
}
