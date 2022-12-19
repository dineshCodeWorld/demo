package com.infy.tesla.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect 
{
	Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	//logs for controller layer-------------------------------
	
	@Before("execution(* com.infy.tesla.controller.UserController.home(..))")
   public void homeLog()
   {
	   logger.info("Entering the home method of controller layer");
   }
	@After("execution(* com.infy.tesla.controller.UserController.home(..))")
	   public void homeLog1()
	   {
		   logger.info("After executing the home method of controller layer");
	   }
//----------------------	
	
	@Before("execution(* com.infy.tesla.controller.UserController.register(..))")
	   public void registerLog()
	   {
		   logger.info("Entering the register method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.register(..))")
	   public void registerLog1()
	   {
		   logger.info("After executing the register method of controller layer");
	   }
//----------------------	
		
	@Before("execution(* com.infy.tesla.controller.UserController.validateToken(..))")
	   public void valiidateTokenLog()
	   {
		   logger.info("Entering the validate Token method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.validateToken(..))")
	   public void valiidateTokenLog1()
	   {
		   logger.info("After executing the validate Token method of controller layer");
	   }
//-----------------------		
	@Before("execution(* com.infy.tesla.controller.UserController.resendToken(..))")
	   public void resendTokenLog()
	   {
		   logger.info("Entering the resend Token method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.resendToken(..))")
	   public void resendTokenLog1()
	   {
		   logger.info("After executing the resend Token method of controller layer");
	   }
//-----------------------		
	@Before("execution(* com.infy.tesla.controller.UserController.resetPassword(..))")
	   public void resetPasswordLog()
	   {
		   logger.info("Entering the reset Password method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.resetPassword(..))")
	   public void resetPasswordLog1()
	   {
		   logger.info("After executing the reset Password method of controller layer");
	   }
//-----------------------		
	@Before("execution(* com.infy.tesla.controller.UserController.validatePasswordResetToken(..))")
	   public void validatePasswordResetTokenLog()
	   {
		   logger.info("Entering the validate Password Reset Token method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.validatePasswordResetToken(..))")
	   public void validatePasswordResetTokenLog1()
	   {
		   logger.info("After executing the validate Password Reset Token method of controller layer");
	   }
//-----------------------		
	@Before("execution(* com.infy.tesla.controller.UserController.changePassword(..))")
	   public void changePasswordLog()
	   {
		   logger.info("Entering the change password method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.changePassword(..))")
	   public void changePasswordLog1()
	   {
		   logger.info("After executing the change password method of controller layer");
	   }
//-----------------------		
	@Before("execution(* com.infy.tesla.controller.UserController.getAllUsersList(..))")
	   public void getAllUserListLog()
	   {
		   logger.info("Entering the get user list method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.getAllUsersList(..))")
	   public void getAllUserListLog1()
	   {
		   logger.info("After executing the get user list method of controller layer");
	   }
//----------------------		
	@Before("execution(* com.infy.tesla.controller.UserController.updateProfile(..))")
	   public void updateProfileLog()
	   {
		   logger.info("Entering the update profile method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.updateProfile(..))")
	   public void updateProfileLog1()
	   {
		   logger.info("After executing the update profile method of controller layer");
	   }
//----------------------		
	@Before("execution(* com.infy.tesla.controller.UserController.deleteUser(..))")
	   public void deleteUserLog()
	   {
		   logger.info("Entering the delete user method of controller layer");
	   }
	@After("execution(* com.infy.tesla.controller.UserController.deleteUser(..))")
	   public void deleteUserLog1()
	   {
		   logger.info("After executing the delete user method of controller layer");
	   }
		
	
//-----------------------------------------------------------------------------------
	
	//logs for service layer-------------------------------------------
	
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.register(..))")
	   public void registerLogs()
	   {
		   logger.info("Entering the register method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.register(..))")
	   public void registerLogs1()
	   {
		   logger.info("After executing the register method of SERVICE layer");
	   }
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.register(..))")
	public void afterThrowing()
	{
		logger.info("after throwing register method of SERVICE layer");
	}
//----------------------	
		
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.validateToken(..))")
	   public void valiidateTokenLogs()
	   {
		   logger.info("Entering the validate Token method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.validateToken(..))")
	   public void valiidateTokenLogs1()
	   {
		   logger.info("After executing the validate Token method of SERVICE layer");
	   }
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.validateToken(..))")
	public void afterThrowing1()
	{
		logger.info("after throwing validate Token method method of SERVICE layer");
	}
//-----------------------		
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.resendToken(..))")
	   public void resendTokenLogs()
	   {
		   logger.info("Entering the resend Token method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.resendToken(..))")
	   public void resendTokenLogs1()
	   {
		   logger.info("After executing the resend Token method of SERVICE layer");
	   }
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.resendToken(..))")
	public void afterThrowing2()
	{
		logger.info("after throwing resend token method of SERVICE layer");
	}
//-----------------------		
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.resetPassword(..))")
	   public void resetPasswordLogs()
	   {
		   logger.info("Entering the reset Password method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.resetPassword(..))")
	   public void resetPasswordLogs1()
	   {
		   logger.info("After executing the reset Password method of SERVICE layer");
	   }
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.resetPassword(..))")
	public void afterThrowing3()
	{
		logger.info("after throwing resetPassword method of SERVICE layer");
	}
//-----------------------		
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.validatePasswordResetToken(..))")
	   public void validatePasswordResetTokenLogs()
	   {
		   logger.info("Entering the validate Password Reset Token method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.validatePasswordResetToken(..))")
	   public void validatePasswordResetTokenLogs1()
	   {
		   logger.info("After executing the validate Password Reset Token method of SERVICE layer");
	   }
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.validatePasswordResetToken(..))")
	public void afterThrowing4()
	{
		logger.info("after throwing validate password reset token method of SERVICE layer");
	}
//-----------------------		
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.changePassword(..))")
	   public void changePasswordLogs()
	   {
		   logger.info("Entering the change password method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.changePassword(..))")
	   public void changePasswordLogs1()
	   {
		   logger.info("After executing the change password method of SERVICE layer");
	   }
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.changePassword(..))")
	public void afterThrowing5()
	{
		logger.info("after throwing change password method of SERVICE layer");
	}
//-----------------------		
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.getAllUsersList(..))")
	   public void getAllUserListLogs()
	   {
		   logger.info("Entering the get user list method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.getAllUsersList(..))")
	   public void getAllUserListLogs1()
	   {
		   logger.info("After executing the get user list method of SERVICE layer");
	   }
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.getAllUsersList(..))")
	public void afterThrowing6()
	{
		logger.info("after throwing get all users list method of SERVICE layer");
	}
//----------------------		
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.updateProfile(..))")
	   public void updateProfileLogs()
	   {
		   logger.info("Entering the update profile method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.updateProfile(..))")
	   public void updateProfileLogs1()
	   {
		   logger.info("After executing the update profile method of SERVICE layer");
	   }
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.updateProfile(..))")
	public void afterThrowing7()
	{
		logger.info("after throwing update profile method of SERVICE layer");
	}
//----------------------		
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.deleteUser(..))")
	   public void deleteUserLogs()
	   {
		   logger.info("Entering the delete user method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.deleteUser(..))")
	   public void deleteUserLogs1()
	   {
		   logger.info("After executing the delete user method of SERVICE layer");
	   }
		
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.deleteUser(..))")
	public void afterThrowing8()
	{
		logger.info("after throwing dlete user method of SERVICE layer");
	}
	
