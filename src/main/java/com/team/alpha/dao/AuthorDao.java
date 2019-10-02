package com.team.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.entity.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {	
	
	@Query(value="from Author where authorName=:authorName")
	Author findByName(@Param("authorName") String authorname);
}
