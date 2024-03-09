package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailWrongException extends RuntimeException {

	private  String msg="Entered Email is Wrong";
}
