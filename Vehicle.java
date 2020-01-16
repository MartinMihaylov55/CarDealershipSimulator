import java.util.*;
/*
Name: Martin Mihaylov
Student#: 500876796
*/
class Vehicle{
	
	//Instance variables to hold manufacturer, color, power, number of wheels and an enum containing engine types as constants
	String mfr,color;
	int power,numWheels, VIN;
	public enum Engine{
		ELECTRIC_MOTOR, GAS_ENGINE 
	}
	Engine engine;
	Random r = new Random();
	//Constructor that simply initializes the instance variables
	public Vehicle(String mfr, String color, int power, int numWheels, Engine engine){
		this.mfr = mfr;
		this.color = color;
		this.power = power;
		this.numWheels = numWheels;
		this.engine = engine;
		this.VIN = r.nextInt(499 - 100) + 100;
	}
	
	//Getter and setter methods for each respective instance variable
	public String getMFR(){
		return mfr;
	}
	public String getColor(){
		return color;
	}
	public int getPower(){
		return power;
	}
	public int getNumWheels(){
		return numWheels;
	}
	public Engine getEngine(){
		return engine;
	}
	public int getVIN(){
		return VIN;
	}
	public void setVIN(){
		VIN = r.nextInt(499 - 100) + 100;
	}
	public void setMFR(String newMFR){
		mfr = newMFR;
	}
	public void setColor(String newColor){
		color = newColor;
	}
	public void setPower(int newPower){
		power = newPower;
	}
	public void setNumWheels(int newNumWheels){
		numWheels = newNumWheels;
	}
	public void setEngine(Engine newEngine){
		engine = newEngine;
	}
	
	//Override of thw equals method from object. Compares the manufacturer, power, and number of wheels of this and another Vehicle and returns true if they are equal
	public boolean equals(Object other){
		Vehicle compared = (Vehicle) other;
		if(this.getMFR().equals(compared.getMFR()) && this.power == compared.power && this.numWheels == compared.numWheels){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Returns the manufacturer and color of the instance in String form when called
	public String display(){
		return " VIN: " + VIN + " " + mfr + " " + color + " ";
	}
}