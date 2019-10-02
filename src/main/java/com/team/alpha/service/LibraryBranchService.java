package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team.alpha.dao.LibraryBranchDao;
import com.team.alpha.entity.LibraryBranch;

@Service
public class LibraryBranchService {
	
	@Autowired
	LibraryBranchDao librarybranchdao;
	
	public List<LibraryBranch> getAllBranches() {
		return librarybranchdao.findAll();
	}
	
	public LibraryBranch getBranchById(int branchId){
		LibraryBranch tempBranch = librarybranchdao.findById(branchId).get();
		return tempBranch;
	}
	public Boolean doesBranchExist(LibraryBranch branch) {
		LibraryBranch temp = librarybranchdao.findByName(branch.getBranchName());
		if(temp==null) {
			return false;
		} else {
			return true;
		}	
	}
	
	public void addBranch(LibraryBranch branch) {
		 librarybranchdao.save(branch);
	}
	
	public void deleteBranch(int id) {
		librarybranchdao.deleteById(id);
	}
	
	public void updateBranch(LibraryBranch branch) {
		librarybranchdao.save(branch);
	}
}