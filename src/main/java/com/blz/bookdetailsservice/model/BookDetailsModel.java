package com.blz.bookdetailsservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.blz.bookdetailsservice.dto.BookDetailsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookDetailsModel {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;
    @Column(name = "bookName")
    private String bookName;
    @Column  (name = "bookAuthor")
    private String bookAuthor;
    @Column (name = "bookDescription")
    private String bookDescription;  
    @Lob
	private byte[] bookLogo;
    @Column (name = "bookPrice")
    private int bookPrice;
    @Column (name = "bookQuantity")
    private int bookQuantity;
    
    public BookDetailsModel(BookDetailsDTO bookDTO) {
    	this.bookName = bookDTO.getBookName();
    	this.bookAuthor = bookDTO.getBookAuthor();
    	this.bookDescription = bookDTO.getBookDescription();
    	this.bookPrice = bookDTO.getBookPrice();
    	this.bookQuantity = bookDTO.getBookQuantity();
    }
}
