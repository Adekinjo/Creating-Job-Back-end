package com.kinjo1.FirstJob.review;

import java.util.List;

public interface ReviewService {

    // getting reviews
    List<Review> getAllReviews(Long CompanyId);

    // to add reviews
    boolean addReview(Long companyId, Review reviews);

    Review getReview(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId,Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
