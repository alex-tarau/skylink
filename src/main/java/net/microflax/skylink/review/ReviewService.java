package net.microflax.skylink.review;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractService{

    @Autowired
    private ReviewRepository reviewRepository;

}
