package net.sefaceblocks.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.sefaceblocks.commands.GlobalChatCommand;
import net.sefaceblocks.commands.GlobalTellCommand;

public class Register {

  public static void pluginCommands(Plugin plugin) {
    ProxyServer.getInstance().getPluginManager().registerCommand(plugin, new GlobalChatCommand("gchat", "seface.command.gchat"));
    ProxyServer.getInstance().getPluginManager().registerCommand(plugin, new GlobalTellCommand("gtell", "seface.command.gtell"));
  }
}
