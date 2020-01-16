import java.util.*;
import java.text.SimpleDateFormat;
/*
Name: Martin Mihaylov
Student#: 500876796
*/
class AccountingSystem{
	/*
	Instance variable: A transactions treemap which associates an int id with each transaction so they can be searched for by id, idList is the keyset for the transactions, 
	salesByMonth holds the amount of sales made each month,
	*/
	Map<Integer, Transaction> transactions = new TreeMap<Integer, Transaction>();
	ArrayList<Integer> idList = new ArrayList<Integer>();
	int[] salesByMonth = {0,0,0,0,0,0,0,0,0,0,0,0};
	
	//Basic constructor, no need to do anything
	public AccountingSystem(){
	}
	
	//Adds a transactions with a randomly generated int id and adds the id to the keyset idList. Increments the amount of sales in a given month if the transaction was a buy
	public String add(GregorianCalendar date, Car car, String salesPerson, Transaction.Type type, double salePrice){
		Random r = new Random();
		int generatedID = r.nextInt(99) + 1;
		transactions.put(generatedID, new Transaction(generatedID, date, car, salesPerson, type, salePrice));
		idList.add(generatedID);
		if(type == Transaction.Type.BUY){
			salesByMonth[date.get(Calendar.MONTH)-1] += 1;
		}
		return transactions.get(generatedID).display();
	}
	
	//Returns a transaction based on id, returns null if it does not exist
	public Transaction getTransaction(int id){
		try{
			return transactions.get(id);
		}
		catch(Exception e){
			return null;
		}
	}
	
	//Prints out all transactions for a given month
	public void transactionsByMonth(int month){
		for(int i = 0; i < idList.size(); i++){
			if(transactions.get(idList.get(i)).date.get(Calendar.MONTH)+1 == month){
				System.out.println(transactions.get(idList.get(i)).display());
			}
		}
	}
	
	//Returns which month has the most sales so far
	public String bestMonth(int returned){
		int highest = 0;
		int bm = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("MMM");
		for(int i = 0; i < salesByMonth.length; i++){
			if(salesByMonth[i]>highest){
				highest = salesByMonth[i];
				bm = i;
			}
		}
		return sdf.format(bm) + ": cars sold - " + (highest - returned);
	}
}