package com.spring.reward;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import model.Transaction;


/**
 * @author Elezabeth
 * Calculates rewards per moth and total reward for each customer
 *
 */
public class RewardCalulator {

	public static int reward(Double price) {
		int reward = 0;

		if(price > 100) {
			reward =  (int)(price-100) * 2 + 50;
		}else if(price > 50) {
			reward = (int)(price - 50);
		}
		
		return reward;
	}
	
	
	/**
	 * 
	 * @param transactionList
	 * @return list of monthly rewards
	 */
	public static HashMap<String, Integer> rewardPerMonth(List<Transaction> transactionList){
		HashMap<String, Integer> monthlyReward = new HashMap<String, Integer>();
		ListIterator<Transaction> iterator = transactionList.listIterator();

		while(iterator.hasNext()) {
			
			Transaction transaction = iterator.next();
			LocalDate localDate = transaction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int year  = localDate.getYear();
			int month = localDate.getMonthValue();
			String key = Integer.toString(month)+"/"+Integer.toString(year);
			

			if(monthlyReward.containsKey(key)) {
				
				monthlyReward.put(key, monthlyReward.get(key) + transaction.getReward());
			}else
				monthlyReward.put(key,transaction.getReward());
		}
		
		return monthlyReward;
	}
	
	/**
	 * 
	 * @param transactionList
	 * @return total reward
	 */
	public static int totalReward(List<Transaction> transactionList) {
		int totalReward = 0;
		ListIterator<Transaction> iterator = transactionList.listIterator();
		while(iterator.hasNext()) {
			
		totalReward += iterator.next().getReward();
		}
		
		return totalReward;
	}
}
