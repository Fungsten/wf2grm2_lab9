/*
Will Fung and Grace Mazzarella

Thought Questions:
1.
[]

2.
[]

3.
[]

4.
[]
*/

import java.util.Vector;
import java.util.Random;
import structure5.*;

public class BizSim {

	/* sequence of customers, prioritized by randomly-generated event time */
	protected PriorityVector<Customer> customerQueue;

	/* series of service points where customers queue and are served */
	protected Vector<QueueVector<Customer>> servicePoints;

	/* current time step in the simulation */
	protected int time;

	/* seed for Random() so that simulation is repeatable */
	protected long seed;

	// If type = 1, market; if type = 2, bank
	protected int type;

	/* Used to bound the range of service times that Customers require in minutes*/
	static final int MIN_SERVICE_TIME = 1;
	static final int MAX_SERVICE_TIME = 30;

	/**
	 * Creates a BusinessSimulation.
	 * @post the step() function may be called.
	 *
	 * @numCustomers number of random customers that appear in the simulation
	 * @numSerivicePoints number of tellers in this simulation
	 * @latestArrival latest timeStep that a Customer may appear in the simulation
	 * @seed used to seed a Random() so that simulation is repeatable.
	 */
	public BizSim(int numCustomers, int numServicePoints, int lastestArrival, long seed, int type) {
		this.customerQueue = generateCustomerSequence(numCustomers, latestArrival, seed);
		this.servicePoints = new Vector<QueueVector<Customer>>();
		for (int i = 0; i < numServicePoints; ++i){
			this.servicePoints.add(new QueueVector<Customer>());
		}
		this.time = 0;
		this.seed = seed;
		this.type = type;
	}

	/**
	 * Generates a sequence of Customer objects, stored in a PriorityQueue.
	 * Customer priority is determined by arrival time
	 * @arg numCustomers number of customers to simulate
	 * @arg latestArrival maximum timeStep that a customer arrives
	 *      in @eventQueue
	 * @arg seed use Random(seed) to make customer sequence repeatable
	 * @return A PriorityVector that represents Customer arrivals,
	 *         ordered by Customer arrival time
	 */
	public static PriorityVector<Customer> generateCustomerSequence(int numCustomers,
								       int latestArrival,
								       long seed) {
		Random rand = new Random(seed);
		PriorityVector<Customer> queue = new PriorityVector<Customer>();
		for (int i = 0; i < numCustomers; ++i){
			int diceRoll = rand.nextInt();
			if (diceRoll % 10 == 0){
				// One in ten customers is a terrible person
				Customer x = new Customer(rand.nextInt(latestArrival), rand.nextInt(MAX_SERVICE_TIME), i, true);
			} else {
				Customer x = new Customer(rand.nextInt(latestArrival), rand.nextInt(MAX_SERVICE_TIME), i, false);
			}
			queue.add(x);
		}
		return queue;
	}

	/**
	 * Advances @timeSteps time steps through the simulation.
	 *
	 * @post the simulation has advanced @timeSteps time steps
	 * @return true if the simulation is over, false otherwise
	 */
	public boolean step(int timeSteps);

	/**
	 * Checks if all customers have been satisfied
	 */
	public boolean check(){
		// if there are still customers to arrive
		if (!customerQueue.isEmpty()){
			return false;
		}
		// else everyone has arrived, check if they're gone
		for (int i = 0; i < servicePoints.size(); ++i){
			// Looking at checkout lines
			for (int j = 0; j < servicePoints.elementAt(i).size(); ++j){
				if (!j.servicePoints.elementAt(i).isEmpty()){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return a string representation of the simulation
	 */
	public String toString() {
		// TODO: modify if needed.
		String str = "Time: " + time + "\n";
		str = str + "Event Queue: ";
		if (eventQueue != null) {
			str = str + eventQueue.toString();
		}
		str = str + "\n";

		if (servicePoints != null)  {
			for (Queue<Customer> sp : servicePoints) {
				str = str + "Service Point: " + sp.toString() + "\n";
			}
		}

		return str;
	}
}
