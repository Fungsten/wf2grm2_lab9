import java.util.Vector;
import java.util.Random;
import structure5.*;

public class Main{

  public static void main(String args[]){
    //Customer a = new Customer(1, 10, 1, false);
    //Customer b = new Customer(2, 10, 2, true);

    /*
    Random seeds = new Random();
    int seed = seeds.nextInt();
    Random rand = new Random(seed);

    PriorityVector<Customer> queue = generateCustomerSequence(23, 400, rand.nextInt());
    for (int j = 0; j < 21; ++j){
      System.out.println(queue.remove().toString());
    }
    //System.out.println(a.compareTo(b));
    //System.out.println(b.toString());
    */

    /*Random seeds = new Random();
    int seed = seeds.nextInt();
    Random rand = new Random(seed);*/

    BizSim sim1 = new BizSim(23, 5, 100, 1997, 1);
    /*while (!sim1.check()){
      sim1.step();
      System.out.println(sim1.toString());
      System.out.println("");
    }
    int i = 0;
    while (i < sim1.waits.size()){
      System.out.println(sim1.waits.elementAt(i));
      ++i;
    }*/
    int i = 0;
    while (i < 3){
      sim1.step(1);
      System.out.println(sim1.toString());
      System.out.println("");
      ++i;
    }
  }
}
