package com.example;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChainServices {
	private static void mine() {
	    BlockChain bcObject = BlockChain.getInstance();
	    int lastBlockIndex = bcObject.getChain().size() - 1;
		ArrayList<String> last_block = bcObject.getChain().get(lastBlockIndex);
		int lastProof = Integer.parseInt(last_block.get(5));
		int newProof;
	}
	
    public static boolean valid_chain(ArrayList<String> otherchain, ArrayList<String> selfchain) {
        int k = otherchain.size();
    	int m = selfchain.size();
    	int i;
    	if(k==m) {
        	for(i=0; otherchain.get(i).equals(selfchain.get(i));i++) {}
        	if(i==k-1) {
        		return true;
        	}
        	else {
        		return false;
        	}
        }
    	else {
    		return false;
    	}
    } 
    
    public static void resolve_conflicts(ArrayList<ArrayList<String>> selfchain, ArrayList<String> nodes) {
    	//need to use node list to query other chain's length
    	int maxLength = selfchain.size();
    	int nodeCount = nodes.size()-1;
    	try {
    		for(int i=0;i<nodeCount;i++) {
    	    URL url = new URL(nodes.get(i)+"/chain");
    	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    	    con.setRequestMethod("GET");
    		}
    		}
    	catch(Exception e) {}	
    }
    
    private static int proof_of_work(int last_proof) {
    	int proof = 0;
    	while(!valid_proof(last_proof, proof)) {
    		proof += 1;
    	}
    	
    	return proof;
    }
    
    private static boolean valid_proof(int last_proof,int proof) {
    	
    	return true;
    }
    
    public static String hash(String msg) {
    	try {
    	MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	byte[] hash = digest.digest(msg.getBytes(StandardCharsets.UTF_8));
        return String.valueOf(hash);
    	}
    	catch(Exception e) {
    		return "Error";
    	}
    }

}
