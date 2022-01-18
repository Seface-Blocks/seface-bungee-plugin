package net.sefaceblocks;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.sefaceblocks.utils.Register;
import net.sefaceblocks.utils.Resources;

public class SefaceBungeePlugin extends Plugin {
  @Getter private static Plugin instance;

  @Override
  public void onEnable() {
    instance = this;

    Resources.register();
    Register.pluginCommands(this);
  }
}
