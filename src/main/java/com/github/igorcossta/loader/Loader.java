package com.github.igorcossta.loader;

import com.github.igorcossta.loader.plugin.LoadWorldEdit;
import com.github.igorcossta.loader.plugin.LoadWorldGuard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Utility class for handle all loaders
 */
public class Loader {
  private final Plugin claim;

  public Loader(Plugin claim) {
    this.claim = claim;
  }

  public void load() {
    LoadWorldEdit worldEdit = new LoadWorldEdit(claim);
    LoadWorldGuard worldGuard = new LoadWorldGuard(claim);
    LoadCommands commands = new LoadCommands(claim);
    LoadListeners listeners = new LoadListeners(claim);

    try {
      worldEdit.loadPlugin();
      worldGuard.loadPlugin();
      commands.loadCommands();
      listeners.loadListeners();
    } catch (Exception ex) {
      claim.getLogger().severe(ex.getMessage());
      Bukkit.getServer().getPluginManager().disablePlugin(claim);
    }
  }
}
