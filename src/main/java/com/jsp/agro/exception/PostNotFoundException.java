package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostNotFoundException extends RuntimeException{
	private String msg="post  not found";

}
