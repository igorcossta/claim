package com.github.igorcossta.loader;

import org.bukkit.plugin.Plugin;

/**
 * Utility class for load and register all plugin commands
 */
public class LoadCommands {
  private final Plugin claim;

  public LoadCommands(Plugin claim) {
    this.claim = claim;
  }

  public void loadCommands() throws Exception {
    boolean isClaimCmdLoaded = loadClaimCommandAndSubCommands();
    boolean isAdminCmdLoaded = loadAdminCommandsAndSubCommands();
    if (!isClaimCmdLoaded || !isAdminCmdLoaded) {
      throw new Exception("failed to load commands");
    }
    claim.getLogger().info("Commands loaded successfully.");
  }

  private boolean loadClaimCommandAndSubCommands() {
    return true;
  }

  private boolean loadAdminCommandsAndSubCommands() {
    return true;
  }
}
