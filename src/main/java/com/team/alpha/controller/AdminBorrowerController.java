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

import com.team.alpha.entity.Borrower;
import com.team.alpha.service.BorrowerService;

@RestController
@RequestMapping(path="/administrator")
public class AdminBorrowerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBorrowerController.class);
	
	@Autowired
	private BorrowerService borrowerservice;
	
	@GetMapping(value="/borrower/",produces = {"application/json", "application/xml"})
	public ResponseEntity<List<Borrower>> getAllBorrowers(){
		List<Borrower> borrowers = borrowerservice.getAllBorrowers();
		if (borrowers.isEmpty()) {
			LOGGER.debug("Could not retrieve list of borrowers");
            return new ResponseEntity<List<Borrower>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Borrower>>(borrowers, HttpStatus.OK);		
	}
	
	@PostMapping(value="/borrower/", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	 public ResponseEntity<String> createBorrower(@RequestBody Borrower borrower, UriComponentsBuilder ucBuilder) {
        if (borrowerservice.doesBorrowerExist(borrower)) {
        	LOGGER.debug("This borrower already exist");
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        } 
        borrowerservice.addBorrower(borrower);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/administrator/borrower/{id}").buildAndExpand(borrower.getCardNo()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);	
	}
	
	@DeleteMapping(value="/borrower/{cardNo}")
    public ResponseEntity<Borrower> deleteBorrower(@PathVariable("cardNo") int cardNo) {    
		Borrower borrower = borrowerservice.getBorrowerById(cardNo);
        if (borrower == null) {
        	LOGGER.debug("This borrower doesn't exist");
            return new ResponseEntity<Borrower>(HttpStatus.NOT_FOUND);
        } 
        borrowerservice.deleteBorrower(cardNo);
        return new ResponseEntity<Borrower>(HttpStatus.NO_CONTENT);
    }
		
	@PutMapping(value="/borrower/{cardNo}", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<Borrower> updateBorrower(@PathVariable("cardNo") int cardNo, @RequestBody Borrower borrower) {
		Borrower tempBorrower = borrowerservice.getBorrowerById(cardNo);
        if (tempBorrower == null) {
        	LOGGER.debug("This borrower doesn't exist");
            return new ResponseEntity<Borrower>(HttpStatus.NOT_FOUND);
        }  
	    borrowerservice.updateBorrower(borrower);
	    return new ResponseEntity<Borrower>(borrower, HttpStatus.OK);
	}
}
