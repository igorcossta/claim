package com.github.igorcossta.manager;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

/**
 * A region manager for easy use WorldGuard API
 */
public interface IRegionManager {
  /**
   * Gets the regions associated with a player.
   *
   * @param player the player whose regions are to be fetched
   * @return a set of region names associated with the player
   */
  Set<String> getPlayerRegions(Player player);

  /**
   * Creates a new region for the specified chunk and player.
   *
   * @param standingChunk the chunk where the region should be created
   * @param uuid          the unique identifier of the player
   * @return true if the region was created successfully, false otherwise
   */
  boolean createRegion(Chunk standingChunk, UUID uuid);

  /**
   * Checks if a given chunk is unclaimed.
   *
   * @param chunk the chunk to check
   * @return true if the region is unclaimed, false otherwise
   */
  boolean isRegionUnclaimed(Chunk chunk);

  /**
   * Checks if a specific location is unclaimed.
   *
   * @param location the location to check
   * @return true if the region at the location is unclaimed, false otherwise
   */
  boolean isRegionUnclaimed(Location location);

  /**
   * Checks if the specified player is the owner of the region at the location.
   *
   * @param location the location of the region
   * @param uuid     the unique identifier of the player
   * @return true if the player is the owner of the region, false otherwise
   */
  boolean isOwnerRegion(Location location, UUID uuid);

  /**
   * Checks if the specified player is a member of the region at the location.
   *
   * @param location the location of the region
   * @param uuid     the unique identifier of the player
   * @return true if the player is a member of the region, false otherwise
   */
  boolean isMemberRegion(Location location, UUID uuid);
}
