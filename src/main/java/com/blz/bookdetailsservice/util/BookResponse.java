package com.blz.bookdetailsservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
	private int errorCode;
	private String message;
	private Object token;
	
	public BookResponse(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
}
