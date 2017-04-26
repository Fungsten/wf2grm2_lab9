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

	/* series of service points where customers queue and are served
	 	 customers wait in the last index of lines */
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
		int countdown = timeSteps;
		while (countdown >= 0){
			step();
			--countdown;
		}
		return step();
	}

	/**
	 * Advances 1 time step through the simulation.
	 *
	 * @post the simulation has advanced 1 time step
	 * @return true if the simulation is over, false otherwise
	 */
	public boolean step(){
		// increment time
		++this.time;

		// regardless of type, first check if lines have customers with completed service
		// if a customer's service is completed, remove that customer from the queue
		for (int a = 0; a < lines.size(); ++a){
			if (this.lines.elementAt(a).get().serviceTime == 0){
				this.lines.elementAt(a).remove();
			}
		}

		// make sure all customers who are first in line (i.e., being served) are marked as such
		for (int b = 0; b < this.lines.size() - 2; ++b){
			this.lines.elementAt(b).get().atTeller = true;
		}

		//if supermarket
		if (this.type == 1){
			// if a customer arrives, add them to the shortest queue; if tied, go to the lower index
			if (this.time == this.customerQueue.getFirst().getArrival()){
				int smallest = this.customerQueue.size(); // a large number
				for (int i = 0; i < this.lines.size() - 2; ++i){
					if (this.lines.elementAt(i).size() < smallest){
						smallest = i;
					}
				}
				this.lines.elementAt(smallest).add(this.customerQueue.remove());
			}

			// decrease service time required for all customers being served
			for (int j = 0; j < this.lines.size() - 2; ++j){
				this.lines.elementAt(j).get().servedTime();
			}
			return check();

		//else bank
		} else {
			// The bank has its own queue for customers waiting to for a teller; this is the last element in this.lines
			// if a customer arrives, add them to the customer queue
			if (this.time == this.customerQueue.getFirst().getArrival()){
				this.lines.elementAt(this.lines.size() - 1).add(this.customerQueue.remove());
			}
			// check if a bank teller is free and there are customers, customers go to first free teller
			for (int u = 0; u < this.lines.size() - 2; ++u){
				if (this.lines.elementAt(u).size() == 0 && this.lines.elementAt(this.lines.size() - 1).size() != 0){
					this.lines.elementAt(u).add(this.lines.elementAt(this.lines.size() - 1).remove());
				}
			}

			// decrease service time required for all customers being served
			for (int v = 0; v < this.lines.size() - 2; ++v){
				this.lines.elementAt(v).get().servedTime();
			}
			return check();
		}
	}

	/**
	 * Checks if all customers have been satisfied
	 */
	public boolean check(){
		// if there are still customers to arrive
		if (!this.customerQueue.isEmpty()){
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
