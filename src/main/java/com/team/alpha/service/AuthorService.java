package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.AuthorDao;
import com.team.alpha.entity.Author;

@Service
public class AuthorService {
	
	@Autowired
	AuthorDao authordao;
	
	public List<Author> getAllAuthors() {
		return authordao.findAll();
	}
	
	public Author findAuthorById(int id) {
		return authordao.findById(id).get();
	}
	public Boolean doesAuthorExist(Author author) {
		Author temp = authordao.findByName(author.getName());
		if(temp==null) {
			return false;
		} else {
			return true;
		}	
	}

	public void addAuthor(Author author) {
		authordao.save(author);
	}
	
	public void removeAuthor(int id) {
		authordao.deleteById(id);
	}
	
	public void updateAuthor(Author author) {
		authordao.save(author);
	}
}
