package net.sefaceblocks.utils;

import lombok.SneakyThrows;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static net.sefaceblocks.SefaceBungeePlugin.*;

public class CustomConfig {
  private File file;
  private final String name;
  private final Boolean copyDefaults;

  public CustomConfig(String name, Boolean copyDefaults) {
    this.name = name;
    this.copyDefaults = copyDefaults;

    this.create();
  }

  @SneakyThrows
  public void create() {
    File dataFolder = getInstance().getDataFolder();
    this.file = new File(dataFolder, this.name);

    if(!dataFolder.exists()) { dataFolder.mkdir(); }

    if(this.copyDefaults) { this.file.delete(); }

    if(!this.file.exists()) {
      InputStream file = getInstance().getClass().getClassLoader().getResourceAsStream(this.name);
      Files.copy(file, Path.of(dataFolder.getPath().concat("/").concat(this.name)));
    }
  }

  @SneakyThrows
  public Configuration loadConfig() {
    return ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);
  }

  @SneakyThrows
  public void saveConfig() {
    ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.loadConfig(), this.file);
  }
}
