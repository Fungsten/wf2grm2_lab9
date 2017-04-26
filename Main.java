import java.util.Vector;
import java.util.Random;
import structure5.*;

public class Main{

  public static void main(String args[]){
    Customer a = new Customer(1, 10, "Grace", false);
    Customer b = new Customer(2, 10, "Will", true);

    //System.out.println(a.toString());
    //System.out.println(a.compareTo(b));
    //System.out.println(b.toString());
    Random seeds = new Random();
    int seed = seeds.nextInt();
    Random rand = new Random(seed);
    for (int i = 0; i < 20; i++){
      int potato = rand.nextInt();
      if (potato % 10 == 0){
        System.out.println(potato);
      }

    }



  }
}
