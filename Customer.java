/*
Will Fung and Grace Mazzarella

*/

import structure5.*;

public class Customer implements Comparable<Customer> {

	/**
	 * Creates a Customer that arrives at time step @eventTime and
	 * requires @serviceTime time steps to be satisfied after
	 * arriving at a service point.
	 */

	 protected int arrivalTime;
	 protected int serviceTime;
	 protected String name;
	 protected boolean atTeller;
	 protected boolean problemCustomer;

	public Customer(int arrivalTime, int serviceTime, String name, boolean val) {
		this.arrivalTime = arrivalTime;
		this.problemCustomer = val;
		if (this.problemCustomer == true){
			this.serviceTime = serviceTime * 5;
		} else {
			this.serviceTime = serviceTime;
		}
		this.name = name;
		this.atTeller = false; // switched if first in queue

	}

	public int getArrival(){
		return this.arrivalTime;
	}

	public int getService(){
		return this.serviceTime;
	}

	public void servedTime(){
		--this.serviceTime;
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
		if (getProblem() == true){
			info = info + "\nBe warned, this is a known problem customer.";
		}
		return info;
	}

	public boolean getProblem(){
		if (this.problemCustomer == true){
			System.out.println("Customer " + this.name + " says: I am a self-entitled brat and want you to give me free things!");
		}
		return this.problemCustomer;
	}


}
