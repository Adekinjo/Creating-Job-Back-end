package com.kinjo1.FirstJob.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //  To get reviews
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review reviews){
        boolean isReviewSaves = reviewService.addReview(companyId, reviews);
        if(isReviewSaves) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not add", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReiview(@PathVariable Long companyId, @PathVariable Long reviewId,
                                                @RequestBody Review review){
        boolean isRevieUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isRevieUpdated) {
            return new ResponseEntity<>("Review update successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isReviewDeleted) {
            return new ResponseEntity<>("Review update successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
        }
    }
}
