package io.github.hsyyid.spongeessentialcmds.utils;

import io.github.hsyyid.spongeessentialcmds.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.world.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Utils
{
	public static void setHome(UUID userName, Location playerLocation, String worldName, String homeName)
	{
		String playerName = userName.toString();
		ConfigurationLoader<CommentedConfigurationNode> configManager = Main.getConfigManager();
		Main.config.getNode("home", "users", playerName, homeName, "world").setValue(worldName);
		Main.config.getNode("home", "users", playerName, homeName, "X").setValue(playerLocation.getX());
		Main.config.getNode("home", "users", playerName, homeName, "Y").setValue(playerLocation.getY());
		Main.config.getNode("home", "users", playerName, homeName, "Z").setValue(playerLocation.getZ());
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("home.users." + playerName + "." + "homes").split("\\."));
		if (valueNode.getString() != null)
		{
			String items = valueNode.getString();
			if (items.contains(homeName + ","))
				;
			else
			{
				String formattedItem = (homeName + ",");
				valueNode.setValue(items + formattedItem);
			}
		}
		else
		{
			valueNode.setValue(homeName + ",");
		}

		try
		{
			configManager.save(Main.config);
			configManager.load();
		}
		catch (IOException e)
		{
			System.out.println("[SpongeEssentialCmds]: Failed to add " + playerName + "'s home!");
		}
	}

	public static void setWarp(Location playerLocation, String worldName, String warpName)
	{
		ConfigurationLoader<CommentedConfigurationNode> configManager = Main.getConfigManager();
		Main.config.getNode("warps", warpName, "world").setValue(worldName);
		Main.config.getNode("warps", warpName, "X").setValue(playerLocation.getX());
		Main.config.getNode("warps", warpName, "Y").setValue(playerLocation.getY());
		Main.config.getNode("warps", warpName, "Z").setValue(playerLocation.getZ());
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("warps.warps").split("\\."));
		if (valueNode.getString() != null)
		{
			String items = valueNode.getString();
			if (items.contains(warpName + ","))
				;
			else
			{
				String formattedItem = (warpName + ",");
				valueNode.setValue(items + formattedItem);
			}
		}
		else
		{
			valueNode.setValue(warpName + ",");
		}

		try
		{
			configManager.save(Main.config);
			configManager.load();
		}
		catch (IOException e)
		{
			System.out.println("[SpongeEssentialCmds]: Failed to add warp!");
		}
	}

	public static String getJoinMsg()
    {
        ConfigurationNode valueNode = Main.config.getNode((Object[]) ("joinmsg").split("\\."));
	    if(valueNode.getValue() != null)
	    {
	        return valueNode.getString();
	    }
	    else
	    {
	        Utils.setJoinMsg("&4Welcome");
	        return "&4Welcome";
	    }
    }
	
	public static double getAFK()
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("afk.timer").split("\\."));
		if (valueNode.getDouble() != 0)
		{
			return valueNode.getDouble();
		}
		else
		{
			Utils.setAFK(30000);
			ConfigurationNode valNode = Main.config.getNode((Object[]) ("afk.timer").split("\\."));
			return valNode.getDouble();
		}
	}

    public static boolean getAFKKick() {
        ConfigurationNode valueNode = Main.config.getNode((Object[]) ("afk.kick.use").split("\\."));
        
        if (valueNode.getValue() != null)
        {
            return valueNode.getBoolean();
        }
        else
        {
            Utils.setAFKKick(false);
            return false;
        }
    }

    public static double getAFKKickTimer()
    {
        ConfigurationNode valueNode = Main.config.getNode((Object[]) ("afk.kick.timer").split("\\."));
        
        if (valueNode.getValue() != null)
        {
            return valueNode.getDouble();
        }
        else
        {
            Utils.setAFKKickTimer(30000);
            ConfigurationNode valNode = Main.config.getNode((Object[]) ("afk.timer").split("\\."));
            return valNode.getDouble();
        }
    }

	public static void setSpawn(Location playerLocation, String worldName)
	{
		ConfigurationLoader<CommentedConfigurationNode> configManager = Main.getConfigManager();
		Main.config.getNode("spawn", "X").setValue(playerLocation.getX());
		Main.config.getNode("spawn", "Y").setValue(playerLocation.getY());
		Main.config.getNode("spawn", "Z").setValue(playerLocation.getZ());
		Main.config.getNode("spawn", "world").setValue(worldName);
		try
		{
			configManager.save(Main.config);
			configManager.load();
		}
		catch (IOException e)
		{
			System.out.println("[SpongeEssentialCmds]: Failed to set spawn");
		}
	}
	
	public static void setJoinMsg(String msg)
    {
	    ConfigurationLoader<CommentedConfigurationNode> configManager = Main.getConfigManager();
        Main.config.getNode("joinmsg").setValue(msg);
        try
        {
            configManager.save(Main.config);
            configManager.load();
        }
        catch (IOException e)
        {
            System.out.println("[SpongeEssentialCmds]: Failed to add Join Message to Config!");
        }
    }
	public static void setAFK(double length)
	{
		ConfigurationLoader<CommentedConfigurationNode> configManager = Main.getConfigManager();
		Main.config.getNode("afk", "timer").setValue(length);
		try
		{
			configManager.save(Main.config);
			configManager.load();
		}
		catch (IOException e)
		{
			System.out.println("[SpongeEssentialCmds]: Failed to add AFK to config");
		}
	}
	
	public static void setAFKKick(boolean val)
    {
        ConfigurationLoader<CommentedConfigurationNode> configManager = Main.getConfigManager();
        Main.config.getNode("afk", "kick", "use").setValue(val);
        try
        {
            configManager.save(Main.config);
            configManager.load();
        }
        catch (IOException e)
        {
            System.out.println("[SpongeEssentialCmds]: Failed to add AFK to config");
        }
    }
	
	public static void setAFKKickTimer(double length)
    {
        ConfigurationLoader<CommentedConfigurationNode> configManager = Main.getConfigManager();
        Main.config.getNode("afk", "kick", "timer").setValue(length);
        try
        {
            configManager.save(Main.config);
            configManager.load();
        }
        catch (IOException e)
        {
            System.out.println("[SpongeEssentialCmds]: Failed to add AFK to config");
        }
    }

	public static void addLastDeathLocation(UUID userName, Location playerLocation)
	{
		String playerName = userName.toString();
		ConfigurationLoader<CommentedConfigurationNode> configManager = Main.getConfigManager();
		Main.config.getNode("back", "users", playerName, "lastDeath", "X").setValue(playerLocation.getX());
		Main.config.getNode("back", "users", playerName, "lastDeath", "Y").setValue(playerLocation.getY());
		Main.config.getNode("back", "users", playerName, "lastDeath", "Z").setValue(playerLocation.getZ());
		try
		{
			configManager.save(Main.config);
			configManager.load();
		}
		catch (IOException e)
		{
			System.out.println("[Back]: Failed to add " + playerName + "'s last death location!");
		}
	}

	public static Location lastDeath(Player player)
	{
		String playerName = player.getUniqueId().toString();
		ConfigurationNode xNode = Main.config.getNode((Object[]) ("back.users." + playerName + "." + "lastDeath.X").split("\\."));
		double x = xNode.getDouble();
		ConfigurationNode yNode = Main.config.getNode((Object[]) ("back.users." + playerName + "." + "lastDeath.Y").split("\\."));
		double y = yNode.getDouble();
		ConfigurationNode zNode = Main.config.getNode((Object[]) ("back.users." + playerName + "." + "lastDeath.Z").split("\\."));
		double z = zNode.getDouble();

		Location location = new Location(player.getWorld(), x, y, z);
		return location;
	}

	public static ArrayList<String> getHomes(UUID userName)
	{
		String playerName = userName.toString();
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("home.users." + playerName + "." + "homes").split("\\."));
		String list = valueNode.getString();

		ArrayList<String> homeList = new ArrayList<String>();
		boolean finished = false;

		// Add all homes to homeList
		if (finished != true)
		{
			int endIndex = list.indexOf(",");
			if (endIndex != -1)
			{
				String substring = list.substring(0, endIndex);
				homeList.add(substring);

				// If they Have More than 1
				while (finished != true)
				{
					int startIndex = endIndex;
					endIndex = list.indexOf(",", startIndex + 1);
					if (endIndex != -1)
					{
						String substrings = list.substring(startIndex + 1, endIndex);
						homeList.add(substrings);
					}
					else
					{
						finished = true;
					}
				}
			}
			else
			{
				homeList.add(list);
				finished = true;
			}
		}

		return homeList;
	}

	public static ArrayList<String> getWarps()
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("warps.warps").split("\\."));
		String list = valueNode.getString();

		ArrayList<String> warpList = new ArrayList<String>();
		boolean finished = false;

		// Add all homes to warpList
		if (finished != true)
		{
			int endIndex = list.indexOf(",");
			if (endIndex != -1)
			{
				String substring = list.substring(0, endIndex);
				warpList.add(substring);

				// If they Have More than 1
				while (finished != true)
				{
					int startIndex = endIndex;
					endIndex = list.indexOf(",", startIndex + 1);
					if (endIndex != -1)
					{
						String substrings = list.substring(startIndex + 1, endIndex);
						warpList.add(substrings);
					}
					else
					{
						finished = true;
					}
				}
			}
			else
			{
				warpList.add(list);
				finished = true;
			}
		}

		return warpList;
	}

	public static double getX(UUID playerName, String homeName)
	{
		String userName = playerName.toString();
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("home.users." + userName + "." + homeName + ".X").split("\\."));
		return valueNode.getDouble();
	}

	public static String getHomeWorldName(UUID playerName, String homeName)
	{
		String userName = playerName.toString();
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("home.users." + userName + "." + homeName + ".world").split("\\."));
		return valueNode.getString();
	}

	public static double getY(UUID playerName, String homeName)
	{
		String userName = playerName.toString();
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("home.users." + userName + "." + homeName + ".Y").split("\\."));
		return valueNode.getDouble();
	}

	public static double getZ(UUID playerName, String homeName)
	{
		String userName = playerName.toString();
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("home.users." + userName + "." + homeName + ".Z").split("\\."));
		return valueNode.getDouble();
	}

	public static double getWarpX(String warpName)
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("warps." + warpName + ".X").split("\\."));
		return valueNode.getDouble();
	}

	public static String getWarpWorldName(String warpName)
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("warps." + warpName + ".world").split("\\."));
		return valueNode.getString();
	}
	
	public static String getSpawnWorldName()
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("spawn.world").split("\\."));
		return valueNode.getString();
	}

	public static double getWarpY(String warpName)
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("warps." + warpName + ".Y").split("\\."));
		return valueNode.getDouble();
	}

	public static double getWarpZ(String warpName)
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("warps." + warpName + ".Z").split("\\."));
		return valueNode.getDouble();
	}

	// Check if Player is In Config
	public static boolean inConfig(UUID playerName, String homeName)
	{
		String userName = playerName.toString();
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("home.users." + userName + "." + homeName + ".X").split("\\."));
		Object inConfig = valueNode.getValue();
		if (inConfig != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean isWarpInConfig(String warpName)
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("warps." + warpName + ".X").split("\\."));
		Object inConfig = valueNode.getValue();
		if (inConfig != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean isSpawnInConfig()
	{
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("spawn.X").split("\\."));
		Object inConfig = valueNode.getValue();
		if (inConfig != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static Location getSpawn(Player player)
	{
		ConfigurationNode xNode = Main.config.getNode((Object[]) ("spawn.X").split("\\."));
		double x = xNode.getDouble();

		ConfigurationNode yNode = Main.config.getNode((Object[]) ("spawn.Y").split("\\."));
		double y = yNode.getDouble();

		ConfigurationNode zNode = Main.config.getNode((Object[]) ("spawn.Z").split("\\."));
		double z = zNode.getDouble();

		Location spawn = new Location(player.getWorld(), x, y, z);
		return spawn;
	}

	public static boolean isLastDeathInConfig(Player player)
	{
		String userName = player.getUniqueId().toString();
		ConfigurationNode valueNode = Main.config.getNode((Object[]) ("back.users." + userName + ".lastDeath.X").split("\\."));
		Object inConfig = valueNode.getValue();
		if (inConfig != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
