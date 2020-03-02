package model;

import java.util.Date;


/**
 * @author Elezabeth
 * Represents a transaction 
 *
 */
public class Transaction {
	
	
	private Date date;
	private Double amount;
	private int reward;
	
	
	public void setDate(Date date) {
		this.date = date;
	}
	public void setAmount(Double amount ) {
		this.amount = amount;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	
	
	
	public Date getDate() {
		return date;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public int getReward() {
		return reward;
	}
}
