import java.util.*;
import java.text.SimpleDateFormat;
/*
Name: Martin Mihaylov
Student#: 500876796
*/
class Transaction{
	/*
	Instance variables: id number of transaction, a GregorianCalendar to store the date the transaction was made, the car sold, 
	an enum to hold the transation type, the amount of money involved, and who sold the car
	*/
	int id;
	GregorianCalendar date;
	Car car;
	public enum Type{
		BUY, RETURN
	}
	Type type;
	double price;
	String seller;
	
	//Constructor to initalize the instance variables
	public Transaction(int id, GregorianCalendar date, Car car, String seller, Type type, double price){
		this.id = id;
		this.date = date;
		this.car = car;
		this.type = type;
		this.price = price;
		this.seller = seller;
	}
	
	//Allows the transaction to toggle between buy and return
	public void setType(Type type){
		this.type = type;
	}
	
	//Its display method using a simpledateformat identical to the one in the demo for the date in the GregorianCalendar instance variable
	public String display(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
		return "ID: " + id + " " + sdf.format(date.getTime()) + " " + type + " SalesPerson: " + seller;
	}
}