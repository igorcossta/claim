package com.github.igorcossta.loader;

import org.bukkit.plugin.Plugin;

public class LoadListeners {
  private final Plugin claim;

  public LoadListeners(Plugin claim) {
    this.claim = claim;
  }

  public void loadListeners() {
    claim.getLogger().info("Listeners loaded successfully.");
  }
}
