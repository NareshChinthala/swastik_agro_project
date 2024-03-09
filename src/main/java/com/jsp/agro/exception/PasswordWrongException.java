package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordWrongException extends RuntimeException {

	private String msg="Entered Password is Wrong";
}
