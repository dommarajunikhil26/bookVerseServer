package com.nikhil.bookVerse.controller;

import com.nikhil.bookVerse.entity.Book;
import com.nikhil.bookVerse.responsemodels.ShelfCurrentLoansResponse;
import com.nikhil.bookVerse.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam Long bookId, HttpServletRequest request) throws Exception {
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        return bookService.checkoutBook(userEmail, bookId);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId, HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> currentLoans(HttpServletRequest request) throws Exception {
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        return bookService.currentLoans(userEmail);
    }

    @PutMapping("/secure/return")
    public void returnBook(HttpServletRequest request, @RequestParam Long bookId) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        bookService.returnBook(userEmail, bookId);
    }

    @PutMapping("/secure/renew/loan")
    public void renewLoan(HttpServletRequest request, @RequestParam Long bookId) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        bookService.renewLoan(userEmail, bookId);
    }
}
