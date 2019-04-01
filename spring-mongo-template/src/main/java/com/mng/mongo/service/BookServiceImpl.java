package com.mng.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mng.mongo.model.Book;
import com.mongodb.client.result.DeleteResult;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void save(Book book) {
		mongoTemplate.insert(book,"Book");
	}

	@Override
	public Book findById(int id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, Book.class, "Book");
	}

	@Override
	public void update(Book book) {
		mongoTemplate.save(book);
		
	}

	@Override
	public DeleteResult deleteById(int id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.remove(query, Book.class,"Book");
	}

	@Override
	public List<Book> findAll() {
		return mongoTemplate.findAll(Book.class, "Book");
	}

}
