package net.sefaceblocks.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.sefaceblocks.utils.SefaceUtils;

import static net.sefaceblocks.utils.Resources.*;

public class GlobalChatCommand extends Command {
  private static final String messageFormat = getConfig().loadConfig().getString("chat-format");
  private static final String invalidMessage = getConfig().loadConfig().getString("messages.invalidMessage");

  public GlobalChatCommand(String name) {
    super(name);
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    if(!(sender instanceof ProxiedPlayer)) { return; }

    ProxiedPlayer player = (ProxiedPlayer) sender;
    StringBuilder content = new StringBuilder();
    TextComponent message = new TextComponent();

    if (args.length == 0) {
      message.setText(SefaceUtils.replaceColorChar(invalidMessage));
      player.sendMessage(message);
      return;
    }

    for (int i = 0; i < args.length; i++) {
      if (i > 0) { content.append(" "); }
      content.append(args[i]);
    }

    message.setText(
      SefaceUtils.replaceColorChar(messageFormat)
        .replace("{PLAYER}", player.getName())
        .replace("{CONTENT}", content)
    );

    ProxyServer.getInstance().broadcast(message);
  }
}
