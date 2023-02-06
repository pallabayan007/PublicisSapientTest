package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.google.gson.Gson;

import com.example.demo.model.*;
import com.example.demo.service.CreditCardService;


@RestController 
@RequestMapping(path = "/api/v1/cc")
public class CreditCardController {
	
	
	@Autowired  
	CreditCardService ccService;  
	
	Gson gson = new Gson();

    @Autowired
    CreditCardController(CreditCardService creditCardService) {
        this.ccService = creditCardService;
    }
    
    @GetMapping("/list")
    public Iterable<CreditCard> list() {
    	try {
			if(ccService.list() != null){				
				return ccService.list();
								
			}
			else {			
				throw new ResponseStatusException(
				           HttpStatus.NOT_ACCEPTABLE, "Credit card number is not valid!");
			}			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseStatusException(
			           HttpStatus.NOT_ACCEPTABLE, "Credit card update failed");
		}
        
    }
	
	@PostMapping(path="/add")
	private ResponseEntity<String> addCreditCard(@RequestBody CreditCard ccDetails) {
		
		try {
			if(ccService.isValidCreditCardNumber(ccDetails.getCardNumber())) {
				ccService.addCreditCard(ccDetails);
				System.out.println(ccDetails.getCardNumber());
				return new ResponseEntity<String>(gson.toJson("Save successful"), HttpStatus.OK);				
			}
			else {				
				return new ResponseEntity<String>(gson.toJson("Credit card number is not valid!"), HttpStatus.NOT_ACCEPTABLE);
			}			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseStatusException(
			           HttpStatus.NOT_ACCEPTABLE, "Credit card update failed");
		}
		
		
	}

}
