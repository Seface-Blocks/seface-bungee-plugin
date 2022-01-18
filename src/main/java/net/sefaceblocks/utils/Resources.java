package net.sefaceblocks.utils;

import lombok.Getter;

public class Resources {
  @Getter private static CustomConfig instance;

  public static void register() {
    instance = new CustomConfig("config.yml", true);
  }
}
