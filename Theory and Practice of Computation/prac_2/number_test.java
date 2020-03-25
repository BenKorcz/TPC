import java.io.*;

class number {

  static public final int invalid = 0;
  static public final int integral = 1;
  static public final int floating = 2;

  public int tag;
  public int value_i;
  public double value_d;

  public number() { tag = invalid; }
  public number(int v) { tag = integral; value_i = v; }
  public number(double v) { tag = floating; value_d = v; }

  void print() {
    switch (tag) {
    case invalid:
        System.out.print("  <<< invalid number in \"print\" >>> "); break;
    case integral:
        System.out.print(value_i); break;
    case floating:
        System.out.print(value_d); break;
    }
  }

};

class number_test {

  static number plus(number l, number r) {
    number result = new number();

    if(l.tag != r.tag){
      System.out.println("Type Error....!");
    }else{
      result.tag = l.tag;
      if(l.tag==number.integral){
        int res = l.value_i+r.value_i;
        result.value_i = res;
      }else if (r.tag == number.floating){
        double res = l.value_d+r.value_d;
        result.value_d = res;
      }
    }
    return result;
  }

  static public void main(String args[]) {
    number a, b;

    a = new number(5);                              // a is integral
    b = new number(6);                              // so is b

    System.out.print("a = "); a.print(); System.out.print("\n");
    System.out.print("b = "); b.print(); System.out.print("\n");
                                                    // OK: both integral
    System.out.print("a + b = "); plus(a, b).print(); System.out.print("\n");

    a = new number(5.7);                            // a is now floating
    System.out.print("a = "); a.print(); System.out.print("\n");
                                                    // type mismatch expected
    System.out.print("a + b = "); plus(a, b).print(); System.out.print("\n");

    b = new number(3.2);                            // b is now floating
    System.out.print("b = "); b.print(); System.out.print("\n");
                                                    // OK: both floating
    System.out.print("a + b = "); plus(a, b).print(); System.out.print("\n");
  }

}
