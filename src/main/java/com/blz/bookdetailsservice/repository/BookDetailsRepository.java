package com.blz.bookdetailsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blz.bookdetailsservice.model.BookDetailsModel;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetailsModel, Long>{
	
	@Query(value="select * from books a where a.book_name= :bookName", nativeQuery = true)
    List<BookDetailsModel> searchBookByName(String bookName);
	
	@Query(value="select * from books a where a.book_author= :bookAuthor", nativeQuery = true)
    List<BookDetailsModel> searchBookByAuthor(String bookAuthor);
}
