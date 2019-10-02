package com.team.alpha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.alpha.entity.BookLoan;
import com.team.alpha.service.BookLoanService;

@RestController
@RequestMapping(path="/administrator")
public class AdminLoanController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoanController.class);
	
	@Autowired
	private BookLoanService bookloanservice;
	
	@GetMapping(value="/bookloan/", produces = {"application/json", "application/xml"})
	public ResponseEntity<List<BookLoan>> getAllLoans() {
		List<BookLoan> loans = bookloanservice.getAllBookLoans();
		if (loans.isEmpty()) {
			LOGGER.debug("Could not retrieve list of book loans");
            return new ResponseEntity<List<BookLoan>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<BookLoan>>(loans, HttpStatus.OK);		
	}
	
		
	@PutMapping(value="/bookloan/{id}", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<BookLoan> updateLoan(@PathVariable("id") int id, @RequestBody BookLoan bookloan) {
		
        if (!bookloanservice.doesBookLoanExist(bookloan)) {
        	LOGGER.debug("Book loan with card number: "+id+" doesn't exist");
            return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
        }
        	bookloanservice.overrideDueDate(bookloan);
	    return new ResponseEntity<BookLoan>(bookloan, HttpStatus.OK);
	}
}
