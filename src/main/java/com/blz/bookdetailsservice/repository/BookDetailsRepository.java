package com.blz.bookdetailsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blz.bookdetailsservice.model.BookDetailsModel;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetailsModel, Long>{

}
