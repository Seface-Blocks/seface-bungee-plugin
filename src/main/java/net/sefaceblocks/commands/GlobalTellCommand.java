package net.sefaceblocks.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.sefaceblocks.utils.SefaceUtils;

import java.util.ArrayList;

import static net.sefaceblocks.utils.Resources.*;

public class GlobalTellCommand extends Command implements TabExecutor {
  private static final ArrayList<String> EMPTY_TAB_COMPLETE = new ArrayList<>();

  private static final String messageFormat = getInstance().getConfig().getString("global-tell-format");
  private static final String me = getInstance().getConfig().getString("messages.me");
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

    pSender.sendMessage(
      message.getText().replace(pSender.getName(), me)
    );

    pRecipient.sendMessage(
      message.getText().replace(pRecipient.getName(), me)
    );
  }

  @Override
  public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
    ArrayList<String> playersList = new ArrayList<>();

    if(args.length == 1) {
      for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
        if(player != sender) {
          playersList.add(player.getName());

        }
      }

      return playersList;
    }

    return EMPTY_TAB_COMPLETE;
  }
}
