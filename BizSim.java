/*
Will Fung and Grace Mazzarella

Thought Questions:
1.
Customers: 23
Lines: 5
Minimum service time: 1 min
Maximum service time: 30 min
(With the exception of the occasional problem customer, in which case service time is multiplied by 5)

Seed 1: 1997 - market (194), bank (176)
Seed 2: 31415926 - market(259), bank (229)
Seed 3: 273 - market(224), bank(194)
Seed 4: 19960928 - market(180), bank(160)
Seed 5: 11970119 - market(212), bank(240)!
Seed 6: 19970119 - market(184), bank(194)!
Seed 7: 20000308 - market(238), bank(258)!
Seed 8: 19661024 - market(235), bank(238)!
Seed 9: 19631108 - market(196), bank(196) **
Seed 10: 17760704 - market(124), bank(133)!

It seems that about half the time, the market is faster and half the time the bank is faster.

2.
WILL HOWEVER YOU SAID TO DO THIS WAS QUICK AND EASY AND NOT THE WAY TO DO IT

WE SHOULD EITHER WRITE A PROGRAM TO CALUCLATE THIS OR *GROAN* DO IT BY HAND FOR ALL 10 SEEDS

Market
Seed 1: 194/23 = 8.435 min
Seed 2: 259/23 = 11.261 min
Seed 3: 224/23 = 9.739 min
Seed 4: 180/23 = 7.826 min
Seed 5: 212/23 = 9.217 min
Seed 6: 184/23 = 8.000 min
Seed 7: 238/23 = 10.348 min
Seed 8: 235/23 = 10.217 min
Seed 9: 196/23 = 8.522 min
Seed 10:  124/23 = 5.391 min

Bank
Seed 1: 176/23 = 7.652 min
Seed 2: 229/23 = 9.957 min
Seed 3: 194/23 = 8.435 min
Seed 4: 160/23 = 6.957 min
Seed 5: 240/23 = 10.435 min
Seed 6: 194/23 = 8.435 min
Seed 7: 258/23 = 11.217 min
Seed 8: 238/23 = 10.348 min
Seed 9: 196/23 = 8.522 min
Seed 10: 133/23 = 5.783 min

Market - Bank
Seed 1: 8.435 - 7.652 = 0.783 min
Seed 2: 11.261 - 9.957 = 1.304 min
Seed 3: 9.739 - 8.435 = 1.304 min
Seed 4: 7.826 - 6.957 = 0.869 min
Seed 5: 9.217 - 10.435 = -1.218 min !
Seed 6: 8.000 - 8.435 = -0.435 min !
Seed 7: 10.348- 11.217 = -0.869 min !
Seed 8: 10.217 - 10.348 = -0.131 min !
Seed 9: 8.522 - 8.522 = 0.000 min **
Seed 10: 5.391 - 5.783 = -0.392 min !

3.
Simulating the ability to jump between lines in a multiple-line simulation where
customers can move from the end of one line to another until the lines are even
would mean we cannot use a queue, since queues can only add to the back and remove
from the front. We'd need a dequeue to store customers, since both ends in that
structure are accessible.

4.

WILL CHECK ME

If there were separate lines dedicated to serving customers of varying lnegths of
service times, improvement of average wait time would depend on how many of each
line there are. If there is only one line serving customers with long requirements,
then all those customers' wait times would average out the short wait times of the
faster customers. However, if there are many lines serving long requirement customers,
average wait time might go down.
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

	// Number of servers
	protected int servers;

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
		this.servers = numServicePoints;
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
				x = new Customer(rand.nextInt(latestArrival) + 1, rand.nextInt(MAX_SERVICE_TIME) + 1, i, true);
			} else {
				x = new Customer(rand.nextInt(latestArrival) + 1, rand.nextInt(MAX_SERVICE_TIME) + 1, i, false);
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
		while (countdown > 0){
			step();
			--countdown;
		}
		return check();
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
			if (this.lines.elementAt(a).isEmpty()){
				// pass
			} else if (this.lines.elementAt(a).get().serviceTime <= 0){
				this.lines.elementAt(a).remove();
			}
		}

		// make sure all customers who are first in line (i.e., being served) are marked as such
		for (int b = 0; b < this.lines.size() - 1; ++b){
			if (!this.lines.elementAt(b).isEmpty()){
				this.lines.elementAt(b).get().atTeller = true;
			}
		}

		//if supermarket
		if (this.type == 1){
			// if a customer arrives, add them to the shortest queue; if tied, go to the lower index
			if (this.customerQueue.getFirst() == null){
				//
			}	else {
				while (this.customerQueue.getFirst() != null && this.time == this.customerQueue.getFirst().getArrival()){
					int smallest = this.servers-1;
					for (int i = 0; i <= this.lines.size() - 1; ++i){
						if (this.lines.elementAt(i).size() < this.lines.elementAt(smallest).size()){
							smallest = i;
						}
					}
					this.lines.elementAt(smallest).add(this.customerQueue.remove());
				}
			}

			// decrease service time required for all customers being served
			for (int j = 0; j < this.lines.size(); ++j){
				if (!this.lines.elementAt(j).isEmpty()){
					this.lines.elementAt(j).get().servedTime();
				}
			}
			return check();

		//else bank
		} else {
			// The bank has its own queue for customers waiting to for a teller; this is the last element in this.lines
			// if a customer arrives, add them to the customer queue
			if (this.customerQueue.getFirst() == null){
				// pass
			} else {
				while (this.customerQueue.getFirst() != null && this.time == this.customerQueue.getFirst().getArrival()){
					this.lines.elementAt(this.lines.size() - 1).add(this.customerQueue.remove());
				}
			}

			// check if a bank teller is free and there are customers, customers go to first free teller
			for (int u = 0; u < this.lines.size() - 1; ++u){
				if (this.lines.elementAt(u).size() == 0 && this.lines.elementAt(this.lines.size() - 1).size() != 0){
					this.lines.elementAt(u).add(this.lines.elementAt(this.lines.size() - 1).remove());
				}
			}

			// decrease service time required for all customers being served

			for (int v = 0; v < this.lines.size() - 1; ++v){
				if (this.lines.elementAt(v).get() != null){
					this.lines.elementAt(v).get().servedTime();
				}
			}
			return check();
		}
	}

	/**
	 * Checks if all customers have been satisfied
	 	 Returns true once all customers have been satisfied
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
