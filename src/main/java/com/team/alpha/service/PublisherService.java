package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.PublisherDao;
import com.team.alpha.entity.Publisher;

@Service
public class PublisherService {

	@Autowired
	PublisherDao publisherdao;
	
	public List<Publisher> getAllPublishers(){
		return publisherdao.findAll();
	}
	
	public Publisher getPublisherById(Integer id){
		Publisher tempPublisher = publisherdao.findById(id).get();
		return tempPublisher;
	}
	
	public Boolean doesPublisherExist(Publisher publisher) {
		Publisher temp = publisherdao.findByName(publisher.getPublisherName());
		if(temp==null) {
			return false;
		} else {
			return true;
		}	
	}
	
	public void addPublisher(Publisher publisher) {
		publisherdao.save(publisher);
	}
	
	public void deletePublisher(int id) {
		publisherdao.deleteById(id);
	}
	
	public void updatePublisher(Publisher publisher) {
		publisherdao.save(publisher);
	}
}
