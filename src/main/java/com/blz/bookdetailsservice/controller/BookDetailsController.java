package com.blz.bookdetailsservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blz.bookdetailsservice.dto.BookDetailsDTO;
import com.blz.bookdetailsservice.model.BookDetailsModel;
import com.blz.bookdetailsservice.service.IBookDetailsService;
import com.blz.bookdetailsservice.util.BookResponse;

/*
 * Purpose : BookDetails Controller to process BookDetails Data API's
 * @author : Sanddep Varma Dubyala
 * @version : 1.0
 * @date : 20-09-2022
 */

@RestController
@RequestMapping("/bookdetails")
public class BookDetailsController {
	@Autowired
	IBookDetailsService bookDetailsService;
	
	/*
     *@Purpose : Api for add book into the bookstore
     *@Param : bookDTO and token
     */
	
	@PostMapping("/addbook")
	public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookDetailsDTO bookDTO, @RequestHeader String token) {
		BookDetailsModel bookModel = bookDetailsService.addBook(bookDTO, token);
		BookResponse bookResponse = new BookResponse(200, "Book Added Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api for update book details 
     *@Param : bookId, bookDTO and token
     */
	
	@PutMapping("/updatebook/{bookId}")
	public ResponseEntity<BookResponse> updatebook(@PathVariable Long bookId,@Valid @RequestBody BookDetailsDTO bookDTO, @RequestHeader String token) {
		BookDetailsModel bookModel = bookDetailsService.updateBook(bookId, bookDTO, token);
		BookResponse bookResponse = new BookResponse(200, "Book Updated Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api for get all book details 
     *@Param : token
     */
	
	@GetMapping("/fetchallbooks")
	public ResponseEntity<BookResponse> fetchAllBooks(@RequestHeader String token) {
		List<BookDetailsModel> bookModel = bookDetailsService.fetchAllBooks(token);
		BookResponse bookResponse = new BookResponse(200, "Fetch All Books Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api for fetch book details 
     *@Param : bookId, token
     */
	
	@GetMapping("/fetchbookby/{bookId}")
	public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId, @RequestHeader String token) {
		BookDetailsModel bookModel = bookDetailsService.getBook(bookId, token);
		BookResponse bookResponse = new BookResponse(200, "Fetch All Books Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api for delete book details 
     *@Param : bookId, token
     */
	
	@DeleteMapping("/deletebook/{bookId}")
	public ResponseEntity<BookResponse> deletebook(@PathVariable Long bookId, @RequestHeader String token) {
		BookDetailsModel bookModel = bookDetailsService.deletebook(bookId, token);
		BookResponse bookResponse = new BookResponse(200, "Delete Book Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api for up quantity of book  
     *@Param : bookId, bookQuantity and token
     */
	
	@PutMapping("/updatequantity/{bookId}")
	public ResponseEntity<BookResponse> changeQuantity(@PathVariable Long bookId,@RequestParam Integer bookQuantity,  @RequestHeader String token) {
		BookDetailsModel bookModel = bookDetailsService.changeQuantity(bookId, bookQuantity, token);
		BookResponse bookResponse = new BookResponse(200, "Quantity Updated Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api for update quantity of book  
     *@Param : bookId, bookPrice and token
     */
	
	@PutMapping("/updateprice/{bookId}")
	public ResponseEntity<BookResponse> updatePrice(@PathVariable Long bookId,@RequestParam Integer bookPrice,  @RequestHeader String token) {
		BookDetailsModel bookModel = bookDetailsService.updatePrice(bookId, bookPrice, token);
		BookResponse bookResponse = new BookResponse(200, "Price Updated Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api for validate bookId  
     *@Param : bookId
     */
	
	@GetMapping("/validatebookId/{bookId}")
	public BookResponse validateBookId(@PathVariable Long bookId) {
		return bookDetailsService.validateBookId(bookId);
	}
	
	/*
     *@Purpose : Api for increase quantity of books
     *@Param : bookId, bookQuantity
     */
	
	@GetMapping("/updatebookquantity/{bookId}/{bookQuantity}")
	public BookResponse updateBookQuantity(@PathVariable Long bookId, @PathVariable Integer bookQuantity) {
		BookResponse book =bookDetailsService.updateBookQuantity(bookId,bookQuantity);
		return new BookResponse(200, "user found", book);
	}
	
	/*
     *@Purpose : Api for decrease quantity of books
     *@Param : bookId, bookQuantity
     */
	
	@GetMapping("/updatequantity/{bookId}/{bookQuantity}")
	public BookResponse updateQuantity(@PathVariable Long bookId, @PathVariable Integer bookQuantity) {
		BookResponse book =bookDetailsService.updateQuantity(bookId,bookQuantity);
		return new BookResponse(200, "user found", book);
	}
	
	//search book by book name
    @GetMapping("/searchByName/{bookName}")
    public ResponseEntity<BookResponse> searchBookByName(@PathVariable String  bookName){
    	List<BookDetailsModel> bookModel = bookDetailsService.searchBookByName(bookName);
    	BookResponse bookResponse = new BookResponse(200, "Book Found !!", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
    }
    
  //search book by author name
    @GetMapping("/searchByAuthor/{bookAuthor}")
    public ResponseEntity<BookResponse> searchBookByAuthor(@PathVariable String  bookAuthor){
    	List<BookDetailsModel> bookModel = bookDetailsService.searchBookByAuthor(bookAuthor);
    	BookResponse bookResponse = new BookResponse(200, "Book Found !!", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
    }
}
