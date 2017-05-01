import java.util.Vector;
import java.util.Random;
import structure5.*;

public class Main{

  /*
  static final int MIN_SERVICE_TIME = 1;
	static final int MAX_SERVICE_TIME = 30;


  public static PriorityVector<Customer> generateCustomerSequence(int numCustomers,
								       int latestArrival,
								       long seed) {
		Random rand = new Random(seed);
		PriorityVector<Customer> queue = new PriorityVector<Customer>();
		for (int i = 0; i < numCustomers; ++i){
			int diceRoll = rand.nextInt();
      Customer x;
			if (diceRoll % 10 == 0){
				// One in ten customers is a terrible person
				x = new Customer(rand.nextInt(latestArrival), rand.nextInt(MAX_SERVICE_TIME), i, true);
			} else {
				x = new Customer(rand.nextInt(latestArrival), rand.nextInt(MAX_SERVICE_TIME), i, false);
			}
			queue.add(x);
		}
		return queue;
	}
  */

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

    BizSim sim1 = new BizSim(23, 5, 100, 1997, 2);
    while (!sim1.check()){
      sim1.step();
      System.out.println(sim1.toString());
      System.out.println("");
    }
    /*int i = 0;
    while (i < 75){
      sim1.step(1);
      System.out.println(sim1.toString());
      System.out.println("");
      ++i;
    }*/
  }
}
