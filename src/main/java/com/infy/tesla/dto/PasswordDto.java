package com.infy.tesla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDto 
{
  private String email;
  private String oldPassword;
  private String newPassword;
}