//-----------------------------------------------------------------------------------	
	//logs for event listener
	
	@Before("execution(* com.infy.tesla.event.EventListener.onApplicationEvent(..))")
	   public void onApplicationEventLog()
	   {
		   logger.info("Entering the onApplicationEvent  method of  EventListener");
	   }
	@After("execution(* com.infy.tesla.event.EventListener.onApplicationEvent(..))")
	   public void onApplicationEventLog1()
	   {
		   logger.info("After executing the onApplicationEvent  method of  EventListener");
	   }
		
	@AfterThrowing("execution(* com.infy.tesla.event.EventListener.onApplicationEvent(..))")
	public void onApplicationEventLogThrow()
	{
		logger.info("after throwing the onApplicationEvent  method of  EventListener");
	}
//---------------------------	
	
	@Before("execution(* com.infy.tesla.event.EventListener.sendSimpleMail(..))")
	   public void sendSimpleMailLog()
	   {
		   logger.info("Entering the sendSimpleMail  method of  EventListener");
	   }
	@After("execution(* com.infy.tesla.event.EventListener.sendSimpleMail(..))")
	   public void sendSimpleMailLog1()
	   {
		   logger.info("After executing the sendSimpleMail  method of  EventListener");
	   }
		
	@AfterThrowing("execution(* com.infy.tesla.event.EventListener.sendSimpleMail(..))")
	public void sendSimpleMailLogThrow()
	{
		logger.info("after throwing the sendSimpleMail  method of  EventListener");
	}
	
//-----------------------------
	@Before("execution(* com.infy.tesla.service.UserServiceImpl.changeRole(..))")
	   public void changeRoleLogs()
	   {
		   logger.info("Entering the change role method of SERVICE layer");
	   }
	@After("execution(* com.infy.tesla.service.UserServiceImpl.changeRole(..))")
	   public void changeRoleLogs1()
	   {
		   logger.info("After executing the change role method of SERVICE layer");
	   }
		
	@AfterThrowing("execution(* com.infy.tesla.service.UserServiceImpl.changeRole(..))")
	public void changeRoleAfterThrowing()
	{
		logger.info("after throwing change role method of SERVICE layer");
	}

}
