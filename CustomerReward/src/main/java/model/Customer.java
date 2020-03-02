package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Elezabeth
 * Represents a customer with list of transaction 
 *
 */
public class Customer {
	
	
	  private int id; 
	  private String name;
	  private HashMap<String, Integer> monthlyreward; //
	  private int totalReward;
	  private List<Transaction> transactionList;
	 
	
	  public void setId(int id) { this.id = id; }
	 
	  public void setName(String name) { this.name = name; }
	  
	  public void setMonthlyReward(HashMap<String, Integer> monthlyreward) {
		  this.monthlyreward = monthlyreward;
	  }
	 
	  public void setTotalReward(int totalReward) {
		  this.totalReward = totalReward;
	  }
	  
	  public void setTransactionList(List<Transaction> transactionList) {
		  this.transactionList = transactionList;
	  }

	  public void addTransactionList(Transaction transactionObj) {
		  if(transactionList == null) {
			  transactionList = new ArrayList<Transaction>();
		  }
		  this.transactionList.add(transactionObj);
	  }
	
	  public int getId() { return id; }
	  
	  public String getName() { return name; }
	  
	  public  HashMap<String, Integer> getMonthlyReward(){
		  return monthlyreward;
	  }
	
	  public int getTotalReward() {
		  return totalReward;
	  }
	  
	  public List<Transaction> getTransactionList(){
		  
		  return transactionList;
	  }
}
