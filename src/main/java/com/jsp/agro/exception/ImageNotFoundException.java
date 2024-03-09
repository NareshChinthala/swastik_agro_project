package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ImageNotFoundException extends RuntimeException {
	private String msg="Image Not Found";

}
