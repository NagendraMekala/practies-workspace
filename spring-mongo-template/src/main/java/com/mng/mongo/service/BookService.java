package com.mng.mongo.service;

import java.util.List;

import com.mng.mongo.model.Book;
import com.mongodb.client.result.DeleteResult;

public interface BookService {
	
    public void save(Book book);
	
	public Book findById(int id);
	
	public void update(Book book);
	
	public DeleteResult deleteById(int id);
	
	public List<Book> findAll();

}
