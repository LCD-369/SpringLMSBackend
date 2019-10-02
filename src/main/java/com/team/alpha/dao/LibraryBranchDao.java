package com.team.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.entity.LibraryBranch;

@Repository
public interface LibraryBranchDao extends JpaRepository<LibraryBranch, Integer> {
	
	@Query(value="from LibraryBranch where branchName=:branchName")
	LibraryBranch findByName(@Param("branchName") String branchName);
}
