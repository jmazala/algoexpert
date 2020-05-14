class Program {
  public static String caesarCypherEncryptor(String str, int key) {
    char[] result = new char[str.length()];
    key = key % 26;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    for (int i = 0; i < str.length(); i++) {
      result[i] = getNextLetter(str.charAt(i), key, alphabet);
    }

    return new String(result);
  }

  private static char getNextLetter(char letter, int key, String alphabet) {
    int code = alphabet.indexOf(letter) + key;
    if (code > 25) {
      code = -1 + code % 25;
    }

    return alphabet.charAt(code);
  }
}
