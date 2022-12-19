package com.infy.tesla.entity;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Token 
{
	private static final int EXPIRY_TIME = 10; //10 minutes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long tokenId;	
   private String token;
   private Date expiryDate;
   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id",nullable = false)
   private User user;
  
   public Token(String token,User user)
   {
	   this.token = token;
	   this.user = user;
	   this.expiryDate = calculateExpiryDate(Calendar.getInstance());
   }
   
   private Date calculateExpiryDate(Calendar calendar)
   {
	  calendar.setTimeInMillis(new Date().getTime());
	  calendar.add(calendar.MINUTE, EXPIRY_TIME);
	  return new Date(calendar.getTime().getTime());
   }
}
