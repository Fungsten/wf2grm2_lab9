/*
Will Fung and Grace Mazzarella

*/

import structure5.*;

public class Customer implements Comparable<Customer> {

	// TODO: fill this class in, adding member variables and
	// methods as needed

	/**
	 * Creates a Customer that arrives at time step @eventTime and
	 * requires @serviceTime time steps to be satisfied after
	 * arriving at a service point.
	 */

	 protected int arrivalTime;
	 protected int serviceTime;
	 protected String name;

	public Customer(int arrivalTime, int serviceTime, String name) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.name = name;
	}

	public int getArrival(){
		return this.arrivalTime;
	}

	public int getService(){
		return this.serviceTime;
	}

	public String getName(){
		return this.name;
	}

	/**
	 * Compares Customers by arrival time: getArrival - other.getArrival
	 */
	public int compareTo(Customer other) {
		return getArrival() - other.getArrival();
	}

	public String toString() {
		String info = "Customer " + this.name + "'s arrival: " + this.arrivalTime + "\n"
			+ "Customer " + this.name + "'s service time required: " + this.serviceTime;
		return info;
	}
}
