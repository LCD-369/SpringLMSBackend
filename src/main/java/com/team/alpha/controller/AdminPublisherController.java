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

import com.team.alpha.entity.Publisher;
import com.team.alpha.service.PublisherService;

@RestController
@RequestMapping(path="/administrator")
public class AdminPublisherController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminPublisherController.class);
	
	@Autowired
	private PublisherService publisherservice;

	@GetMapping(value="/publisher/", produces = {"application/json", "application/xml"})
	public ResponseEntity<List<Publisher>> getAllPublishers(){
		List<Publisher> publishers = publisherservice.getAllPublishers();
		if (publishers.isEmpty()) {
			LOGGER.debug("Could not retrieve list of publishers");
            return new ResponseEntity<List<Publisher>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);		
	}

	@PostMapping(value="/publisher/", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	 public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher, UriComponentsBuilder ucBuilder) {
        if (publisherservice.doesPublisherExist(publisher)) {
        	LOGGER.debug("Publisher already exist");
            return new ResponseEntity<Publisher>(HttpStatus.CONFLICT);
        }
        publisherservice.addPublisher(publisher);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/administrator/publisher/{id}").buildAndExpand(publisher.getPublisherId()).toUri());
        return new ResponseEntity<Publisher>(headers, HttpStatus.CREATED);	
	}
	
	@DeleteMapping(value="/publisher/{id}")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable("id") int id) {    
        if (publisherservice.getPublisherById(id)==null) {
        	LOGGER.debug("Publisher doesn't exist");
            return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
        }
        publisherservice.deletePublisher(id);
        return new ResponseEntity<Publisher>(HttpStatus.NO_CONTENT);
    }
	

	@PutMapping(value="/publisher/{id}", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<Publisher> updatePublisher(@PathVariable("id") int id, @RequestBody Publisher publisher) {
        if (publisherservice.getPublisherById(id)==null) {
        	LOGGER.debug("Publisher doesn't exist");
            return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
        }    
	    publisherservice.updatePublisher(publisher);
	    return new ResponseEntity<Publisher>(publisher, HttpStatus.OK);
	}
}
