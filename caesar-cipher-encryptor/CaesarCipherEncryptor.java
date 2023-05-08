class CaesarCipherEncryptor {
  public static String caesarCypherEncryptor(String str, int key) {
    char[] result = new char[str.length()];
    key = key % 26;

    /*
     * can convert between chars and ints pretty easily in Java
     * Convert current char to int
     * Add key, do modulo to keep in bounds
     * Convert back to char and add to result array
     */
    for (int i = 0; i < str.length(); i++) {
      int c = (int) (str.charAt(i) - 'a');
      c = (c + key) % 26;
      result[i] = (char) (c + 'a');
    }

    return new String(result);
  }

  public static void main(String[] args) {
    System.out.println(CaesarCipherEncryptor.caesarCypherEncryptor("", 2)); // ""
    System.out.println(CaesarCipherEncryptor.caesarCypherEncryptor("a", 0)); // "a"
    System.out.println(CaesarCipherEncryptor.caesarCypherEncryptor("a", 1)); // "b"
    System.out.println(CaesarCipherEncryptor.caesarCypherEncryptor("a", 26)); // "a"
    System.out.println(CaesarCipherEncryptor.caesarCypherEncryptor("a", 27)); // "b"
    System.out.println(CaesarCipherEncryptor.caesarCypherEncryptor("xyz", 2)); // "zab"
  }
}
