/*
Name: Martin Mihaylov
Student#: 500876796
*/
class Car extends Vehicle implements Comparable<Car>{
//Intstance variables for the car's maximum range, safety rating, price, whether or not it is all-wheel drive, and an enum that contains constants for each kind of car model	
	public enum Model{
		SEDAN, SUV, SPORTS, MINIVAN
	}
		
	public Model model;
	public int maxRange;
	public double safetyRating, price;
	public boolean AWD;
	
	//class constructor that takes in values to set the instance variables as well as parameters that are passed through the superclass constructor
	public Car(String mfr, String color, int power, int numWheels, Engine engine, Model model, int maxRange, double safetyRating, double price, boolean AWD){
		super(mfr,color,power,numWheels,engine);
		this.model = model;
		this.maxRange = maxRange;
		this.safetyRating = safetyRating;
		this.price = price;
		this.AWD = AWD;
	}
	
	//Overrides the display() method from Vehicle where it calls Vehicle's version of display as well as adding model, price, safetyRating, and maximum range to the returned string
	public String display(){
		String result = super.display() + model + " " + price + "$ SF: " + safetyRating + " RNG: " + maxRange;
		return result;
	}
	
	//Overrides the equals() method previously overridden in Vehicle. This method compares the cars using the previously-overridden equals() method then the model and all-wheel drive 
	public boolean equals(Object other){
		Car compared = (Car) other;
		if(this.equals(compared)){
			if(this.model == compared.model && this.AWD == compared.AWD){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	//Overrides the compareTo() method from Comparable. Returns an int value so that cars can be sorted according to price
	public int compareTo(Car other){
		if(this.price>other.price){
			return -1;
		}
		else if(this.price<other.price){
			return 1;
		}
		else{
			return 0;
		}
	}
}