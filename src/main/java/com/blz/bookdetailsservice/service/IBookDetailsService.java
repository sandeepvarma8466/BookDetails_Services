package com.blz.bookdetailsservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blz.bookdetailsservice.dto.BookDetailsDTO;
import com.blz.bookdetailsservice.model.BookDetailsModel;

public interface IBookDetailsService {

	BookDetailsModel addBook(BookDetailsDTO bookDTO, String token);

	BookDetailsModel updateBook(Long bookId, BookDetailsDTO bookDTO, String token);

	List<BookDetailsModel> fetchAllBooks(String token);

	BookDetailsModel getBook(Long bookId, String token);

	BookDetailsModel deletebook(Long bookId, String token);

	BookDetailsModel changeQuantity(Long bookId, Integer bookQuantity, String token);

	BookDetailsModel updatePrice(Long bookId, Integer bookPrice, String token);
}
