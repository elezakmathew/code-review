package com.spring.reward;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.spring.util.ReadExcel;

import model.Customer;
import model.Transaction;

public class AppMain {
	
	public static void main(String args[]) {
		
		ReadExcel readxl = new ReadExcel();
		HashMap<Integer, Customer> customerHashList = readxl.readExcelfile("transaction.xlsx");
		List<Transaction> transactionList ;

		Collection<Customer> customerList = customerHashList.values();
		for(Customer customerObj : customerList) {
		System.out.println("Customer : " + customerObj.getName());
		transactionList = customerObj.getTransactionList();
		ListIterator<Transaction> iterator = transactionList.listIterator();
		while(iterator.hasNext()) {
			Transaction transaction = iterator.next();
			int reward = RewardCalulator.reward(transaction.getAmount());
			transaction.setReward(reward);
		}
		
		//calculate reward per month
		HashMap<String, Integer> monthlyReward = RewardCalulator.rewardPerMonth(transactionList);
		for (Map.Entry<String, Integer> reward: monthlyReward.entrySet()) {
			System.out.println("Rewards for the month"+"\t"+ reward.getKey()+"\t" + reward.getValue());

		}

		//calculate total reward
		int totalReward = RewardCalulator.totalReward(transactionList);
		System.out.println("Total rewards for three months = "+ totalReward +"\n\n");
		
	}    
	}
	
}
