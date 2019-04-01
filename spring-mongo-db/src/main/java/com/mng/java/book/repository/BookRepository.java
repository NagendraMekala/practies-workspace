package com.mng.java.book.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mng.java.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

}
