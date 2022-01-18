package net.sefaceblocks.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.sefaceblocks.commands.GlobalChatCommand;

public class Register {

  public static void pluginCommands(Plugin plugin) {
    ProxyServer.getInstance().getPluginManager().registerCommand(plugin, new GlobalChatCommand("gchat"));
  }
}
