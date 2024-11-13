package com.github.igorcossta;

import com.github.igorcossta.loader.Loader;
import org.bukkit.plugin.java.JavaPlugin;

public final class Claim extends JavaPlugin {

  @Override
  public void onEnable() {
    // Plugin startup logic
    Loader loader = new Loader(this);
    loader.load();
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
