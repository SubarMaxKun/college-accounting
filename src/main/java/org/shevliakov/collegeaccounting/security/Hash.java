package org.shevliakov.collegeaccounting.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Hashes a line using SHA-256.
 */
public final class Hash {

  private Hash() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Hashes a line using SHA-256.
   *
   * @param base line to hash
   * @return hashed line
   */
  public static String hash(String base) {
    try {
      final MessageDigest digest = MessageDigest.getInstance("SHA-256");
      final byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
      final StringBuilder hexString = new StringBuilder();
      for (int i = 0; i < hash.length; i++) {
        final String hex = Integer.toHexString(0xff & hash[i]);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
