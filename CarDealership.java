import java.util.*;
/*
Name: Martin Mihaylov
Student#: 500876796
*/
class CarDealership{
	/*Declaration of instance variables; a Car ArrayList to hold the cars in stock, an array of booleans to hold the applied filters, 
	and two doubles to hold the max and min prices used by the price filter, VINs of existing cars, VINs of bought cars, and doubles to hold revenue, sales, and return stats
	*/
	public ArrayList<Car> cars;
	public SalesTeam st;
	public AccountingSystem as;
	boolean[] filters = new boolean[3];
	double minimumPrice, maximumPrice;
	
	public ArrayList<Integer> VINS = new ArrayList<Integer>();
	public ArrayList<Integer> boughtVINS = new ArrayList<Integer>();
	double revenue, sales, returns;
	
	//Constructor; The Car ArrayList containing the cars in stock is initialized and all filters are set to false by default using a for loop
	public CarDealership(ArrayList<Car> cars, SalesTeam st, AccountingSystem as){
		this.cars = cars;
		this.st = st;
		this.as = as;
		for(int i = 0; i<filters.length; i++){
			filters[i] = false;
		}
		this.revenue = 0;
		this.sales = 0;
		this.returns = 0;
	}
	
	//Takes a given arraylist and adds its contents to the instance variable holding the cars currently in stock
	public void addCars(ArrayList<Car> newCars){
		for(int i = 0; i<newCars.size(); i++){
			cars.add(newCars.get(i));
		}
		//Checks for duplicates and reset the VIN if needed
		for(int i = 0; i<cars.size(); i++){
			for(int j = 0; j<cars.size(); j++){
				if(!(cars.get(j).mfr.equals(cars.get(i).mfr)) && (cars.get(j).VIN == cars.get(i).VIN)){
					cars.get(j).setVIN();
				}
			}
		}
		for(int i = 0; i<newCars.size(); i++){
			VINS.add(newCars.get(i).getVIN());
		}
	}
	
	//Removes a car from the ArrayList of cars in stock and returns it. Throws an Exception and returns null if a non-existent index is given
	public Car buyCar(int VIN){
		try{
		Car bought = null;
		String salesPerson;
		Random r = new Random();
		GregorianCalendar dateOfSale = new GregorianCalendar(2019, r.nextInt(11) + 1, r.nextInt(29) + 1);
			for(int i = 0; i < cars.size(); i++){
				if(cars.get(i).getVIN() == VIN){
					boughtVINS.add(cars.get(i).getVIN());
					bought = cars.get(i);
					revenue+=cars.get(i).price;
					cars.remove(i);
					salesPerson = st.findSalesPerson();
					as.add(dateOfSale, bought, salesPerson, Transaction.Type.BUY, bought.price);
					sales++;
				}
			}
			return bought;
		}
		catch(Exception e){
			System.out.println("Error trying to fetch car");
			return null;
		}
	}
	
	//Creates a return transaction for a returned car and increments the return stat
	public void returnCar(int transaction){
		Transaction returnTransaction = as.getTransaction(transaction);
		GregorianCalendar newDate = as.getTransaction(transaction).date;
		as.add(newDate, returnTransaction.car, returnTransaction.seller, Transaction.Type.RETURN, returnTransaction.price);
		returns++;
	}
	
	/*
	The following three methods activate search filters for electric cars, all-wheel drive, and 
	price respectively by setting the associated boolean in the array to true
	*/
	public void filterByElectric(){
		filters[0] = true;
	}
	
	public void filterByAWD(){
		filters[1] = true;
	}
	
	public void filterByPrice(double minPrice, double maxPrice){
		minimumPrice = minPrice;
		maximumPrice = maxPrice;
		filters[2] = true;
	}
	
	//Turns off all filters in the boolean array that holds the filters
	public void filtersClear(){
		for(int i = 0; i<filters.length; i++){
			filters[i] = false;
		}
	}
	
	//Prints out cars in stock from the cars ArrayList according to the activated filters
	public void displayInventory(){
				for(int i = 0; i<cars.size(); i++){
				if(filters[0]==false && filters[1]==false && filters[2]==false){
					System.out.println(cars.get(i).display());
				}
				else if(filters[0]==false && filters[1]==false && filters[2]==true){
					if(cars.get(i).price>= minimumPrice && cars.get(i).price<= maximumPrice){
						System.out.println(cars.get(i).display());
					}
				}
				else if(filters[0]==false && filters[1]==true && filters[2]==false){
					if(cars.get(i).AWD == true){
						System.out.println(cars.get(i).display());
					}
				}
				else if(filters[0]==false && filters[1]==true && filters[2]==true){
					if(cars.get(i).AWD == true && cars.get(i).price>= minimumPrice && cars.get(i).price<= maximumPrice){
						System.out.println(cars.get(i).display());
					}
				}
				else if(filters[0]==true && filters[1]==false && filters[2]==false){
					if(cars.get(i).getClass().getName().equals("ElectricCar")){
						System.out.println(cars.get(i).display());
					}
				}
				else if(filters[0]==true && filters[1]==false && filters[2]==true){
					if(cars.get(i).getClass().getName().equals("ElectricCar") && cars.get(i).price>= minimumPrice && cars.get(i).price<= maximumPrice){
						System.out.println(cars.get(i).display());
					}
				}
				else if(filters[0]==true && filters[1]==true && filters[2]==false){
					if(cars.get(i).getClass().getName().equals("ElectricCar") && cars.get(i).AWD == true){
						System.out.println(cars.get(i).display());
					}
				}
				else if(filters[0]==true && filters[1]==true && filters[2]==true){
					if(cars.get(i).getClass().getName().equals("ElectricCar") && cars.get(i).AWD == true && cars.get(i).price>= minimumPrice && cars.get(i).price<= maximumPrice){
						System.out.println(cars.get(i).display());
					}
				}
			}
		}
	
	//The following two inner classes are comparators for cars that can sort by safety rating and max range respectively
	class CompareSR implements Comparator<Car>{
		public int compare(Car one, Car two){
			if(one.safetyRating>two.safetyRating){
				return -1;
			}
			else if(one.safetyRating<two.safetyRating){
				return 1;
			}
			else{
				return 0;
			}
		}
	}
	
	class CompareMR implements Comparator<Car>{
		public int compare(Car one, Car two){
			if(one.maxRange>two.maxRange){
				return -1;
			}
			else if(one.maxRange<two.maxRange){
				return 1;
			}
			else{
				return 0;
			}
		}
	}

	//Sorts the ArrayList of cars in stock by price using the compareTo() method defined in Car.java
	public void sortByPrice(){
		Collections.sort(cars);
	}
	
	//The following two methods sort the ArrayList of cars in stock using the comparators for safety rating and max range respectively
	public void sortBySafetyRating(){
		Collections.sort(cars, new CompareSR());
	}
	
	public void sortByMaxRange(){
		Collections.sort(cars, new CompareMR());
	}
	
	//prints all of the transaction made at this dealership
	public void printSales(){
		for(int i = 0; i < as.transactions.size(); i++){
			System.out.println(as.transactions.get(as.idList.get(i)).display());
		}
	}
	
	//Returns the general stats of this dealership
	public String totalStats(){
		double avgSales = revenue/12;
		return "Total Sales: " + (int)revenue + " Total Sold: " + (int)sales + " Avg Sales: " + (int)avgSales + " Total Returned: " + (int)returns + " Best Month: " + as.bestMonth((int)returns);
	}
}