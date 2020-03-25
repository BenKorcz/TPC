import java.io.*;

abstract class mapper {
  abstract public char map (char c);
};

class upshift extends mapper {
  public char map (char c) {
    return (char)((c >= 'a' && c <= 'z') ? c - 'a' + 'A' : c);
  }
};

class downshift extends mapper {
  public char map (char c) ...
}

class rot13 extends mapper {
  public char map (char c) ...
};

class string_application {

  static void apply (mapper m, StringBuffer s) {
    for (int i = 0; i < s.length(); i++) {
      s.setCharAt(i, m.map(s.charAt(i)));
    }
  }

  static public void main (String[] args) {
    StringBuffer s = new StringBuffer("The quick fox jumps!");

    System.out.println(s);        // -> The quick fox jumps!
    apply...
    System.out.println(s);        // -> THE QUICK FOX JUMPS!
    apply...
    System.out.println(s);        // -> the quick fox jumps!
    apply...
    System.out.println(s);        // -> gur dhvpx sbk whzcf!
    apply...
    System.out.println(s);        // -> the quick fox jumps!
  }

}
