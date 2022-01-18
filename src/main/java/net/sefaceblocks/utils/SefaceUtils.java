package net.sefaceblocks.utils;

public class SefaceUtils {

  /** Replaces the characters '&' with '§' in a String. */
  public static String replaceColorChar(String content) {
    return content.replace('&', '§');
  }
}
