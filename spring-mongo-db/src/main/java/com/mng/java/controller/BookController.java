package com.mng.java.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mng.java.book.repository.BookRepository;
import com.mng.java.model.Book;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@PostMapping("/add")
	public String save(@RequestBody Book book) {
		bookRepository.save(book);
		return "added successfully";
	}

	@GetMapping("/findAll")
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<Book> findById(@PathVariable(value="id") int id) {
		return bookRepository.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") int id) {
		bookRepository.deleteById(id);
		return "deleted book successfully";
	}
	
	@PutMapping("/update/{id}/{bookName}")
	public String update(@PathVariable(value="id") int id, @PathVariable(value="bookName") String bookName) {
		Optional<Book> book = bookRepository.findById(id);
		 if(book.isPresent()) {
			Book b = book.get();
			b.setBookName(bookName);
			bookRepository.save(b);
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
