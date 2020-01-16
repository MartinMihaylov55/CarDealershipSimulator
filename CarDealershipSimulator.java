import java.util.*;
import java.io.*;
/*
Name: Martin Mihaylov
Student#: 500876796
*/
public class CarDealershipSimulator 
{	
  public static void main(String[] args) throws FileNotFoundException
  {
	  // Create a CarDealership object
	  CarDealership cd = new CarDealership(new ArrayList<Car>(), new SalesTeam(), new AccountingSystem());
	  // Then create an (initially empty) array list of type Car
	  ArrayList<Car> cars = new ArrayList<Car>();
	  ArrayList<Car> garage = new ArrayList<Car>();
	  Scanner fileIn = new Scanner(new File("cars.txt"));
	  String[] carFileData;
      //This loop iterates through the cars file and parses the data to add cars to the dealership
	  while(fileIn.hasNext()){
		  carFileData = fileIn.nextLine().split(" ");
		  if(Car.Engine.valueOf(carFileData[3]) == Car.Engine.ELECTRIC_MOTOR){
			if(carFileData[6].equals("AWD")){
				cars.add(new ElectricCar(carFileData[0],carFileData[1],130,4,Car.Engine.ELECTRIC_MOTOR,Car.Model.valueOf(carFileData[2]),Integer.parseInt(carFileData[5]),Double.parseDouble(carFileData[4]),Double.parseDouble(carFileData[7]),true,Integer.parseInt(carFileData[8]),"LITHIUM"));
			}
			else{
				cars.add(new ElectricCar(carFileData[0],carFileData[1],130,4,Car.Engine.ELECTRIC_MOTOR,Car.Model.valueOf(carFileData[2]),Integer.parseInt(carFileData[5]),Double.parseDouble(carFileData[4]),Double.parseDouble(carFileData[7]),false,Integer.parseInt(carFileData[8]),"LITHIUM"));
			}
		}
		else{
			if(carFileData[6].equals("AWD")){
				cars.add(new Car(carFileData[0],carFileData[1],130,4,Car.Engine.GAS_ENGINE,Car.Model.valueOf(carFileData[2]),Integer.parseInt(carFileData[5]),Double.parseDouble(carFileData[4]),Double.parseDouble(carFileData[7]),true));
			}
			else{
				cars.add(new Car(carFileData[0],carFileData[1],130,4,Car.Engine.GAS_ENGINE,Car.Model.valueOf(carFileData[2]),Integer.parseInt(carFileData[5]),Double.parseDouble(carFileData[4]),Double.parseDouble(carFileData[7]),false));
			}
		}
	  }
	  // See the cars file for car object details
	  // Add the car objects to the array list
      // The ADD command should hand this array list to CarDealership object via the addCars() method	  
	  
	  // Create a scanner object
	  Scanner sc = new Scanner(System.in);
	  //holds the command inputted by the user
	  String command = "";
	  // while the scanner has another line
	  while(!(command.equals("Q"))){
		  try{
			//read the next word from the commandLine scanner 
			command = sc.nextLine();
			//check if the word (i.e. string) is equal to one of the commands and if so, call the appropriate method via the CarDealership object  
			//lists cars in stock
			if(command.equals("L")){
				cd.displayInventory();
			}
			
			//adds cars to dealership
			else if(command.equals("ADD")){
				cd.addCars(cars);
			}
			
			//removes a car from the dealership and puts it in your garage ArrayList
			else if(command.contains("BUY")){
				String[] operands = command.split(" ");
				if(cd.VINS.contains(Integer.parseInt(operands[1])) && !(cd.boughtVINS.contains(Integer.parseInt(operands[1])))){
				garage.add(cd.buyCar(Integer.parseInt(operands[1])));
				System.out.print(cd.as.transactions.get(cd.as.idList.get(cd.as.idList.size()-1)).display()); 
				System.out.println(garage.get(garage.size()-1).display() + " purchased");
				}
				else{
					System.out.println("Car not found in inventory");
				}
			}
			
			//Returns the car in your garage associated with a given transaction id to the dealership
			else if(command.contains("RET")){
				String[] operands = command.split(" ");
				if(garage.size()>0){
				ArrayList<Car> returned = new ArrayList<Car>();
				returned.add(cd.as.getTransaction(Integer.parseInt(operands[1])).car);
				cd.addCars(returned);
				cd.returnCar(Integer.parseInt(operands[1]));
				System.out.println(cd.as.getTransaction(Integer.parseInt(operands[1])).display() + cd.as.getTransaction(Integer.parseInt(operands[1])).car.display() + " returned");
				System.out.println("Debug placeholder");
				for(int i = 0; i < garage.size(); i++){
					if(garage.get(i).mfr.equals(cd.as.getTransaction(Integer.parseInt(operands[1])).car.mfr)){
							garage.remove(i);
							break;
						}
					}
				}
				else{
					System.out.println("The given ID cannot be returned");
				}
			}
			
			//sorts dealership cars by price
			else if(command.equals("SPR")){
				cd.sortByPrice();
			}
			
			//sorts dealership cars by safety rating
			else if(command.equals("SSR")){
				cd.sortBySafetyRating();
			}
			
			//sorts dealership cars by max range
			else if(command.equals("SMR")){
				cd.sortByMaxRange();
			}
			
			//applies a price filter given a max and min price
			else if(command.contains("FPR")){
				String[] operands = command.split(" ");
				cd.filterByPrice(Integer.parseInt(operands[1]), Integer.parseInt(operands[2]));
				
			}
			
			//applies an electric car only filter
			else if(command.equals("FEL")){
				cd.filterByElectric();
			}
			
			//applies an all-wheel drive only filter
			else if(command.equals("FAW")){
				cd.filterByAWD();
			}
			
			//clears all filters
			else if(command.equals("FCL")){
				cd.filtersClear();
			}
			
			//All sales-related commands are handled here
			else if(command.contains("SALES")){
				
				String[] operands = command.split(" ");
				//prints all transactions
				if(command.equals("SALES")){
					cd.printSales();
				}
				
				//prints out the sales team
				else if(command.equals("SALES TEAM")){
					System.out.println(cd.st.display());
				}
				
				//prints out the top salesperson/people
				else if(command.equals("SALES TOPSP")){
					System.out.println(cd.st.bestSeller());
				}
				
				//prints out the dealership's stats
				else if(command.equals("SALES STATS")){
					System.out.println(cd.totalStats());
				}
				
				//prints out the sales for a particular month
				else if(Integer.parseInt(operands[1]) <= 12){
					cd.as.transactionsByMonth(Integer.parseInt(operands[1]));
				}
			}
			
			//Quits the program by breaking the while loop
			else if(command.equals("Q")){
				sc.close();
				System.out.print("");
			}
			
			//Prints when an unrecognized command is entered
			else{
				System.out.println("Unrecognized command");
			}
		  }
		  
		  //General exception handler for any other problems not caught by the previous else statement
		  catch(Exception e){
			  System.out.println("Invalid operand");
		  }
	  }
  }
}