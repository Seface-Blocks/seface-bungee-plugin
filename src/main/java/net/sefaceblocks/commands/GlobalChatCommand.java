package net.sefaceblocks.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GlobalChatCommand extends Command {
  public GlobalChatCommand(String name) {
    super(name);
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    if(!(sender instanceof ProxiedPlayer)) { return; }

    ProxiedPlayer player = (ProxiedPlayer) sender;
    StringBuilder content = new StringBuilder();
    TextComponent message = new TextComponent();

    if (args.length == 0) { return; }

    for (int i = 0; i < args.length; i++) {
      if (i > 0) { content.append(" "); }
      content.append(args[i]);
    }

    message.setText("§b§lGLOBAL§r " + player.getDisplayName() + " §d»§7 " + content);

    ProxyServer.getInstance().broadcast(message);
  }
}
