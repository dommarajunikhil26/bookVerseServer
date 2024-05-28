package com.nikhil.bookVerse.controller;

import com.nikhil.bookVerse.requestmodels.AddBookRequest;
import com.nikhil.bookVerse.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/secure/add/book")
    public void postBook(HttpServletRequest request, @RequestBody AddBookRequest addBookRequest) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        adminService.postBook(addBookRequest);
    }

    @PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(HttpServletRequest request, @RequestParam Long bookId) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        adminService.increaseBookQuantity(bookId);
    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(HttpServletRequest request, @RequestParam Long bookId) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        adminService.decreaseBookQuantity(bookId);
    }

    @DeleteMapping("/secure/delete/book")
    public void deleteBook(HttpServletRequest request, @RequestParam Long bookId) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        adminService.deleteBook(bookId);
    }
}
