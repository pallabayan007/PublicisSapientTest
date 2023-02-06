package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import java.util.Arrays;

import com.example.demo.model.*;
import com.example.demo.repository.*;


@Service
public class CreditCardService {
	
	@Autowired  
	CreditCardRepository ccRepo;
	
	public void addCreditCard(CreditCard ccDetails) {
//	public void addCreditCard(List<CreditCard> ccDetails) {
		
//		ccRepo.saveAll(ccDetails);
		ccRepo.save(ccDetails);
		
	}
	
	public Iterable<CreditCard> list() {
        return ccRepo.findAll();
    }
	
//	Luhn algorithm for card validation
	public boolean isValidCreditCardNumber(String cardNumber)
    {
        // int array for processing the cardNumber
        int[] cardIntArray=new int[cardNumber.length()];
 
        for(int i=0;i<cardNumber.length();i++)
        {
            char c= cardNumber.charAt(i);
            cardIntArray[i]=  Integer.parseInt(""+c);
        }
 
        for(int i=cardIntArray.length-2;i>=0;i=i-2)
        {
            int num = cardIntArray[i];
            num = num * 2;  // step 1
            if(num>9)
            {
                num = num%10 + num/10;  // step 2
            }
            cardIntArray[i]=num;
        }
 
        int sum = sumDigits(cardIntArray);  // step 3
 
        System.out.println(sum);
 
        if(sum%10==0)  // step 4
        {
            return true;
        }
 
        return false;
 
    }
 
    public static int sumDigits(int[] arr)
    {
        return Arrays.stream(arr).sum();
    }


}
