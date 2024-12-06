package com.kinjo1.FirstJob.review.implement;

import com.kinjo1.FirstJob.Company.Company;
import com.kinjo1.FirstJob.Company.CompanyService;
import com.kinjo1.FirstJob.review.Review;
import com.kinjo1.FirstJob.review.ReviewRepository;
import com.kinjo1.FirstJob.review.ReviewService;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;


    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review reviews) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            reviews.setCompany(company);
            reviewRepository.save(reviews);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews1 = reviewRepository.findByCompanyId(companyId);
        return reviews1.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
