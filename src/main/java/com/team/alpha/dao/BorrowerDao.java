package com.team.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.entity.Borrower;

@Repository
public interface BorrowerDao extends JpaRepository<Borrower, Integer> {

	@Query(value="from Borrower where name=:name and cardNo=:cardNo")
	Borrower findByName(@Param("name") String name, 
			@Param("cardNo") Integer cardNo);
}
