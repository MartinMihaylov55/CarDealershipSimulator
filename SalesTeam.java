import java.util.*;
/*
Name: Martin Mihaylov
Student#: 500876796
*/
class SalesTeam{
	//Instance variables: A LinkedList of the available salespeople, their sales stats in a treemap associated with their names, 
	LinkedList<String> salesPeople = new LinkedList<String>();
	Map<String, Integer> salesStats = new TreeMap<String, Integer>();
	int[] stats = new int[5];
	
	//Constructor: Adds the salespeople and sets their stats to zero in the salesStats map
	public SalesTeam(){
		salesPeople.addFirst("Preston");
		salesPeople.addFirst("Codsworth");
		salesPeople.addFirst("Piper");
		salesPeople.addFirst("Cait");
		salesPeople.addFirst("Nick");
		Iterator<String> iter = salesPeople.iterator();
		while(iter.hasNext()){
			salesStats.put(iter.next(), 0);
		}
	}
	
	//Returns a salesPerson when needed, returns NIL in case of error to keep the program stable
	public String findSalesPerson(){
		try{
			Random r = new Random();
			int addressToGet = r.nextInt(4+1);
			int newSales = salesStats.get(salesPeople.get(addressToGet));
			newSales++;
			salesStats.put(salesPeople.get(addressToGet),newSales);
			return salesPeople.get(addressToGet);
		}
		catch(Exception e){
			return "NIL";
		}
	}
	
	//Returns the salesPeople with the most sales. If the best salespeople are tied, they also get printed out along with the number of sales
	public String bestSeller(){
		String winner = "";
		String testName;
		int highest = 0;
		Iterator<String> iter = salesPeople.iterator();
		while(iter.hasNext()){
			testName = iter.next();
			if(salesStats.get(testName) > highest){
				winner = (testName + " ");
				highest = salesStats.get(testName);
			}
			else if(salesStats.get(testName) == highest){
				winner += (testName + " ");
				highest = salesStats.get(testName);
			}
		}
		return "TopSP: " + winner +  highest;
	}
	
	//Returns all of the names in the sales team
	public String display(){
		String result = "Team: ";
		Iterator<String> iter = salesPeople.iterator();
		while(iter.hasNext()){
			result += iter.next() + " ";
		}
		return result;
	}
}