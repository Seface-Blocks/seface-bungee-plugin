package net.sefaceblocks;

import net.md_5.bungee.api.plugin.Plugin;
import net.sefaceblocks.utils.Register;

public class SefaceBungeePlugin extends Plugin {
  private static Plugin instance;

  @Override
  public void onEnable() {
    instance = this;

    Register.pluginCommands(this);
  }
}
