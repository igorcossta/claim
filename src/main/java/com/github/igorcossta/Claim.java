package com.github.igorcossta;

import com.github.igorcossta.loader.Loader;
import com.github.igorcossta.manager.IRegionManager;
import com.github.igorcossta.manager.RegionManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Claim extends JavaPlugin {
  private IRegionManager regionManager;

  @Override
  public void onEnable() {
    // Plugin startup logic
    Loader loader = new Loader(this);
    loader.load();

    this.regionManager = new RegionManager();
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  public IRegionManager getRegionManager() {
    return regionManager;
  }
}
