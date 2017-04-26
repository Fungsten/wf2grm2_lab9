import java.util.Vector;
import java.util.Random;
import structure5.*;

public class Main{

  public static void main(String args[]){
    Customer a = new Customer(1, 10, "Grace");
    Customer b = new Customer(2, 10, "Will");

    System.out.println(a.toString());
    System.out.println(a.compareTo(b));
    System.out.println(b.toString());

  }
}
