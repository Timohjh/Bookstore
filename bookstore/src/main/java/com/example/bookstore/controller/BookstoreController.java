package com.example.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.domain.Bookstore;
import com.example.bookstore.domain.BookstoreRepository;

@Controller
public class BookstoreController {
	@Autowired
	private BookstoreRepository repository;
	
	@RequestMapping("/bookslist")
	public String listBookstore(Model model) {
		model.addAttribute("books", repository.findAll());
		return "bookslist";
	}
	
	@RequestMapping(value = "/addbook")
	public String addBookstore(Model model){
	model.addAttribute("books", new Bookstore());
	return "addbook";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Bookstore bookstore){
	repository.save(bookstore);
	return "redirect:bookslist";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBookstore(@PathVariable("id") Long bookstoreId, Model model) {
	repository.deleteById(bookstoreId);
	return "redirect:../bookslist";
	}
	@RequestMapping(value = "/edit/{id}")
	public String addBookstore(@PathVariable("id") Long bookstoreId, Model model){
	model.addAttribute("bookstore", repository.findById(bookstoreId));
	return "editbookstore";
	}
	
}
