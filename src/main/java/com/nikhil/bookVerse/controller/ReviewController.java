package com.nikhil.bookVerse.controller;

import com.nikhil.bookVerse.entity.Book;
import com.nikhil.bookVerse.requestmodels.ReviewRequest;
import com.nikhil.bookVerse.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http:localhost:3000")
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/secure")
    public void postReview(HttpServletRequest request, @RequestBody ReviewRequest reviewRequest) throws  Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(HttpServletRequest request, @RequestParam Long bookId) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        if (userEmail == null) {
            throw new RuntimeException("User email not found in request attributes.");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }
}
