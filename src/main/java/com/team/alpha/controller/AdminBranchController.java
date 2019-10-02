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

import com.team.alpha.entity.LibraryBranch;
import com.team.alpha.service.LibraryBranchService;

@RestController
@RequestMapping(path="/administrator")
public class AdminBranchController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBranchController.class);
	
	@Autowired
	private LibraryBranchService branchservice;
		
	@GetMapping(value="/librarybranch/", produces = {"application/json", "application/xml"})
	public ResponseEntity<List<LibraryBranch>> getAllBranches(){
		List<LibraryBranch> branches = branchservice.getAllBranches();
		if (branches.isEmpty()) {
			LOGGER.debug("Could not retrieve list of library branches");
            return new ResponseEntity<List<LibraryBranch>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<LibraryBranch>>(branches, HttpStatus.OK);		
	}
		
	@PostMapping(value="/librarybranch/", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	 public ResponseEntity<LibraryBranch> createBranch(@RequestBody LibraryBranch branch, UriComponentsBuilder ucBuilder) {
        if (branchservice.doesBranchExist(branch)) {
        	LOGGER.debug("Library branch already exist");
            return new ResponseEntity<LibraryBranch>(HttpStatus.CONFLICT);
        }
        branchservice.addBranch(branch);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/administrator/librarybranch/{id}").buildAndExpand(branch.getBranchId()).toUri());
        return new ResponseEntity<LibraryBranch>(headers, HttpStatus.CREATED);	
	}
	
	@DeleteMapping(value="/librarybranch/{id}")
    public ResponseEntity<LibraryBranch> deleteBranch(@PathVariable("id") int id) {    
        if (branchservice.getBranchById(id)==null) {
        	LOGGER.debug("Library branch doesn't exist");
            return new ResponseEntity<LibraryBranch>(HttpStatus.NOT_FOUND);
        }
        branchservice.deleteBranch(id);
        return new ResponseEntity<LibraryBranch>(HttpStatus.NO_CONTENT);
    }
		
	@PutMapping(value="/librarybranch/{id}", consumes= {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<LibraryBranch> updateBranch(@PathVariable("id") int id, @RequestBody LibraryBranch branch) {
        if (branchservice.getBranchById(id)==null) {
        	LOGGER.debug("Library branch doesn't exist");
            return new ResponseEntity<LibraryBranch>(HttpStatus.NOT_FOUND);
        }    
	    branchservice.updateBranch(branch);
	    return new ResponseEntity<LibraryBranch>(branch, HttpStatus.OK);
	}
}
