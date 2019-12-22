package com.technical.challenge.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.technical.challenge.model.Data;

@Service
public class DataService {
	
	static final String patternCodes[] = { " ", " ","abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz" }; 
  
    public List<String> allCombinations(String phoneNumber) {
    	
        if (phoneNumber.length() == 0) {
        	ArrayList<String> emptyList = new ArrayList<>();
        	emptyList.add("");
        	
        	return emptyList;  
	    } 
	  
	    char character = phoneNumber.charAt(0); 
	    String remainDigits = phoneNumber.substring(1); 
	    
	    List<String> prevdigits = allCombinations(remainDigits); 
	    ArrayList<String> combinationsList = new ArrayList<>(); 
	  
	    String code = patternCodes[character - '0']; 
  
        for (String combination : prevdigits) { 
            for (int i = 0; i < code.length(); i++) { 
            	combinationsList.add(code.charAt(i) + combination); 
            } 
        } 
        Collections.sort(combinationsList);
        return combinationsList; 
    }
    
    public Data combinations(Integer phoneNumber, Integer pageNo, Integer pageSize){
    	String numberString = String.valueOf(phoneNumber);
    	List<String> combinationsList= allCombinations(numberString);
    	
    	Data finalData = new Data();
    	
    	int CombinationSize = combinationsList.size();
    	if (CombinationSize > (pageNo * pageSize)) {
    		Integer startIndex = (pageNo == 1? 0 : ((pageNo-1)*pageSize));
        	Integer endIndex = pageNo * pageSize;

        	finalData.setCount(CombinationSize);
        	finalData.setCombinations(combinationsList.subList(startIndex, endIndex));
        	return finalData;
    	} else {
    		return finalData;
    	}  	
    }
}
