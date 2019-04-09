package com.example;
import java.util.ArrayList;
import java.util.List;

public class BlockChain {
	   ArrayList<ArrayList<String>> chain;  
	   List<String> current_transactions;
	   String transaction_sender;
	   String transaction_receiver;	   
	   String transaction_amount;
	   ArrayList<String> nodes;
	   
	   //create an object of SingleObject
	   private static BlockChain instance = new BlockChain();

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private BlockChain(){
		   this.chain = new ArrayList<ArrayList<String>>();
		   this.nodes = new ArrayList<String>();
		   this.current_transactions = new ArrayList<String>();
		   
	   }
	   //should append the first 2 params to the inner array that the outer is ARRAY<<ARRAY>> 
	   //check length to divide the transactions
	   public void new_block(int proof, String previous_hash) {
		   if(current_transactions.isEmpty()) {
			   return;
		   }
		   ArrayList<String> block=new ArrayList<String>();
		   String index = String.valueOf(chain.size());
		   block.add(index);
		   block.add(String.valueOf(java.time.LocalDateTime.now()));
		   for(int i = 0;i<current_transactions.size();i++) {
			   block.add(current_transactions.get(i));
		   }
		   block.add(String.valueOf(proof));
		   block.add(previous_hash);
		   chain.add(block);
		   return;
	   }
	   
	   public void new_transaction(String sender, String recipient, String amt) {
		   current_transactions.add(sender);
		   current_transactions.add(recipient);
		   current_transactions.add(amt);
		   return;
	   }

	   //Get the only object available
	   public static BlockChain getInstance(){
	      return instance;
	   }
	   
	   public ArrayList<ArrayList<String>> getChain(){
		   return this.chain;
	   }
	   
	   public List<String> getTransactions(){
		   return this.current_transactions;
	   }	   
}