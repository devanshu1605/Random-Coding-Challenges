package com.java.palindrome;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	
	public static void main(String[] args) {
		
		String sampleString= "aabaabbaabb";
		
		Set<String> count = getPalindromesAndCount(sampleString);
		System.out.println("Palindromes :"+count+"\nCount : "+count.size());
		
		
	}

	private static Set<String> getPalindromesAndCount(String sampleString) {
	    Set<String> palindromeSet = new HashSet<>();
	    for (int index = 0; index < sampleString.length(); index++) {
	    	Set<String> set =findAllPalindromes(index, index + 1,sampleString);
	    	palindromeSet.addAll(set);
	    	set = findAllPalindromes(index, index, sampleString);
	    	palindromeSet.addAll(set);
	    }
	    return palindromeSet;
	}

	private static Set<String> findAllPalindromes(int lowIndex, int highIndex, String sampleString) {
	    Set<String> setOfPalindromes = new HashSet<>();
	    while (lowIndex >= 0 && 
	    		highIndex < sampleString.length() && 
	    		sampleString.charAt(highIndex) == sampleString.charAt(lowIndex)) {
	    	String temp = sampleString.substring(lowIndex, highIndex + 1);
	    	if(temp.length()>2){
	    		setOfPalindromes.add(temp);
	    	}
	    	highIndex++;
	    	lowIndex--;   
	    }
	    return setOfPalindromes;
	}
	
}
