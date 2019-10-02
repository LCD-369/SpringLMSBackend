package com.team.alpha.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class BookLoanId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer bookId;
	private Integer branchId;
	private Integer cardNo;
	
	public BookLoanId() {
		
	}
	
	public BookLoanId(int bookId, int branchId, int cardNo) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
	}

	public int getBookId() {
		return bookId;
	}
	public int getBranchId() {
		return branchId;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	
}
