package net.microflax.skylink.review;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractService<Review,Integer> {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    protected JpaRepository<Review, Integer> getRepository() {
        return reviewRepository;
    }

    @Override
    protected Review preSave(Review review) {
        // In the future, the review might have additional attributes to populate before persist the airline entity
        return null;
    }
}
