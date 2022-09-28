package com.blz.bookdetailsservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blz.bookdetailsservice.dto.BookDetailsDTO;
import com.blz.bookdetailsservice.exception.BookDetailsNotFoundException;
import com.blz.bookdetailsservice.model.BookDetailsModel;
import com.blz.bookdetailsservice.repository.BookDetailsRepository;
import com.blz.bookdetailsservice.util.BookResponse;
import com.blz.bookdetailsservice.util.TokenUtil;

/*
 * Purpose : Service implementation of BookDetails
 * @author : Sanddep Varma Dubyala
 * @version : 1.0
 * @date : 20-09-2022
 */

@Service
public class BookDetailsService implements IBookDetailsService{
	@Autowired
	BookDetailsRepository bookDetailsRepository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	TokenUtil tokenUtil;

	/**
	 *  Purpose:SERVICE FOR addbook details to BookStore 
	 */

	@Override
	public BookDetailsModel addBook(BookDetailsDTO bookDTO, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USER-SERVICE:8049/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			BookDetailsModel bookModel = new BookDetailsModel(bookDTO);
			bookDetailsRepository.save(bookModel);
			return bookModel;
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:SERVICE FOR update book details
	 */

	@Override
	public BookDetailsModel updateBook(Long bookId, BookDetailsDTO bookDTO, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USER-SERVICE:8049/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookDetailsModel> isBookPresent = bookDetailsRepository.findById(bookId);
			if (isBookPresent.isPresent()) {
				isBookPresent.get().setBookName(bookDTO.getBookName());
				isBookPresent.get().setBookDescription(bookDTO.getBookDescription());
				isBookPresent.get().setBookAuthor(bookDTO.getBookAuthor());
				bookDetailsRepository.save(isBookPresent.get());
				return isBookPresent.get();
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:SERVICE FOR fetch all books Details from BookStore 
	 */

	@Override
	public List<BookDetailsModel> fetchAllBooks(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USER-SERVICE:8049/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			List<BookDetailsModel> checkBooksPresent = bookDetailsRepository.findAll();
			if (checkBooksPresent.size()>0) {
				return checkBooksPresent;
			}
			throw new BookDetailsNotFoundException(500, "Book List is Empty");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:SERVICE FOR getbook details from BookStore 
	 */

	@Override
	public BookDetailsModel getBook(Long bookId, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USER-SERVICE:8049/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookDetailsModel> isBooksPresent = bookDetailsRepository.findById(bookId);
			if (isBooksPresent.isPresent()) {
				return isBooksPresent.get();
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:SERVICE FOR delete book details from BookStore 
	 */

	@Override
	public BookDetailsModel deletebook(Long bookId, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USER-SERVICE:8049/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookDetailsModel> isBooksPresent = bookDetailsRepository.findById(bookId);
			if (isBooksPresent.isPresent()) {
				bookDetailsRepository.delete(isBooksPresent.get());
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:SERVICE FOR change quantity of book details
	 */

	@Override
	public BookDetailsModel changeQuantity(Long bookId, Integer bookQuantity, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USER-SERVICE:8049/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookDetailsModel> isBooksPresent = bookDetailsRepository.findById(bookId);
			if (isBooksPresent.isPresent()) {
				isBooksPresent.get().setBookQuantity(isBooksPresent.get().getBookQuantity()+bookQuantity);
				bookDetailsRepository.save(isBooksPresent.get());
				return isBooksPresent.get();
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:SERVICE FOR update price of book
	 */

	@Override
	public BookDetailsModel updatePrice(Long bookId, Integer bookPrice, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USER-SERVICE:8049/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookDetailsModel> isBooksPresent = bookDetailsRepository.findById(bookId);
			if (isBooksPresent.isPresent()) {
				isBooksPresent.get().setBookPrice(bookPrice);
				bookDetailsRepository.save(isBooksPresent.get());
				return isBooksPresent.get();
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}
	
	/**
	 *  Purpose:SERVICE FOR validate book
	 */

	@Override
	public BookResponse validateBookId(Long bookId) {
		Optional<BookDetailsModel> isBookPresent = bookDetailsRepository.findById(bookId);
		if (isBookPresent.isPresent()) {
			return new BookResponse(200,"User Validate Successfully",isBookPresent.get());
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}
	
	/**
	 *  Purpose:SERVICE FOR increase quantity of book
	 */

	@Override
	public BookResponse updateBookQuantity(Long bookId, Integer bookQuantity) {
		Optional<BookDetailsModel> isBooksPresent = bookDetailsRepository.findById(bookId);
		if (isBooksPresent.isPresent()) {
			isBooksPresent.get().setBookQuantity(isBooksPresent.get().getBookQuantity() - bookQuantity);
			bookDetailsRepository.save(isBooksPresent.get());
			return new BookResponse(200,"User Update Successfully",isBooksPresent.get());
		}
		throw new BookDetailsNotFoundException(500, "Book Not Found");
	}
	
	/**
	 *  Purpose:SERVICE FOR decrease quantity of book
	 */

	@Override
	public BookResponse updateQuantity(Long bookId, Integer bookQuantity) {
		Optional<BookDetailsModel> isBooksPresent = bookDetailsRepository.findById(bookId);
		if (isBooksPresent.isPresent()) {
			isBooksPresent.get().setBookQuantity(isBooksPresent.get().getBookQuantity() + bookQuantity);
			bookDetailsRepository.save(isBooksPresent.get());
			return new BookResponse(200,"User Update Successfully",isBooksPresent.get());
		}
		throw new BookDetailsNotFoundException(500, "Book Not Found");
	}

	@Override
	public List<BookDetailsModel> searchBookByName(String bookName) {
		List<BookDetailsModel> isBookPresent = bookDetailsRepository.searchBookByName(bookName);
		if (isBookPresent.isEmpty()) {
			throw new BookDetailsNotFoundException(500, "Book Name Not Found");
		}
		else {
			return isBookPresent;
		}
	}

	@Override
	public List<BookDetailsModel> searchBookByAuthor(String bookAuthor) {
		List<BookDetailsModel> isBookPresent = bookDetailsRepository.searchBookByAuthor(bookAuthor);
		if (isBookPresent.isEmpty()) {
			throw new BookDetailsNotFoundException(500, "Author Name Not Found");
		}
		else {
			return isBookPresent;
		}
	}
}
