package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.BorrowerDao;
import com.team.alpha.entity.Borrower;

@Service
public class BorrowerService {
	
	@Autowired
	BorrowerDao borrowerdao;
	
	public List<Borrower> getAllBorrowers() {
		return borrowerdao.findAll();
	}
	
	public Borrower getBorrowerById(int cardNo){
		Borrower tempBorrower = borrowerdao.findById(cardNo).get();
		return tempBorrower;
	}
	public Boolean doesBorrowerExist(Borrower borrower) {
		Borrower temp = borrowerdao.findByName(borrower.getBorrowerName(), borrower.getCardNo());
		if(temp==null) {
			return false;
		} else {
			return true;
		}	
	}
	
	public void addBorrower(Borrower borrower) {
		borrowerdao.save(borrower);
	}
	
	public void deleteBorrower(int id) {
		borrowerdao.deleteById(id);
	}
	
	public void updateBorrower(Borrower borrower) {
		borrowerdao.save(borrower);
	}
}
