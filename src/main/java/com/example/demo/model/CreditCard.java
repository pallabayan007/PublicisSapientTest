package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table (name = "credit_card")
public class CreditCard {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
    private int serialId;
    @Column
    private String cardNumber;
    @Column
    private String custName;
    @Column
    private String creditLimit;
    
    public CreditCard() { 
    	
    }
    
    public CreditCard(String cardNumber, String custName, String creditLimit) {
    	
    	this.cardNumber = cardNumber;
    	this.custName = custName;
    	this.creditLimit = creditLimit;
    	
    }
    
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
    
   

}
