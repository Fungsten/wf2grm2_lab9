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

	public Customer(int arrivalTime, int serviceTime, int name, boolean val) {
		this.arrivalTime = arrivalTime;
		this.name = nameList(name); //Customers are people, too
		if (this.name.equals("Brady") || this.name.equals("Brent")){
			this.problemCustomer = true; //Disclaimer: we think Brent is great. Brady's still a mischievous being.
		} else {
			this.problemCustomer = val; //true for problem customers, false for regular boring ones
		}
		if (this.problemCustomer == true){
			this.serviceTime = serviceTime * 5; //to simulate real life and notalwaysright.com
		} else {
			this.serviceTime = serviceTime;
		}
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
		//clean presentation of some relevant information and some not-so-relevant-but-just-as-important information

		//rather than somewhat meaningless integers, we now have a clock
		//everything apparently starts at noon
		String time;
			if (this.arrivalTime % 60 < 10){
				time = (int)Math.floor(this.arrivalTime / 60) +":0" + (this.arrivalTime % 60);
			} else {
				time = (int)Math.floor(this.arrivalTime / 60) +":" + (this.arrivalTime % 60);
			}
		String info = "Customer " + this.name + "'s arrival time: " + time + ".\n"
			+ this.name + "'s service time: " + this.serviceTime + " minutes.";

		//purely for fun
		if (getProblem() == true){
			info = info + "\n" + this.name + " says: \"I am a self-entitled brat and want free things!\""
				+ "\nBe warned, this is a known problem customer.";
		}

		info = info + "\n";
		return info;
	}

	//may be extraneous but we can't be too careful
	public boolean getProblem(){
		return this.problemCustomer;
	}

	//Vector<String> names = new Vector<String>("Xemnas", "Xigbar", "Xaldin",
	//"Vexen", "Lexaeus", "Zexion", "Saix", "Axel", "Demyx", "Luxord", "Marluxia",
	//"Larxene", "Roxas", "Xion", "Grace", "Will", "Alison", "Alexia", "Cielo", "Cecilia",
	//"Brady");

	public String nameList(int i){
		Vector<String> names = new Vector<String>();
		//Organization XIII goes to the bank or supermarket
		names.add("Xemnas"); //i = 0
		names.add("Xigbar"); //i = 1
		names.add("Xaldin"); //i = 2
		names.add("Vexen"); //i = 3
		names.add("Lexaeus"); //i = 4
		names.add("Zexion"); //i = 5
		names.add("Saix"); //i = 6
		names.add("Axel"); //i = 7
		names.add("Demyx"); //i = 8
		names.add("Luxord"); //i = 9
		names.add("Marluxia"); //i = 10
		names.add("Larxene"); //i = 11
		names.add("Roxas"); //i = 12
		names.add("_____"); //i = 13
		names.remove("_____"); //jk
		names.add("Xion");  //i = 13
		//yes, I just spent time and memory on a joke very few people get and even less people find funny

		//NaBrO4 is here
		names.add("Grace"); //i = 14
		names.add("Will");  //i = 15
		names.add("Alison"); //i = 16
		names.add("Alexia"); //i = 17
		names.add("Cielo"); //i = 18
		names.add("Cecilia"); //i = 19
		names.add("Brady"); //i = 20

		//cameo by the CS department chair
		names.add("Chair"); //i = 21

		//cameo by the the person who sits in the CS department chair
		names.add("Brent"); //i = 22

		return names.elementAt(i);
	}

}
