/*
Name: Martin Mihaylov
Student#: 500876796
*/
class ElectricCar extends Car{
	//New instance variables that hold the cars recharge time and battery type respectively
	int rechargeTime;
	String batteryType;
	
	//Constructor that takes in values that are passed through the superclass constructor as well as values to initialize the instance variables
	public ElectricCar(String mfr, String color, int power, int numWheels, Engine engine, Model model, int maxRange, double safetyRating, double price, boolean AWD, int rechargeTime, String batteryType){
		super(mfr,color,power,numWheels,engine,model,maxRange,safetyRating,price,AWD);
		this.rechargeTime = rechargeTime;
		this.batteryType = batteryType;
	}
	
	//Getter and setter methods for the respective instance variables
	public int getRechargeTime(){
		return rechargeTime;
	}
	
	public String getBatteryType(){
		return batteryType;
	}
	
	public void setRechargeTime(int newRechargeTime){
		rechargeTime = newRechargeTime;
	}
	
	public void setBatteryType(String newBT){
		batteryType = newBT;
	}
	
	//Overrides the display() method previously defined in car. This definition calls Car's display() method as well as adds the battery type and recharge method to the returned string
	public String display(){
		String result = super.display() + " EL, BAT: " + batteryType + " RCH: " + rechargeTime;
		return result;
	}
	
}