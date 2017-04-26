import java.util.Vector;
import java.util.Random;
import structure5.*;

public class Main{

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

  public static void main(String args[]){
    Customer a = new Customer(1, 10, 1, false);
    Customer b = new Customer(2, 10, 2, true);

    System.out.println(generateCustomerSequence(21, 400, 9).toString());
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

    //System.out.println(generateCustomerSequence(21, 400, potato).toString());

  }

}
