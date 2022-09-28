package com.blz.bookdetailsservice.service;

import java.util.List;

import com.blz.bookdetailsservice.dto.BookDetailsDTO;
import com.blz.bookdetailsservice.model.BookDetailsModel;
import com.blz.bookdetailsservice.util.BookResponse;

public interface IBookDetailsService {

	BookDetailsModel addBook(BookDetailsDTO bookDTO, String token);

	BookDetailsModel updateBook(Long bookId, BookDetailsDTO bookDTO, String token);

	List<BookDetailsModel> fetchAllBooks(String token);

	BookDetailsModel getBook(Long bookId, String token);

	BookDetailsModel deletebook(Long bookId, String token);

	BookDetailsModel changeQuantity(Long bookId, Integer bookQuantity, String token);

	BookDetailsModel updatePrice(Long bookId, Integer bookPrice, String token);

	BookResponse validateBookId(Long bookId);

	BookResponse updateBookQuantity(Long bookId, Integer bookQuantity);

	BookResponse updateQuantity(Long bookId, Integer bookQuantity);

	List<BookDetailsModel> searchBookByName(String bookName);

	List<BookDetailsModel> searchBookByAuthor(String bookAuthor);
}
