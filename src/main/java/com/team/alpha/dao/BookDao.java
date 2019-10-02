package com.team.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {
	
	@Query(value="from Book where title=:title")
	Book findByTitle(@Param("title") String title);
}
