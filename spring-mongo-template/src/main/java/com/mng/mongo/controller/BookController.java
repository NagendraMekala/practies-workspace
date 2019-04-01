package com.mng.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mng.mongo.model.Book;
import com.mng.mongo.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/add")
	public String save(@RequestBody Book book) {
		bookService.save(book);
		return "added successfully";
	}

	@GetMapping("/findAll")
	public List<Book> findAllBooks() {
		return bookService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Book findById(@PathVariable(value="id") int id) {
		return bookService.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") int id) {
		bookService.deleteById(id);
		return "deleted book successfully";
	}
	
	@PutMapping("/update/{id}/{bookName}")
	public String update(@PathVariable(value="id") int id, @PathVariable(value="bookName") String bookName) {
		Book book = bookService.findById(id);
		 if(book != null) {
			 book.setBookName(bookName);
			 bookService.update(book);
		  return "book updated";
		 }
		return "book not exixts";
	}

	@GetMapping("/hello")
	public String hello() {
		return "well come mongo db";
	}

	@GetMapping("/get")
	public Book temp() {
		Book b = new Book();
		b.setId(101);
		b.setBookName("java");
		b.setAuthorName("james");
		return b;
	}
}
