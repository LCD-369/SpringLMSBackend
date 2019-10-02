package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.BookDao;
import com.team.alpha.entity.Book;

@Service
public class BookService {
	
	@Autowired
	BookDao bookdao;
	
	public List<Book> getAllBooks(){
		return bookdao.findAll();
		}
	
	public Book getBookById(Integer id){
		Book tempBook = bookdao.findById(id).get();
		return tempBook;
	}
	
	public Boolean doesBookExist(Book book) {
		Book temp = bookdao.findByTitle(book.getTitle());
		if(temp==null) {
			return false;
		} else {
			return true;
		}	
	}
	
	public void addBook(Book book) {
		bookdao.save(book);
	}
	
	public void deleteBook(int id) {
		bookdao.deleteById(id);
	}
	
	public void updateBook(Book book){
		bookdao.save(book);
	}
}
