package com.team.alpha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.team.alpha.entity.Book;
import com.team.alpha.service.BookService;

@RestController
@RequestMapping(path="/administrator")
public class AdminBookController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBookController.class);
	
	@Autowired
	private BookService bookservice;
		
	@GetMapping(value="/book/", produces = {"application/json", "application/xml"})
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> books = bookservice.getAllBooks();
		if (books.isEmpty()) {
			LOGGER.debug("Could not retrieve list of books");
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);		
	}
	
	@PostMapping(value="/book/", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	 public ResponseEntity<Book> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        if (bookservice.doesBookExist(book)) {
        	LOGGER.debug("Book already exist");
            return new ResponseEntity<Book>(HttpStatus.CONFLICT);
        }else {
            book.setBookId(0);	
            bookservice.addBook(book);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/administrator/book/").buildAndExpand(book.getBookId()).toUri());
            return new ResponseEntity<Book>(headers, HttpStatus.CREATED);	
            }	
	}
	
	@DeleteMapping(value="/book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id) {    
		Book book = bookservice.getBookById(id);
        if (book == null) {
        	LOGGER.debug("Book doesn't exist");
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        bookservice.deleteBook(book.getBookId());
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }
		
	@PutMapping(value="/book/{id}", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
		Book tempBook = bookservice.getBookById(id);
		Book updatedBook = null;
        if (tempBook == null) {
        	LOGGER.info("Book doesn't exist");
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }    
	    updatedBook = new Book(book.getBookId(), book.getTitle(), book.getAuthorId(), book.getPublisherId());
	    bookservice.updateBook(updatedBook);
	    return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
	}
}
