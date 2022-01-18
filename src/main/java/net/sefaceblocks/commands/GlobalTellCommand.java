package net.sefaceblocks.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.sefaceblocks.utils.SefaceUtils;

import static net.sefaceblocks.utils.Resources.*;

public class GlobalTellCommand extends Command {
  private static final String messageFormat = getInstance().getConfig().getString("global-tell-format");
  private static final String invalidMessage = getInstance().getConfig().getString("messages.invalidMessage");
  private static final String invalidPlayer = getInstance().getConfig().getString("messages.invalidPlayer");
  private static final String recipientIsSender = getInstance().getConfig().getString("messages.recipientIsSender");
  private static final String playerOffline = getInstance().getConfig().getString("messages.playerOffline");

  public GlobalTellCommand(String name, String permission) {
    super(name, permission);
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    if(!(sender instanceof ProxiedPlayer)) { return; }

    ProxiedPlayer pSender = (ProxiedPlayer) sender;
    StringBuilder content = new StringBuilder();
    TextComponent message = new TextComponent();

    if(args.length == 0 || args[0] == null) {
      message.setText(SefaceUtils.replaceColorChar(invalidPlayer));
      pSender.sendMessage(message);
      return;
    }

    ProxiedPlayer pRecipient =  ProxyServer.getInstance().getPlayer(args[0]);

    if (pRecipient == null || !pRecipient.isConnected()) {
      message.setText(SefaceUtils.replaceColorChar(playerOffline));
      pSender.sendMessage(message);
      return;
    }

    if (pSender == pRecipient) {
      message.setText(SefaceUtils.replaceColorChar(recipientIsSender));
      pSender.sendMessage(message);
      return;
    }

    if (args.length == 1 || args[1] == null) {
      message.setText(SefaceUtils.replaceColorChar(invalidMessage));
      pSender.sendMessage(message);
      return;
    }

    for (int i = 1; i < args.length; i++) {
      if (i > 1) { content.append(" "); }
      content.append(args[i]);
    }

    message.setText(
      SefaceUtils.replaceColorChar(messageFormat)
        .replace("{SENDER}", pSender.getName())
        .replace("{RECIPIENT}", pRecipient.getName())
        .replace("{CONTENT}", content)
    );

    pSender.sendMessage(message);
    pRecipient.sendMessage(message);
  }
}
