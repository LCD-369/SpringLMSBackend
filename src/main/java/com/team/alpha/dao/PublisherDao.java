package com.team.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.team.alpha.entity.Publisher;

@Repository
public interface PublisherDao extends JpaRepository<Publisher, Integer> {
	
	@Query(value="from Publisher where publisherName=:publisherName")
	Publisher findByName(@Param("publisherName") String publisherName);
}
