package com.infy.tesla.event;

import org.springframework.context.ApplicationEvent;
import com.infy.tesla.entity.User;

import lombok.Data;
@Data
public class RegistrationCompleteEvent extends ApplicationEvent
{
   User user;
   String url;
	
	public RegistrationCompleteEvent(User user,String url) {
		super(user);
      this.user = user;
      this.url = url;
	}

}
