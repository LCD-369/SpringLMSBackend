package com.team.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.alpha.entity.BookLoan;
import com.team.alpha.entity.BookLoanId;

@Repository
public interface BookLoanDao extends JpaRepository<BookLoan, BookLoanId> {

}
