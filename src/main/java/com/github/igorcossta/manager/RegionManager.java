package com.github.igorcossta.manager;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RegionManager implements IRegionManager {

  @Override
  public Set<String> getPlayerRegions(Player player) {
    String playerName = player.getName();
    return Objects.requireNonNull(WorldGuard.getInstance()
        .getPlatform()
        .getRegionContainer()
        .get(BukkitAdapter.adapt(player.getWorld())))
      .getRegions()
      .entrySet()
      .stream().filter(entry -> entry.getKey().startsWith(playerName + "_"))
      .map(entry -> entry.getValue().getId())
      .collect(Collectors.toSet());
  }

  @Override
  public boolean createRegion(Chunk standingChunk, UUID uuid) {
    if (!isRegionUnclaimed(standingChunk)) return false;

    var protectedCuboidRegion = createProtectedCuboidRegion(standingChunk, uuid);
    Objects.requireNonNull(WorldGuard.getInstance()
        .getPlatform()
        .getRegionContainer()
        .get(BukkitAdapter.adapt(standingChunk.getWorld())))
      .addRegion(protectedCuboidRegion);
    // todo: save the player new protected region in db if fail delete the protected region
    // todo: update the claimed chunks cache
    return true;
  }

  @Override
  public boolean isRegionUnclaimed(Chunk chunk) {
    World world = chunk.getWorld();
    int centerX = (chunk.getX() << 4) + 7;
    int centerZ = (chunk.getZ() << 4) + 7;
    int centerY = world.getHighestBlockYAt(centerX, centerZ);
    return isRegionUnclaimed(new Location(world, centerX, centerY, centerZ));
  }

  @Override
  public boolean isRegionUnclaimed(Location location) {
    return getApplicableRegions(location).size() == 0;
  }

  @Override
  public boolean isOwnerRegion(Location location, UUID uuid) {
    return matchOwner(location, uuid);
  }

  @Override
  public boolean isMemberRegion(Location location, UUID uuid) {
    return matchMember(location, uuid);
  }

  private boolean matchOwner(Location location, UUID uuid) {
    return getApplicableRegions(location)
      .getRegions()
      .stream()
      .anyMatch(region -> region.getOwners().contains(uuid));
  }

  private boolean matchMember(Location location, UUID uuid) {
    return getApplicableRegions(location)
      .getRegions()
      .stream()
      .anyMatch(region -> region.getMembers().contains(uuid));
  }

  private ApplicableRegionSet getApplicableRegions(Location location) {
    return Objects.requireNonNull(WorldGuard.getInstance()
        .getPlatform()
        .getRegionContainer()
        .get(BukkitAdapter.adapt(location.getWorld())))
      .getApplicableRegions(BukkitAdapter.asBlockVector(location));
  }

  private ProtectedRegion createProtectedCuboidRegion(Chunk chunk, UUID uuid) {
    int chunkX = chunk.getX() << 4;
    int chunkZ = chunk.getZ() << 4;

    int maxY = 320;
    int minY = -64;

    String regionID = String.format("%s_%s_%s", Bukkit.getOfflinePlayer(uuid).getName(), chunk.getX(), chunk.getZ());

    BlockVector3 minPoint = BlockVector3.at(chunkX, minY, chunkZ);
    BlockVector3 maxPoint = BlockVector3.at(chunkX + 15, maxY, chunkZ + 15);

    ProtectedCuboidRegion region = new ProtectedCuboidRegion(regionID, minPoint, maxPoint);
    return configureRegion(region, uuid);
  }

  private ProtectedRegion configureRegion(ProtectedRegion region, UUID uuid) {
    region.setPriority(1);
    region.getOwners().addPlayer(uuid);
    return region;
  }
}
