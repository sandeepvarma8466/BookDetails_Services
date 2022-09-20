package com.blz.bookdetailsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO {
    public String bookName;
    public String bookAuthor;
    public String bookDescription;
    public int bookPrice;
    public int bookQuantity;
}
