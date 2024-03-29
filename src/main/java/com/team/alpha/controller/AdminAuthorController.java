package com.team.alpha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.team.alpha.entity.Author;
import com.team.alpha.service.AuthorService;

@RestController
@CrossOrigin(origins="http://localhost:8081")
@RequestMapping(path="/administrator")
public class AdminAuthorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminAuthorController.class);
	
	@Autowired
	private AuthorService authorservice;
	
	@GetMapping(value="/author/", produces = {"application/json", "application/xml"})
	public ResponseEntity<List<Author>> getAllAuthors(){
		List<Author> authors = authorservice.getAllAuthors();
		LOGGER.info("test");
		if (authors.isEmpty()) {
            return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);		
	}
	
	@PostMapping(value="/author/", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<Author> createAuthor(@RequestBody Author author, UriComponentsBuilder ucBuilder) {
		if (authorservice.doesAuthorExist(author)) {
			LOGGER.info("Author already exist");
            return new ResponseEntity<Author>(HttpStatus.CONFLICT);  
        } else {
        author.setAuthorId(0);	
        authorservice.addAuthor(author);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/administrator/author/").buildAndExpand(author.getAuthorId()).toUri());
        return new ResponseEntity<Author>(headers, HttpStatus.CREATED);	
        }
	}
	
	@DeleteMapping(value="/author/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable("id") int id) {    
        Author author = authorservice.findAuthorById(id);
        if (author == null) {
            return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
        }
        authorservice.removeAuthor(id);
        return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
    }
		
	@PutMapping(value="/author/{id}", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<Author> updateAuthor(@PathVariable("id") int id, @RequestBody Author author) {
		Author tempAuthor = authorservice.findAuthorById(id);
        if (tempAuthor == null) {
            return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
        }    
	    tempAuthor.setName(author.getName());
	    authorservice.updateAuthor(tempAuthor);
	    return new ResponseEntity<Author>(tempAuthor, HttpStatus.OK);
	}
}
