package net.sefaceblocks.utils;

import lombok.Getter;

public class Resources {
  @Getter private static CustomConfig config;

  public static void register() {
    config = new CustomConfig("config.yml", true);
  }
}
