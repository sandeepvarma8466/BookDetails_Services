package com.blz.bookdetailsservice.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO {
	@NotNull
    public String bookName;
	@NotNull
    public String bookAuthor;
	@NotNull
    public String bookDescription;
	@NotNull
    public int bookPrice;
	@NotNull
    public int bookQuantity;
}
