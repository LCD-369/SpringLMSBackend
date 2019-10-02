package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.BookLoanDao;
import com.team.alpha.entity.BookLoan;
import com.team.alpha.entity.BookLoanId;

@Service
public class BookLoanService {

	@Autowired
	BookLoanDao bookloandao;
	
	public List<BookLoan> getAllBookLoans(){
		return bookloandao.findAll();
	}
	
	public void overrideDueDate(BookLoan bookloan) {
		bookloandao.save(bookloan);
	}
	
	public Boolean doesBookLoanExist(BookLoan bookloan) {
		BookLoanId loanid = new BookLoanId(bookloan.getBookId(), 
				bookloan.getBranchId(), bookloan.getCardNo());
		if(bookloandao.existsById(loanid)) {
			return true;
		} else {
			return false;
		}
				
	}
	
}