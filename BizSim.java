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
	protected Vector<QueueVector<Customer>> lines;

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
	public BizSim(int numCustomers, int numServicePoints, int latestArrival, long seed, int type) {
		this.customerQueue = generateCustomerSequence(numCustomers, latestArrival, seed);
		this.lines = new Vector<QueueVector<Customer>>();
		for (int i = 0; i < numServicePoints; ++i){
			this.lines.add(new QueueVector<Customer>());
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
		int latestArrival, long seed) {

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

	/**
	 * Advances @timeSteps time steps through the simulation.
	 *
	 * @post the simulation has advanced @timeSteps time steps
	 * @return true if the simulation is over, false otherwise
	 */
	public boolean step(int timeSteps){
<<<<<<< HEAD
		// regardless of type, first check if lines have customers with completed service
		++this.time;
		for (int i = 0; i < lines.size(); ++i){
			if (lines.elementAt(i).get().serviceTime == 0){
				// if a customer's service is completed, remove that customer from the queue
				lines.elementAt(i).remove();
			}
		}
		//if supermarket
		if (this.type == 1){

		//else bank
		} else {
			if (this.time == customerQueue.getFirst().getArrival()){
				lines
			}
			}

		}
	}
=======


	/**
	 * Advances 1 time step through the simulation.
	 *
	 * @post the simulation has advanced 1 time step
	 * @return true if the simulation is over, false otherwise
	 */
	abstract public boolean step(){
		/*
		pseudo code time
		time adds one to itself
		every customer at a teller has his/her service time subtracted by one
			if service time reaches zero, next customer goes to teller
		if all queues are empty, return true
		*/
		this.time++;
		if ()
	}

>>>>>>> 17f9c939324236d0f0ac85ab770582ad7f30f6ab

	/**
	 * Checks if all customers have been satisfied
	 */
	public boolean check(){
		// if there are still customers to arrive
		if (!customerQueue.isEmpty()){
			return false;
		}
		// else everyone has arrived, check if they're gone
		for (int i = 0; i < this.lines.size(); ++i){
			if (!this.lines.elementAt(i).isEmpty()){
				return false;
			}
		}
		return true;
	}

	/**
	 * @return a string representation of the simulation
	 */
	public String toString() {
		String str = "Time: " + time + "\n";
		str = str + "Event Queue: ";
		if (this.customerQueue != null) {
			str = str + this.customerQueue.toString();
		}
		str = str + "\n";

		if (this.lines != null)  {
			for (QueueVector<Customer> l : lines) {
				str = str + "Lines: " + l.toString() + "\n";
			}
		}

		return str;
	}
}
